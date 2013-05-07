#include <stdio.h>

#include <windows.h>
#include <mmsystem.h>
#include <process.h>


int InitWinSock();
void StartListening(void *port);
void Stop();

typedef int (_cdecl *AsyncDLL_Callback_Proto) (int NumAnalog, int *Analog, int AnalogMin, int AnalogMax, int NumDigital, char *Digital);

AsyncDLL_Callback_Proto	UpdatePPJoy;

#define	NUM_CHANNELS	2
#define NUM_BUTTONS		32
#define ANALOG_MIN		0
#define ANALOG_MAX		150

#define PACKET_SIZE			16
#define PACKET_X_OFFSET		4
#define PACKET_Y_OFFSET		8
#define PACKET_DIR_OFFSET	0
#define PACKET_BTN_OFFSET	12

static int Position[NUM_CHANNELS] = {75,75};
static char Buttons[NUM_BUTTONS] = {0};
BOOL ServerRunning = FALSE;

void UpdateJoystick (char *data) {
	int i = 0;
	int buttons = 0;
	memcpy(&Position[0], &data[PACKET_X_OFFSET], 4);
	memcpy(&Position[1], &data[PACKET_Y_OFFSET], 4);
	memcpy(&buttons, &data[PACKET_BTN_OFFSET], 4);
	for ( i = 0; i < NUM_BUTTONS; i++) {
		Buttons[i] = (char)((buttons >> i) & 0x01);
	}
	UpdatePPJoy (NUM_CHANNELS, Position, ANALOG_MIN, ANALOG_MAX, NUM_BUTTONS, Buttons);
	return;
}

int __stdcall InitDLL (AsyncDLL_Callback_Proto CallbackFunc)
{
	if (!CallbackFunc)
		return 0;

	UpdatePPJoy= CallbackFunc;
	InitWinSock();
	ServerRunning = TRUE;
	_beginthread(&StartListening, 0, NULL);
	return 1;
}

int __stdcall Cleanup (void)
{
	Stop();
	return 1;
}
WSADATA wsaData;
SOCKET server;
char buf[255] = {0};

int InitWinSock(){
	int error = 0;
	error = WSAStartup( MAKEWORD( 2, 0 ), &wsaData );
	if ( error != 0 ) {
		return -1;
	}
	if ( LOBYTE( wsaData.wVersion ) != 2 ||
		HIBYTE( wsaData.wVersion ) != 0 ) {
			WSACleanup();
			return -1;
	}
	return 0;
}

void __cdecl StartListening(void *p) {
	struct sockaddr_in sin;
	struct sockaddr sfrom;
	int sfromLen = sizeof sfrom;
	int bytesReceived = 0;
	int t = 0;
	server = socket( AF_INET, SOCK_DGRAM, 0 );

	memset( &sin, 0, sizeof sin );

	sin.sin_family = AF_INET;
	sin.sin_addr.s_addr = INADDR_ANY;
	sin.sin_port = htons( 5000 );

	if ( bind( server, &sin, sizeof sin ) == SOCKET_ERROR ) {
		return;
	}

	while (ServerRunning) {
		bytesReceived = recv (server, buf, 255, 0);
		t = WSAGetLastError();
		if (bytesReceived >= PACKET_SIZE) {
			UpdateJoystick(buf);
		}
	}
}
void Stop(){
	ServerRunning = FALSE;
	WSACleanup();
}
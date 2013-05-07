aGamepad allows you to use your Android based phone as gamepad for Windows. It supports three analog axes and up to 32 buttons (currently implemented 4).

Special thanks to:
Tsutomu SEKI
Windows side of application is based on his sources.

Deon van der Westhuysen
For writing such a great application as PPJoy.

How to use
Download aGamepad.apk and install it on your device
Download PPJoySetup and install it on your PC
Download controller.dll and extract it somewhere
After installing PPJoySetup run Configure Joysticks (Programs->Parallel Port Joystick)
Press Add
Make sure that Parallel port field is set to Virtual Joysticks. Press Add
Wait for drivers to be installed
Choose currently added joystick from list and press Mapping
Press Next
Choose any number of axes (max 3)
Choose number of buttons (currently max 4)
Set POV to 0 and press Next
Make sure there is for each axis set Analog N and press Next
Press Next and Finish
Press Done
Now everything is ready for use

Run PPJoyDLL (Programs->Parallel Port Joystick)
Choose DLL type to Callback DLL interface and press Load DLL
Point the dll you saved earlier
Now run aGamepad on your phone (assuming that you have connected with your PC using wifi)
Enter IP address of your PC and press Ok
If you see in PPJoyDLL's window line "Sending joystick updates to PPJoy" it means that everything works

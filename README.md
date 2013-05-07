aGamepad allows you to use your Android based phone as gamepad for Windows. It supports three analog axes and up to 32 buttons (currently implemented 4).

Special thanks to:
------------------
### Tsutomu SEKI
Windows side of application is based on his sources.

### Deon van der Westhuysen
For writing such a great application as PPJoy.

How to use
----------
1. Download aGamepad.apk and install it on your device
2. Download PPJoySetup and install it on your PC
3. Download controller.dll and extract it somewhere
4. After installing PPJoySetup run Configure Joysticks (Programs->Parallel Port Joystick)
5. Press Add
6. Make sure that Parallel port field is set to Virtual Joysticks. Press Add
7. Wait for drivers to be installed
8. Choose currently added joystick from list and press Mapping
9. Press Next
10. Choose any number of axes (max 3)
11. Choose number of buttons (currently max 4)
12. Set POV to 0 and press Next
13. Make sure there is for each axis set Analog N and press Next
14. Press Next and Finish
15. Press Done
Now everything is ready for use

Run PPJoyDLL (Programs->Parallel Port Joystick)
-----------------------------------------------
1. Choose DLL type to Callback DLL interface and press Load DLL
2. Point the dll you saved earlier
3. Now run aGamepad on your phone (assuming that you have connected with your PC using wifi)
4. Enter IP address of your PC and press Ok
5. If you see in PPJoyDLL's window line "Sending joystick updates to PPJoy" it means that everything works

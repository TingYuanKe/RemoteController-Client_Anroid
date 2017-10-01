
# RemoteController-Client_Anroid
An android APP to remote your laptop by android device


Here is Server-side project, if want to go Client-side<br>
  see:https://github.com/TingYuanKe/RemoteController-Server_PC/
# Image
<img src="http://i.imgur.com/r0ZklFR.jpg" width="22%" height="22%">
<img src="http://i.imgur.com/lRzkb2x.jpg" width="22%" height="22%">
<img src="http://i.imgur.com/vjyQLUe.jpg" width="22%" height="22%">
<img src="http://i.imgur.com/XMJkJcv.jpg" width="22%" height="22%"><br>
<img src="http://i.imgur.com/c9eIP2L.png" width="30%" height="30%">
# Introduction
 The Application Android app that send messages over wifi to the receiving server.<br>
 Using Socket mechanism, TCP / IP protocol to communicate with Server-side<br>
 <br>
 Application allow users use to their own mobile phone to connect the computer via Wifi, 
 and control the computer mouse ,keyboard, remote Powerpoint and other related actions.
  
#### `Server` 
- Runs on PC with Windows 7/10 OS.<br>
          develop using `Vs2015 C`# with `DllImport("user32.dll" API`<br>
           <br>
          The main function of the server is to receive the messages from the CLIENT side, 
          to decode and perform the corresponding operations, and to maintain the operation of the server
          
#### `Client` 
- Runs on Android device. Send commands to PC.<br>
          develop using Android Studio with `Android 5.0`and `SDKVersion 22`  
      
  The App(cleint) is divided into four classes.
  - `Mouse Control`:
    Touch on the screen to control the mouse with left and right mouse button.
    Single finger click on the screen for the left mouse click.
    Double finger click on the screean for the right mouse click.
<br>

  - `Keyboard Control`:
    Input the word or number on the android keyboard and click Enter to transfer the String to PC.
    Use backspace button on android keyboard to be backspace button on PC
<br>

  - `PowerPoint Control`:
    Developing...
<br>

  - `System Control`:
    Adjust the volume of the sound, mute, back to the desktop, sleep, shutdown.


# How to use
 - Donwload and run the application both on your phone(Client) and computer(Server)
 - Make sure your computer is connected to the same WIFI location as your phone.
 - Enter your IP address show on Server application to App side.
 - Try it!
 
# TODO
 - PowerPoint Controll.
 - Mechanism to detect if the socket is disconnected, and reconnected with both size application.
 - Fix keyboard control with some bug that may get null input, and develop to support Chinese characters.
 
# Contact me 
This application is a student-level project.The code may not be very clean and readable.<br>
It's welcome to contact me if you have any question<br>
               TingYuan-Ke:prelude0390@hotmail.com

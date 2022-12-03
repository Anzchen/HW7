# CS3500 Assignment04
## By Jenna and John


**All the images used for JUnit testing were created by ourselves**

Through testing, every part of the program is complete
in the sense that it is working.

The first controller is represented by the ImageController
interface and implemented by ImageControllerImpl. Its main purpose is calling
control(), which allows the user to interact with the program through
a Readable.
*The original controller's design has stayed the same since the previous assignment.*

The new controller is represented by the ImageGuiController interface and implemented
by ImageGuiControllerImpl. This class also implements
the ViewEvents interface, which has a bunch of functions
that communicate with the model. The view calls a ViewEvent method, which
then tells the model to do something.

The view is represented by ImageGuiView and implemented by
ImageGuiViewImpl. This class essentially builds and displays the GUI.
It has buttons that call action events that call
ViewEvents, which go to the controller and then the
model. The view also uses the Histogram class, which
extends JPanel, to make and display histograms. 

The Histogram class extends JPanel and holds data for an image's red, green,
blue, or intensity values and draws it.


The model is represented by ImageModel and implemented by
ImageModelImpl. It has one field which is a HashMap<String, PixelRBG[][]>.
This allows the model to store images according to a name
the user wants to use to refer to the image as. The model is
responsible for implementation of the images, modifying, loading, or saving
them as needed. *The model's design has stayed the same since the previous
assignment.*

Assisting the model is a function object interface called
ImageFunctionObject. This interface contains one sole method: apply(PixelRGB[][]).
Based on the implementation of the interface, apply takes in an image
and modifies it. The model's process method can take in an ImageFunctionObject
and use its apply method to modify one of its images. *This interface's design
has stayed the same since the previous assignment.*


A user can interact with the program via System.in,
a script file, or the GUI.
. More info in the USEME file, which lists
every command.


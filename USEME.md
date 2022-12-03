# Command Line Inputs

Run the program through a script file. Replace
"path-of-script-file" to the actual file path.
Script commands are shown below.
```
java -jar Program.jar -file path-of-script-file
```

Run the program through interactive commands. 
Script commands are shown below.
```
java -jar Program.jar -text
```

Rune the program through an interactive GUI.
```
java -jar Program.jar
```

## Script Commands
**Everything must be typed in the order as shown,
case-sensitive**

Load an image from your files and refer to it henceforth by a new name
```
load (file-name)
```
Save an image to your files from the program 
```
save (image-name, new-file-name, new-image-name)
```
Greyscale the image based on the red, green, blue, value, intensity, or luma
of the image
```
red-component (image-name, new-image-name)

green-component (image-name, new-image-name)

blue-component (image-name, new-image-name)

value-component (image-name, new-image-name)

intensity-component (image-name, new-image-name)

luma-component (image-name, new-image-name)
```

Flip the image horizontally
```
horizontal-flip (image-name, new-image-name)
```
Flip the image vertically
```
vertical-flip (image-name, new-image-name)
```
Brighten the image based on an amount. You can input
a negative value to darken the image instead.
```
brighten (amount, image-name, new-image-name)
```
Greyscale the image based on luma. (Note: this is
identical to the luma-component command.)
```
greyscale (image-name, new-image-name)
```
Make a sepia tone version of the image
```
sepia (image-name, new-image-name)
```
Blur the image
```
blur (image-name, new-image-name)
```
Sharpen the image
```
sharpen (image-name, new-image-name)
```
Run the program using a script file
```
file (file-name)
```
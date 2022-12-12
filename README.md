# Easy Animator
OOD class project that uses the MVC and Swing to create an animator with multiple views.

Easy Animator is a Java program that creates animations from a text file. The program can output multiple types of views:
1) Visual: plays an animation once through at a given speed.
2) Playback: plays an animation with multiple options including looping, pausing, resetting, and changing speed.
3) Text: outputs a human-readable description of the animation to the console or a specified text file.
4) SVG: outputs an SVG version of the animation to the console or a file. An SVG file can be played in any compatible browser.

## Usage
To run the program, the following command must be run in the command line, followed by a series of arguments specified below. This assumes the shell is open at the folder of EasyAnimator.jar; otherwise, provide its absolute path.
```
java -cp EasyAnimator.jar cs5004.animator.EasyAnimator
```
There are four argument flags, each of which must be immediately followed by a corresponding argument. These argument pairs can appear in any order. All arguments must be separated by a single space. 

The only required argument flags are -in and -view and their corresponding arguments. 

```
-in name-of-animation-file
-view type-of-view
-out where-output-goes
-speed integer-ticks-per-second
```
Valid options for view (don't include quotes when calling them): "text", "visual", "playback", and "svg".

File names must be given as an absolute file path.

If an output file isn't specified for the text or SVG views, the output will default to the console.

The speed argument must be greater than or equal to 1.


### Examples of running the program
```
java -cp EasyAnimator.jar cs5004.animator.EasyAnimator -in /name/documents/smalldemo.txt -view text -speed 2
```
use smalldemo.txt for the animation file, and create a text view with its output going to System.out, and a speed of 2 ticks per second.
```
java -cp EasyAnimator.jar cs5004.animator.EasyAnimator -view svg -out out.svg -in buildings.txt
```
use buildings.txt for the animation file, and create an SVG view with its output going to the file out.svg, with a speed of 1 tick per second.
```
java -cp EasyAnimator.jar cs5004.animator.EasyAnimator -in smalldemo.txt -view text
```
use smalldemo.txt for the animation file, and create a text view with its output going to System.out.
```
java -cp EasyAnimator.jar cs5004.animator.EasyAnimator -in smalldemo.txt -speed 50 -view visual
```
use smalldemo.txt for the animation file, and create a visual view to show the animation at a speed of 50 ticks per second.

## Design
Easy Animator was created using the Model View Controller design paradigm.

The main class, EasyAnimator.java, creates the model by parsing the input file into relevent shapes and animations. EasyAnimator creates the specified view and passes the model and view to the controller, which then takes control of the program.

The model consists of four data structures to store information on the shapes and their animations:
1) a hashmap where the key is the name of a shape and the value is the shape object. 
2) a linked list of shapes sorted by the order they were added from the input file.
3) a linked list of all animation steps sorted by the time their time of appearance in the animation.
4) a hashmap where the key is the name of a shape and the value is a linked list of animations associated only with that shape in the order those animations occur.

The controller manages data retrieval from the model for the view. All data structures retrieved from the model are deep copies of the originals in order to avoid unintentional or unwanted modification of the data by the view.

The text view and svg view loop through all shapes and animations and use getter methods to populate a string with information about the shapes and animations. The string gets appended to an Appendable object, which can be either the console or a file (using a FileWriter object).

The visual and playback views use Java Swing to animate. The playback view is a more complex version of the visual view. Both use the Swing Timer to increment through the frames of the animation. The timer is handled by the controller which computes an updated list of shapes with their animated values at a snapshot in time. This list gets sent to a JPanel to paint the shapes.

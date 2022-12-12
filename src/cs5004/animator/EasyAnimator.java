package cs5004.animator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.ControllerImpl;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.Builder;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.View;
import cs5004.animator.view.VisualView;

/**
 * The main class of the Easy Animator program. This class creates the animation
 * model, the view, and then gives those to the controller, which takes control
 * of the rest of the program.
 * 
 * @author Bram Ziltzer
 *
 */
public final class EasyAnimator extends JFrame {

  private static final long serialVersionUID = 1L;
  private static String inputFile = "";
  private static String outputFile = "";
  private static String viewType = "";
  private static int speed = 1;

  /*
   * Displays a given error message to the user in a Java Swing JOptionPane and
   * quits the program.
   */
  private static void showErrorMessage(String error) {
    JFrame errorFrame = new JFrame();
    errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JOptionPane.showMessageDialog(errorFrame, "ERROR: " + error + "\n");
    System.exit(-1);
  }

  /*
   * Parses the command line arguments and stores the arguments in their relevant
   * variables. Checks for invalid or missing inputs.
   */
  private static void parseCommandLine(String[] args) {
    if (args.length % 2 == 1) {
      showErrorMessage(
          "Odd number of arguments detected. Inputs must be given in pairs, e.g. -speed 10");
    }
    for (int i = 0; i < args.length; i++) {
      String parameter = args[i];

      if (args[i].startsWith("-") && args[i + 1].startsWith("-")) {
        showErrorMessage("Can't start two adjacent arguments with \"-\"");
      }

      switch (parameter) {
        // checks to stop argument from being added twice
        case "-in":
          if (inputFile.equals("")) {
            inputFile = args[i + 1].toLowerCase();
          }
          break;
  
        case "-out":
          if (outputFile.equals("")) {
            outputFile = args[i + 1];
          }
          break;
  
        case "-view":
          if (viewType.equals("")) {
            viewType = args[i + 1].toLowerCase();
          }
          break;
  
        case "-speed":
          try {
            speed = Integer.parseInt(args[i + 1]);
            if (speed < 1) {
              showErrorMessage("Speed must be an integer greater than 0");
            }
          } catch (NumberFormatException nfe) {
            showErrorMessage("Speed must be an integer greater than 0");
          }
          break;
        default:
          // no default case
      }
    }

    // check for missing or invalid view type and check for missing input file name
    if (viewType.equals("")) {
      showErrorMessage("View argument is empty. A view type must be specified.");
    } else if (!(viewType.equals("text") || viewType.equals("svg") || viewType.equals("visual")
        || viewType.equals("playback"))) {
      showErrorMessage("Invalid view type \"" + viewType
          + "\"\nAvailable views: text, svg, visual, and playback.\n");
    } else if (inputFile.equals("")) {
      showErrorMessage("Input file argument is empty. An input file must be specified.");
    }
  }

  /**
   * Runs the Easy Animator program with the specified command line inputs.
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // parse command line arguments
    View view = null;

    parseCommandLine(args);

    BufferedReader in = null;
    try {
      in = new BufferedReader(new FileReader(inputFile));
    } catch (FileNotFoundException fnfe) {
      showErrorMessage(fnfe.getMessage());
    }

    FileWriter fw = null;
    switch (viewType) {
      case "text":
        if (outputFile.equals("")) {
          view = new TextView(System.out);
        } else {
          try {
            fw = new FileWriter(outputFile);
          } catch (IOException e) {
            showErrorMessage(e.getMessage());
          }
          view = new TextView(fw);
        }
        break;
  
      case "svg":
        if (outputFile.equals("")) {
          view = new SVGView(System.out, speed);
        } else {
          try {
            fw = new FileWriter(outputFile);
          } catch (IOException e) {
            showErrorMessage(e.getMessage());
          }
          view = new SVGView(fw, speed);
        }
        break;
  
      case "visual":
        view = new VisualView(speed);
        break;
  
      case "playback":
        view = new PlaybackView(speed);
        break;
      default:
        // no default case 
    }

    // create model
    AnimationBuilder<AnimationModel> builder = new Builder();
    AnimationModel model = AnimationReader.parseFile(in, builder);

    // create controller
    Controller controller = new ControllerImpl(model, view);

    // give control to controller
    controller.run();

    if (fw != null) {
      try {
        fw.close();
      } catch (IOException e) {
        showErrorMessage(e.getMessage());
      }
    }
  }

}

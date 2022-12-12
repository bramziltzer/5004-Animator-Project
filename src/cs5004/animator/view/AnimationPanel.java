package cs5004.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import cs5004.animator.model.Shape;

/**
 * An AnimationPanel is a JPane implementation that uses a list of Shape objects
 * to paint the Shapes on a canvas.
 * 
 * @author Bram Ziltzer
 *
 */
public class AnimationPanel extends JPanel {

  private static final long serialVersionUID = 1L;
  ArrayList<Shape> shapes;

  /**
   * Creates an instance of an AnimationPanel.
   */
  public AnimationPanel() {
    super(true); // set visible
    this.shapes = new ArrayList<Shape>();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!shapes.isEmpty()) {
      for (Shape shape : shapes) {
        if (shape == null) {
          return;
        }
        
        g.setColor(new Color(shape.getColor().getRed(), shape.getColor().getGreen(),
            shape.getColor().getBlue()));
        
        if (shape.getShapeType().equals("oval")) {
          // adjust location and dimensions to account for swing drawing oval upper right
          // corner instead of center
          g.fillOval(shape.getLocation().x - shape.getXDimension(),
              shape.getLocation().y - shape.getYDimension(), shape.getXDimension() * 2,
              shape.getYDimension() * 2);
        } else if (shape.getShapeType().equals("rectangle")) {
          g.fillRect(shape.getLocation().x, shape.getLocation().y, shape.getXDimension(),
              shape.getYDimension());
        }
      }
    }
  }

  /**
   * Sets the list of Shapes to paint on the screen.
   * 
   * @param shapeList the list of Shapes to paint on the screen
   */
  public void setShapes(ArrayList<Shape> shapeList) {
    this.shapes = shapeList;
  }
}

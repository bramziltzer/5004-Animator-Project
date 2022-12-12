
import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.ControllerImpl;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.Color;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.view.TextView;
import cs5004.animator.view.View;

/**
 * JUnit test of the ControllerImpl class.
 * 
 * @author Bram Ziltzer
 *
 */
public class ControllerImplTest {

  /**
   * Test of the controller run method.
   */
  @Test
  public void testControllerGo() {
    // ensure controller runs view

    AnimationModel m = new AnimationModelImpl();
    StringBuilder sb = new StringBuilder();
    View v = new TextView(sb);

    m.addShape(new Rectangle("R", new Point(0, 0), 5, 5, new Color(0, 0, 0), 0, 50));
    m.addShape(new Oval("o1", new Point(0, 0), 5, 5, new Color(0, 0, 0), 5, 10));

    m.addAnimation("R", 1, 200, 200, 50, 100, 255, 0, 0, 10, 200, 300, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("R", 11, 200, 300, 50, 100, 255, 0, 0, 14, 200, 200, 50, 100, 0, 0, 0);
    m.addAnimation("o1", 5, 0, 0, 5, 5, 0, 0, 0, 10, 5, 5, 15, 15, 255, 0, 100);

    Controller c = new ControllerImpl(m, v);

    c.run();
    assertEquals(
        "Create rectangle R with corner at (0.0,0.0) with color(0,0,0), width 5 and height 5\n"
            + "Create oval o1 with center at (0.0,0.0) with color(0,0,0), x radius 5 and y "
            + "radius 5\n\n" + "R appears at time t=0 and disappears at time t=50\n"
            + "o1 appears at time t=5 and disappears at time t=10\n" + "\n"
            + "R changes color from (255,0,0) to (0,0,0) from t=1 to t=10\n"
            + "R moves from (200.0,200.0) to (200.0,300.0) from t=1 to t=10\n"
            + "o1 changes color from (0,0,0) to (255,0,100) from t=5 to t=10\n"
            + "o1 changes x radius from 2 to 7 from t=5 to t=10\n"
            + "o1 changes y radius from 2 to 7 from t=5 to t=10\n"
            + "o1 moves from (2.0,2.0) to (12.0,12.0) from t=5 to t=10\n"
            + "R changes color from (255,0,0) to (0,0,0) from t=11 to t=14\n"
            + "R moves from (200.0,300.0) to (200.0,200.0) from t=11 to t=14\n",
        sb.toString());
  }

}

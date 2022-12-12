package cs5004.animator.util;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;

/**
 * A Builder is an implementation of the AnimationBuilder for creating an
 * AnimationModel.
 * 
 * @author Bram Ziltzer
 *
 */
public final class Builder implements AnimationBuilder<AnimationModel> {

  private AnimationModel model;

  /**
   * Creates an instance of a Builder used to read a file and create an
   * AnimationModel.
   */
  public Builder() {
    this.model = new AnimationModelImpl();

  }

  @Override
  public AnimationModel build() {
    return this.model;
  }

  @Override
  public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
    this.model.setCanvasBounds(x, y, width, height);
    return this;
  }

  @Override
  public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
    int infinity = Integer.MAX_VALUE;
    if (type.equals("ellipse")) {
      model.addShape(new Oval(name, null, infinity, infinity, null, infinity, infinity));
    } else if (type.equals("rectangle")) {
      model.addShape(new Rectangle(name, null, infinity, infinity, null, infinity, infinity));
    }

    return this;
  }

  @Override
  public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
      int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
      int b2) {
    model.addAnimation(name, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
    return this;
  }

}
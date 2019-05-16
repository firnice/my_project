package com.airbnb.lottie.json.model.animatable;


import com.airbnb.lottie.json.value.Keyframe;

import java.awt.*;

public class AnimatableSplitDimensionPathValue implements AnimatableValue<Point, Point> {
  private final AnimatableFloatValue animatableXDimension;
  private final AnimatableFloatValue animatableYDimension;

  public AnimatableSplitDimensionPathValue(
      AnimatableFloatValue animatableXDimension,
      AnimatableFloatValue animatableYDimension) {
    this.animatableXDimension = animatableXDimension;
    this.animatableYDimension = animatableYDimension;
  }

  @Override
  public List<Keyframe<Point>> getKeyframes() {
    throw new UnsupportedOperationException("Cannot call getKeyframes on AnimatableSplitDimensionPathValue.");
  }

  @Override
  public boolean isStatic() {
    return animatableXDimension.isStatic() && animatableYDimension.isStatic();
  }

  @Override public BaseKeyframeAnimation<Point, Point> createAnimation() {
    return new SplitDimensionPathKeyframeAnimation(
        animatableXDimension.createAnimation(), animatableYDimension.createAnimation());
  }
}

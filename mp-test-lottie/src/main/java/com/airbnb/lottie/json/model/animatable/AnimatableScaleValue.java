package com.airbnb.lottie.json.model.animatable;

import com.airbnb.lottie.json.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.json.keyframe.ScaleKeyframeAnimation;
import com.airbnb.lottie.json.value.Keyframe;
import com.airbnb.lottie.json.value.ScaleXY;

import java.util.List;

public class AnimatableScaleValue extends BaseAnimatableValue<ScaleXY, ScaleXY> {

  AnimatableScaleValue() {
    this(new ScaleXY(1f, 1f));
  }

  public AnimatableScaleValue(ScaleXY value) {
    super(value);
  }

  public AnimatableScaleValue(List<Keyframe<ScaleXY>> keyframes) {
    super(keyframes);
  }

  @Override public BaseKeyframeAnimation<ScaleXY, ScaleXY> createAnimation() {
    return new ScaleKeyframeAnimation(keyframes);
  }
}

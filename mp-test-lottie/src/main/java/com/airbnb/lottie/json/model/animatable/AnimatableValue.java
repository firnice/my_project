package com.airbnb.lottie.json.model.animatable;


import com.airbnb.lottie.json.value.Keyframe;

import java.util.List;

public interface AnimatableValue<K, A> {
  List<Keyframe<K>> getKeyframes();
  boolean isStatic();
  BaseKeyframeAnimation<K, A> createAnimation();
}

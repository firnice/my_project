package com.airbnb.lottie.json.keyframe;

import com.airbnb.lottie.json.LottieComposition;
import com.airbnb.lottie.json.Utils;
import com.airbnb.lottie.json.value.Keyframe;

import javax.annotation.Nullable;
import java.awt.*;
import java.nio.file.Path;

public class PathKeyframe extends Keyframe<Point> {
  @Nullable private Path path;

  private final Keyframe<Point> pointKeyFrame;

  public PathKeyframe(LottieComposition composition, Keyframe<Point> keyframe) {
    super(composition, keyframe.startValue, keyframe.endValue, keyframe.interpolator,
        keyframe.startFrame, keyframe.endFrame);
    this.pointKeyFrame = keyframe;
    createPath();
  }

  public void createPath() {
    // This must use equals(float, float) because PointF didn't have an equals(PathF) method
    // until KitKat...
    boolean equals = endValue != null && startValue != null &&
        startValue.equals(endValue.x, endValue.y);
    //noinspection ConstantConditions
    if (endValue != null && !equals) {
      path = Utils.createPath(startValue, endValue, pointKeyFrame.pathCp1, pointKeyFrame.pathCp2);
    }
  }

  /** This will be null if the startValue and endValue are the same. */
  @Nullable
  Path getPath() {
    return path;
  }
}

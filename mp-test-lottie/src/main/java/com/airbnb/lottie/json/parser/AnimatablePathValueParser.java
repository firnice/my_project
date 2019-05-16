package com.airbnb.lottie.json.parser;


import com.airbnb.lottie.json.LottieComposition;
import com.airbnb.lottie.json.Utils;
import com.airbnb.lottie.json.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.json.model.animatable.AnimatablePathValue;
import com.airbnb.lottie.json.model.animatable.AnimatableSplitDimensionPathValue;
import com.airbnb.lottie.json.model.animatable.AnimatableValue;
import com.airbnb.lottie.json.value.Keyframe;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnimatablePathValueParser {

  private AnimatablePathValueParser() {}

  public static AnimatablePathValue parse(
          JsonReader reader, LottieComposition composition) throws IOException {
    List<Keyframe<Point>> keyframes = new ArrayList<>();
    if (reader.peek() == JsonToken.BEGIN_ARRAY) {
      reader.beginArray();
      while (reader.hasNext()) {
        keyframes.add(PathKeyframeParser.parse(reader, composition));
      }
      reader.endArray();
      KeyframesParser.setEndFrames(keyframes);
    } else {
      keyframes.add(new Keyframe<>(JsonUtils.jsonToPoint(reader, Utils.dpScale())));
    }
    return new AnimatablePathValue(keyframes);
  }

  /**
   * Returns either an {@link AnimatablePathValue} or an {@link AnimatableSplitDimensionPathValue}.
   */
  static AnimatableValue<Point, Point> parseSplitPath(
      JsonReader reader, LottieComposition composition) throws IOException {

    AnimatablePathValue pathAnimation = null;
    AnimatableFloatValue xAnimation = null;
    AnimatableFloatValue yAnimation = null;

    boolean hasExpressions = false;

    reader.beginObject();
    while (reader.peek() != JsonToken.END_OBJECT) {
      switch (reader.nextName()) {
        case "k":
          pathAnimation = AnimatablePathValueParser.parse(reader, composition);
          break;
        case "x":
          if (reader.peek() == JsonToken.STRING) {
            hasExpressions = true;
            reader.skipValue();
          } else {
            xAnimation = AnimatableValueParser.parseFloat(reader, composition);
          }
          break;
        case "y":
          if (reader.peek() == JsonToken.STRING) {
            hasExpressions = true;
            reader.skipValue();
          } else {
            yAnimation = AnimatableValueParser.parseFloat(reader, composition);
          }
          break;
        default:
          reader.skipValue();
      }
    }
    reader.endObject();

    if (hasExpressions) {
//      composition.addWarning("Lottie doesn't support expressions.");
    }

    if (pathAnimation != null) {
      return pathAnimation;
    }
    return new AnimatableSplitDimensionPathValue(xAnimation, yAnimation);
  }

}

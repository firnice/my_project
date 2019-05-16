package com.airbnb.lottie.json.parser;


import com.airbnb.lottie.json.value.ScaleXY;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;

public class ScaleXYParser implements ValueParser<ScaleXY> {
  public static final ScaleXYParser INSTANCE = new ScaleXYParser();

  private ScaleXYParser() {
  }

  @Override public ScaleXY parse(JsonReader reader, float scale) throws IOException {
    boolean isArray = reader.peek() == JsonToken.BEGIN_ARRAY;
    if (isArray) {
      reader.beginArray();
    }
    float sx = (float) reader.nextDouble();
    float sy = (float) reader.nextDouble();
    while (reader.hasNext()) {
      reader.skipValue();
    }
    if (isArray) {
      reader.endArray();
    }
    return new ScaleXY(sx / 100f * scale, sy / 100f * scale);
  }

}

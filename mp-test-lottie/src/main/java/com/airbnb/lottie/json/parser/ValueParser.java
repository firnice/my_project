package com.airbnb.lottie.json.parser;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

interface ValueParser<V> {
  V parse(JsonReader reader, float scale) throws IOException;
}

package com.airbnb.lottie;

public class Test {

    public static LottieResult<LottieComposition> fromJsonStringSync(String json) {
        return fromJsonReaderSync(new JsonReader(new StringReader(json)));
    }
}

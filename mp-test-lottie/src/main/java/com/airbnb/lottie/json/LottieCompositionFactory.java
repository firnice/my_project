package com.airbnb.lottie.json;

import com.airbnb.lottie.json.parser.LottieCompositionParser;
import com.google.gson.stream.JsonReader;

import javax.annotation.Nullable;
import java.io.StringReader;


/**
 * Helpers to create or cache a LottieComposition.
 * <p>
 * All factory methods take a cache key. The animation will be stored in an LRU cache for future use.
 * In-progress tasks will also be held so they can be returned for subsequent requests for the same
 * animation prior to the cache being populated.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class LottieCompositionFactory {
    /**
     * Keep a map of cache keys to in-progress tasks and return them for new requests.
     * Without this, simultaneous requests to parse a composition will trigger multiple parallel
     * parse tasks prior to the cache getting populated.
     */

    private LottieCompositionFactory() {
    }


    /**
     * @see #fromJsonStringSync(String, String)
     */
    public static LottieResult<LottieComposition> fromJsonString(final String json, @Nullable final String cacheKey) {

        return fromJsonStringSync(json, cacheKey);
    }

    /**
     * Return a LottieComposition for the specified raw json string.
     * If loading from a file, it is preferable to use the InputStream or rawRes version.
     */
    public static LottieResult<LottieComposition> fromJsonStringSync(String json, @Nullable String cacheKey) {
        return fromJsonReaderSync(new JsonReader(new StringReader(json)), cacheKey);
    }

    /**
     * Return a LottieComposition for the specified json.
     */
    public static LottieResult<LottieComposition> fromJsonReaderSync(JsonReader reader, @Nullable String cacheKey) {
        return fromJsonReaderSyncInternal(reader, cacheKey, true);
    }

    private static LottieResult<LottieComposition> fromJsonReaderSyncInternal(
            JsonReader reader, @Nullable String cacheKey, boolean close) {
        try {
            LottieComposition composition = LottieCompositionParser.parse(reader);
            return new LottieResult<>(composition);
        } catch (Exception e) {
            return new LottieResult<>(e);
        } finally {
            if (close) {
//        closeQuietly(reader);
            }
        }
    }


    @Nullable
    private static LottieImageAsset findImageAssetForFileName(LottieComposition composition, String fileName) {
        for (LottieImageAsset asset : composition.getImages().values()) {
            if (asset.getFileName().equals(fileName)) {
                return asset;
            }
        }
        return null;
    }

}

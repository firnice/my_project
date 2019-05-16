package com.airbnb.lottie.json;


import com.airbnb.lottie.json.model.Font;
import com.airbnb.lottie.json.model.Marker;
import com.airbnb.lottie.json.model.layer.Layer;
import org.opencv.core.Rect;

import javax.annotation.Nullable;
import java.util.*;

/**
 * After Effects/Bodymovin composition model. This is the serialized model from which the
 * animation will be created.
 *
 * To create one, use {@link LottieCompositionFactory}.
 *
 */
public class LottieComposition {

//  private final PerformanceTracker performanceTracker = new PerformanceTracker();
  private final HashSet<String> warnings = new HashSet<>();
  private Map<String, List<Layer>> precomps;
  private Map<String, LottieImageAsset> images;
  /** Map of font names to fonts */
  private Map<String, Font> fonts;
  private List<Marker> markers;
//  private SparseArrayCompat<FontCharacter> characters;
  private LongSparseArray<Layer> layerMap;
  private List<Layer> layers;
  // This is stored as a set to avoid duplicates.
  private Rect bounds;
  private float startFrame;
  private float endFrame;
  private float frameRate;
  /**
   * Used to determine if an animation can be drawn with hardware acceleration.
   */
  private boolean hasDashPattern;
  /**
   * Counts the number of mattes and masks. Before Android switched to SKIA
   * for drawing in Oreo (API 28), using hardware acceleration with mattes and masks
   * was only faster until you had ~4 masks after which it would actually become slower.
   */
  private int maskAndMatteCount = 0;

  public void init(Rect bounds, float startFrame, float endFrame, float frameRate,
      List<Layer> layers, LongSparseArray<Layer> layerMap, Map<String,
      List<Layer>> precomps, Map<String, LottieImageAsset> images,
//      SparseArrayCompat<FontCharacter> characters, 
                   Map<String, Font> fonts,
      List<Marker> markers) {
    this.bounds = bounds;
    this.startFrame = startFrame;
    this.endFrame = endFrame;
    this.frameRate = frameRate;
    this.layers = layers;
    this.layerMap = layerMap;
    this.precomps = precomps;
    this.images = images;
//    this.characters = characters;
    this.fonts = fonts;
    this.markers = markers;
  }
 

  
  public void setHasDashPattern(boolean hasDashPattern) {
    this.hasDashPattern = hasDashPattern;
  }

  
  public void incrementMatteOrMaskCount(int amount) {
    maskAndMatteCount += amount;
  }

  /**
   * Used to determine if an animation can be drawn with hardware acceleration.
   */
  
  public boolean hasDashPattern() {
    return hasDashPattern;
  }

  /**
   * Used to determine if an animation can be drawn with hardware acceleration.
   */
  
  public int getMaskAndMatteCount() {
    return maskAndMatteCount;
  }

  public ArrayList<String> getWarnings() {
    return new ArrayList<>(Arrays.asList(warnings.toArray(new String[warnings.size()])));
  }


  
  public Layer layerModelForId(long id) {
    return layerMap.get(id);
  }

  @SuppressWarnings("WeakerAccess") public Rect getBounds() {
    return bounds;
  }

  @SuppressWarnings("WeakerAccess") public float getDuration() {
    return (long) (getDurationFrames() / frameRate * 1000);
  }

  
  public float getStartFrame() {
    return startFrame;
  }

  
  public float getEndFrame() {
    return endFrame;
  }

  public float getFrameRate() {
    return frameRate;
  }

  public List<Layer> getLayers() {
    return layers;
  }

  
  @Nullable
  public List<Layer> getPrecomps(String id) {
    return precomps.get(id);
  }

//  public SparseArrayCompat<FontCharacter> getCharacters() {
//    return characters;
//  }

  public Map<String, Font> getFonts() {
    return fonts;
  }

  public List<Marker> getMarkers() {
    return markers;
  }

  @Nullable
  public Marker getMarker(String markerName) {
    int size = markers.size();
    for (int i = 0; i < markers.size(); i++) {
      Marker marker = markers.get(i);
      if (markerName.equals(marker.name)) {
        return marker;
      }
    }
    return null;
  }

  public boolean hasImages() {
    return !images.isEmpty();
  }

  @SuppressWarnings("WeakerAccess") public Map<String, LottieImageAsset> getImages() {
    return images;
  }

  public float getDurationFrames() {
    return endFrame - startFrame;
  }


  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("LottieComposition:\n");
    for (Layer layer : layers) {
      sb.append(layer.toString("\t"));
    }
    return sb.toString();
  }

}

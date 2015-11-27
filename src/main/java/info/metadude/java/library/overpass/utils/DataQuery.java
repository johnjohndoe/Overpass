package info.metadude.java.library.overpass.utils;

import java.util.Map;

/**
 * Constructs a data query for Overpass-Turbo
 * Example: "[out:json];node(around:600,52.516667,13.383333)[\"amenity\"=\"post_box\"];out qt 13;"
 */
public class DataQuery {

    protected final int radius;

    protected final double longitude;

    protected final double latitude;

    protected final Map<String, String> tags;

    protected final boolean sortByDistance;

    protected final int maxResponseCount;

    public DataQuery(int radius,
                     double longitude,
                     double latitude,
                     Map<String, String> tags,
                     boolean sortByDistance,
                     int maxResponseCount) {
        this.radius = radius;
        this.longitude = longitude;
        this.latitude = latitude;
        this.tags = tags;
        this.sortByDistance = sortByDistance;
        this.maxResponseCount = maxResponseCount;
        validateParams();
    }

    public String getFormattedDataQuery() {
        String sortInstruction = sortByDistance ? "qt " : "";
        String formattedTags = getFormattedTags();
        return "[out:json];" +
                "node(around:" + radius + "," + longitude + "," + latitude + ")" +
                formattedTags +
                "out " +
                sortInstruction +
                maxResponseCount + ";";
    }

    private void validateParams() {
        if (radius < 2) {
            throw new IllegalArgumentException("Expected maximum radius to be greater then one, but was " + radius);
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Expected longitude between -180 and 180, but was " + longitude);
        }
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Expected latitude between -90 and 90, but was " + latitude);
        }
        if (tags == null || tags.isEmpty()) {
            throw new IllegalArgumentException("Expected at least one tag.");
        }
        if (maxResponseCount < 1) {
            throw new IllegalArgumentException("Expected maximum response count to be " +
                    "greater then zero, but was " + maxResponseCount);
        }
    }

    private String getFormattedTags() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : tags.keySet()) {
            stringBuilder
                    .append("[\"")
                    .append(key).append("\"=\"")
                    .append(tags.get(key))
                    .append("\"]");
        }
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

}

package info.metadude.java.library.overpass.utils.test;

import info.metadude.java.library.overpass.utils.NodesQuery;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

public final class NodesQueryTest {

    @Test
    public void testDataQueryWithTag() {
        String expectedDataQuery =
                "[out:json];node(around:600,52.516667,13.383333)" +
                        "[\"amenity\"=\"post_box\"];out qt 13;";
        Map<String, String> tags = new HashMap<String, String>() {
            {
                put("amenity", "post_box");
            }
        };
        NodesQuery nodesQuery = new NodesQuery(600, 52.516667, 13.383333, tags, true, 13);
        assertThat(nodesQuery.getFormattedDataQuery()).isEqualTo(expectedDataQuery);
    }

    @Test
    public void testDataQueryWithInvalidRadius() {
        Map<String, String> tags = new HashMap<String, String>() {
            {
                put("amenity", "post_box");
            }
        };
        int radius = 0;
        try {
            new NodesQuery(radius, 52.516667, 13.383333, tags, true, 13);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Expected maximum radius to be greater then one, but was " + radius);
        }
    }

    @Test
    public void testDataQueryWithInvalidLongitude() {
        Map<String, String> tags = new HashMap<String, String>() {
            {
                put("amenity", "post_box");
            }
        };
        int longitude = 0;
        try {
            new NodesQuery(600, longitude, 13.383333, tags, true, 13);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Expected longitude between -180 and 180, but was " + longitude);
        }
    }

    @Test
    public void testDataQueryWithInvalidLatitude() {
        Map<String, String> tags = new HashMap<String, String>() {
            {
                put("amenity", "post_box");
            }
        };
        int latitude = 0;
        try {
            new NodesQuery(600, 52.516667, latitude, tags, true, 13);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Expected latitude between -90 and 90, but was " + latitude);
        }
    }

    @Test
    public void testDataQueryWithInvalidResponseCount() {
        Map<String, String> tags = new HashMap<String, String>() {
            {
                put("amenity", "post_box");
            }
        };
        int maxResponseCount = 0;
        try {
            new NodesQuery(600, 52.516667, 13.383333, tags, true, maxResponseCount);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Expected maximum response count to be " +
                    "greater then zero, but was " + maxResponseCount);
        }
    }

    @Test
    public void testUnsortedDataQuery() {
        String expectedDataQuery =
                "[out:json];node(around:600,52.516667,13.383333)" +
                        "[\"amenity\"=\"post_box\"];out 13;";
        Map<String, String> tags = new HashMap<String, String>() {
            {
                put("amenity", "post_box");
            }
        };
        boolean sortByDistance = false;
        //noinspection ConstantConditions
        NodesQuery nodesQuery = new NodesQuery(600, 52.516667, 13.383333, tags, sortByDistance, 13);
        assertThat(nodesQuery.getFormattedDataQuery()).isEqualTo(expectedDataQuery);
    }

    @Test
    public void testDataQueryWithoutTags() {
        try {
            new NodesQuery(600, 52.516667, 13.383333, null, true, 13);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Expected at least one tag.");
        }
    }

    @Test
    public void testDataQueryWithMultipleTags() {
        String expectedDataQuery =
                "[out:json];node(around:600,52.516667,13.383333)" +
                        "[\"amenity\"=\"recycling\"][\"recycling:clothes\"=\"yes\"];" +
                        "out qt 13;";
        Map<String, String> tags = new TreeMap<String, String>() {
            {
                put("amenity", "recycling");
                put("recycling:clothes", "yes");
            }
        };
        NodesQuery nodesQuery = new NodesQuery(600, 52.516667, 13.383333, tags, true, 13);
        assertThat(nodesQuery.getFormattedDataQuery()).isEqualTo(expectedDataQuery);
    }

}

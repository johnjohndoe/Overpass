package info.metadude.java.library.overpass.test;

import info.metadude.java.library.overpass.ApiModule;
import info.metadude.java.library.overpass.OverpassService;
import info.metadude.java.library.overpass.models.Element;
import info.metadude.java.library.overpass.models.OverpassResponse;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class OverpassServiceTest {

    protected OverpassService streamsService;

    @Before
    public void initStreamService() {
        streamsService = ApiModule.provideOverpassService();
    }

    @Test
    public void testThatRealServerIsReachable() {
        int responseLimit = 13;
        String data = "[out:json];" +
                "node(around:1600,52.516667,13.383333)" +
                "[\"amenity\"=\"post_box\"];" +
                "out qt " + responseLimit + ";";
        Call<OverpassResponse> streamsResponseCall = streamsService.getOverpassResponse(data);

        try {
            Response<OverpassResponse> response = streamsResponseCall.execute();
            if (response.isSuccessful()) {
                OverpassResponse overpassResponse = response.body();
                // Expect at least some data
                assertThat(overpassResponse)
                        .isNotNull();
                List<Element> elements = overpassResponse.elements;
                assertThat(elements)
                        .isNotNull()
                        .isNotEmpty();
                assertThat(elements.size()).isLessThanOrEqualTo(responseLimit);
                for (Element element : elements) {
                    testElement(element);
                }
            } else {
                fail("GetOffers response is not successful.");
            }
        } catch (IOException e) {
            fail("Should not throw " + e);
        }
    }

    private void testElement(Element element) {
        assertThat(element).isNotNull();
        assertThat(element.id).isGreaterThan(0);
        assertThat(element.lat).isGreaterThan(0);
        assertThat(element.lon).isGreaterThan(0);
        assertThat(element.type).isEqualTo("node");
        assertThat(element.tags).isNotNull();
    }

}

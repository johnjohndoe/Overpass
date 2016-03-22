package info.metadude.java.library.overpass;

import info.metadude.java.library.overpass.models.OverpassResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Service to query Overpass-Turbo.
 * Example query: http://overpass-api.de/api/interpreter?data=[out:json];node(around:1600,52.516667,13.383333)["amenity"="post_box"];out qt 13;
 */
public interface OverpassService {

    String BASE_URL = "http://overpass-api.de";

    /**
     * Returns a Overpass response for the given query.
     *
     * @param data Overpass QL string data part
     *             Example: [out:json];node(around:1600,52.516667,13.383333)["amenity"="post_box"];out qt 13;
     * @return a call to execute the actual query.
     */
    @GET("/api/interpreter")
    Call<OverpassResponse> getOverpassResponse(
            @Query("data") String data
    );

}

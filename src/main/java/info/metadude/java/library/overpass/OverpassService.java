package info.metadude.java.library.overpass;

import info.metadude.java.library.overpass.models.OverpassResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface OverpassService {

    String BASE_URL = "http://overpass-api.de";

    // http://overpass-api.de/api/interpreter?data=[out:json];node(around:1600,52.516667,13.383333)["amenity"="post_box"];out;
    // Pass in data as: [out:json];node(around:1600,52.516667,13.383333)["amenity"="post_box"];out;
    @GET("/api/interpreter")
    Call<OverpassResponse> getOverpassResponse(
            @Query("data") String data
    );

}

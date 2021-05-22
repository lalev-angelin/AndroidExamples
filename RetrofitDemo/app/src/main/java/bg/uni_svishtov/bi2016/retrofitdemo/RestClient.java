package bg.uni_svishtov.bi2016.retrofitdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RestClient {
    @POST("/authenticate")
    Call<LoginResponse> authenticate (@Body LoginRequest request);

    @GET("/v1/marks")
    Call<List<Mark>> getMarks(@Header("Authorization") String token);
}

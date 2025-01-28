package com.example.cryptoapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/v1/cryptocurrency/quotes/latest")
    Call<CryptoCurrencyResponse> getCryptoCurrencyData(
            @Header("X-CMC_PRO_API_KEY") String apiKey,
            @Query("id") int id
    );

    @GET("/v1/global-metrics/quotes/latest")
    Call<GlobalMetricsResponse> getGlobalMetrics(
            @Header("X-CMC_PRO_API_KEY") String apiKey
    );

}
package com.example.cryptoapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToolFragment extends Fragment {

    private TextView textViewMetrics;
    private final String API_KEY = "2c30889f-5f8d-492b-8807-86fb55ea2aef";
    private ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tool, container, false);


        textViewMetrics = view.findViewById(R.id.textViewMetrics);


        apiService = ApiClient.getClient().create(ApiService.class);


        obtenerMetricasGlobales();

        return view;
    }

    private void obtenerMetricasGlobales() {
        apiService.getGlobalMetrics(API_KEY).enqueue(new Callback<GlobalMetricsResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<GlobalMetricsResponse> call, Response<GlobalMetricsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mostrarMetricas(response.body());
                } else {
                    textViewMetrics.setText("Error al obtener métricas globales.");
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(Call<GlobalMetricsResponse> call, Throwable t) {
                textViewMetrics.setText("Error de conexión.");
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void mostrarMetricas(GlobalMetricsResponse metrics) {

        if (metrics == null || metrics.getData() == null || metrics.getData().getQuote() == null || metrics.getData().getQuote().getUsd() == null) {
            textViewMetrics.setText("Datos incompletos.");
            return;
        }


        GlobalMetricsResponse.Data data = metrics.getData();
        GlobalMetricsResponse.Data.Quote.USD usd = data.getQuote().getUsd();


        String resultado = String.format(
                getString(R.string.capitalizaci_n_total_del_mercado_usd_s) +
                        "Volumen total 24h (USD): %s\n\n" +
                        "Dominancia BTC: %.2f%%\n\n" +
                        "Criptomonedas activas: %d\n\n" +
                        "Volumen DeFi 24h (USD): %s\n\n" +
                        "Volumen stablecoin 24h (USD): %s\n\n" +
                        "Volumen de derivados 24h (USD): %s\n\n" +
                        "Última actualización: %s",
                usd.getTotalMarketCap() != 0 ? usd.getTotalMarketCap() : "N/A",  // Verificación de null o valor por defecto
                usd.getTotalVolume24h() != 0 ? usd.getTotalVolume24h() : "N/A",
                data.getBtcDominance() != 0 ? data.getBtcDominance() : "N/A",
                data.getActiveCryptocurrencies() != 0 ? data.getActiveCryptocurrencies() : "N/A",
                usd.getDefiVolume24h() != 0 ? usd.getDefiVolume24h() : "N/A",
                usd.getStablecoinVolume24h() != 0 ? usd.getStablecoinVolume24h() : "N/A",
                usd.getDerivativesVolume24h() != 0 ? usd.getDerivativesVolume24h() : "N/A",
                data.getTimestamp() != null ? data.getTimestamp() : "Desconocido"
        );


        textViewMetrics.setText(resultado);
    }

}


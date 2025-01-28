package com.example.cryptoapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.Map;

public class CryptoFragment extends Fragment {
    private TextView textViewCriptoData;
    private Spinner spinner;
    private ImageView imageViewLogo;
    private ApiService apiService;
    private final String API_KEY = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crypto, container, false);


        textViewCriptoData = view.findViewById(R.id.textViewCriptoData);
        spinner = view.findViewById(R.id.spinner);
        imageViewLogo = view.findViewById(R.id.imageViewLogo);


        apiService = ApiClient.getClient().create(ApiService.class);


        configurarSpinner();

        return view;
    }

    private void configurarSpinner() {

        String[] criptomonedas = {"BTC", "ETH", "BNB", "ADA", "DOGE"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                R.layout.spinner_item,
                criptomonedas
        );


        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);


        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCrypto = criptomonedas[position];
                int cryptoId = obtenerIdDeCriptomoneda(selectedCrypto);
                obtenerDatosCriptomoneda(cryptoId);
                cargarLogo(selectedCrypto);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private int obtenerIdDeCriptomoneda(String symbol) {
        switch (symbol) {
            case "BTC": return 1;
            case "ETH": return 1027;
            case "BNB": return 1839;
            case "ADA": return 2010;
            case "DOGE": return 74;
            default: return -1;
        }
    }

    private void obtenerDatosCriptomoneda(int id) {
        apiService.getCryptoCurrencyData(API_KEY, id).enqueue(new Callback<CryptoCurrencyResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<CryptoCurrencyResponse> call, Response<CryptoCurrencyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mostrarDatosCriptomoneda(response.body());
                } else {
                    textViewCriptoData.setText("Error al obtener los datos.");
                }
            }

            @Override
            public void onFailure(Call<CryptoCurrencyResponse> call, Throwable t) {
                textViewCriptoData.setText(R.string.error_de_conexi_n);
                Log.e("API_ERROR", t.getMessage());
            }
        });
    }

    private void mostrarDatosCriptomoneda(CryptoCurrencyResponse response) {
        StringBuilder resultado = new StringBuilder();


        Map<String, CryptoCurrencyResponse.CryptoCurrency> data = response.getData();
        if (data != null && !data.isEmpty()) {

            CryptoCurrencyResponse.CryptoCurrency crypto = data.values().iterator().next();
            CryptoCurrencyResponse.Quote quote = crypto.getQuote().get("USD");

            resultado.append("Nombre: ").append(crypto.getName()).append("\n\n")
                    .append("Símbolo: ").append(crypto.getSymbol()).append("\n\n")
                    .append("Precio (USD): ").append(quote.getPrice()).append("\n\n")
                    .append("Capitalización de mercado: ").append(quote.getMarketCap()).append("\n\n")
                    .append("Volumen 24h: ").append(quote.getVolume24h()).append("\n\n")
                    .append("Cambio 24h: ").append(quote.getPercentChange24h()).append("%\n\n");

        } else {
            resultado.append("No se encontraron datos para la criptomoneda seleccionada.");
        }


        textViewCriptoData.setText(resultado.toString());
    }

    private void cargarLogo(String crypto) {
        String url = obtenerUrlLogo(crypto);
        Picasso.get().load(url).into(imageViewLogo);
    }

    private String obtenerUrlLogo(String crypto) {
        switch (crypto) {
            case "BTC": return "https://cryptologos.cc/logos/bitcoin-btc-logo.png";
            case "ETH": return "https://cryptologos.cc/logos/ethereum-eth-logo.png";
            case "BNB": return "https://cryptologos.cc/logos/binance-coin-bnb-logo.png";
            case "ADA": return "https://cryptologos.cc/logos/cardano-ada-logo.png";
            case "DOGE": return "https://cryptologos.cc/logos/dogecoin-doge-logo.png";
            default: return "";
        }
    }
}


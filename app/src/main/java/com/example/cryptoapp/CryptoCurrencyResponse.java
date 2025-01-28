package com.example.cryptoapp;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CryptoCurrencyResponse {

    @SerializedName("data")
    private Map<String, CryptoCurrency> data;

    @SerializedName("status")
    private Status status;

    public Map<String, CryptoCurrency> getData() {
        return data;
    }

    public Status getStatus() {
        return status;
    }

    public static class CryptoCurrency {
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("symbol")
        private String symbol;

        @SerializedName("quote")
        private Map<String, Quote> quote;


        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getSymbol() {
            return symbol;
        }

        public Map<String, Quote> getQuote() {
            return quote;
        }
    }

    public static class Quote {
        @SerializedName("price")
        private double price;
        @SerializedName("volume_24h")
        private double volume24h;
        @SerializedName("percent_change_24h")
        private double percentChange24h;

        @SerializedName("market_cap")
        private double marketCap;
        @SerializedName("last_updated")
        private String lastUpdated;

        public double getPrice() {
            return price;
        }
        public double getVolume24h() {
            return volume24h;
        }
        public String getLastUpdated() {
            return lastUpdated;
        }

        public double getPercentChange24h() {
            return percentChange24h;
        }

        public double getMarketCap() {
            return marketCap;
        }
    }

    public static class Status {
        @SerializedName("timestamp")
        private String timestamp;

        @SerializedName("error_code")
        private int errorCode;

        public String getTimestamp() {
            return timestamp;
        }

        public int getErrorCode() {
            return errorCode;
        }
    }
}
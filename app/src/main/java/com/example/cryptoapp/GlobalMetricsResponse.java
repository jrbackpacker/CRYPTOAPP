package com.example.cryptoapp;

import com.google.gson.annotations.SerializedName;

public class GlobalMetricsResponse {

    @SerializedName("data")
    private Data data;

    @SerializedName("status")
    private Status status;

    public Data getData() {
        return data;
    }

    public Status getStatus() {
        return status;
    }

    public static class Data {
        @SerializedName("btc_dominance")
        private double btcDominance;

        @SerializedName("eth_dominance")
        private double ethDominance;

        @SerializedName("active_cryptocurrencies")
        private int activeCryptocurrencies;

        @SerializedName("total_cryptocurrencies")
        private int totalCryptocurrencies;

        @SerializedName("active_market_pairs")
        private int activeMarketPairs;

        @SerializedName("active_exchanges")
        private int activeExchanges;

        @SerializedName("total_exchanges")
        private int totalExchanges;

        @SerializedName("quote")
        private Quote quote;

        public double getBtcDominance() {
            return btcDominance;
        }

        public double getEthDominance() {
            return ethDominance;
        }

        public int getActiveCryptocurrencies() {
            return activeCryptocurrencies;
        }

        public int getTotalCryptocurrencies() {
            return totalCryptocurrencies;
        }

        public int getActiveMarketPairs() {
            return activeMarketPairs;
        }

        public int getActiveExchanges() {
            return activeExchanges;
        }

        public int getTotalExchanges() {
            return totalExchanges;
        }

        public Quote getQuote() {
            return quote;
        }

        public Object getTimestamp() {
            return quote;
        }

        public static class Quote {
            @SerializedName("USD")
            private USD usd;

            public USD getUsd() {
                return usd;
            }

            public static class USD {
                @SerializedName("total_market_cap")
                private double totalMarketCap;

                @SerializedName("total_volume_24h")
                private double totalVolume24h;

                @SerializedName("altcoin_volume_24h")
                private double altcoinVolume24h;

                @SerializedName("defi_volume_24h")
                private double defiVolume24h;

                @SerializedName("stablecoin_volume_24h")
                private double stablecoinVolume24h;

                @SerializedName("derivatives_volume_24h")
                private double derivativesVolume24h;

                public double getTotalMarketCap() {
                    return totalMarketCap;
                }

                public double getTotalVolume24h() {
                    return totalVolume24h;
                }

                public double getAltcoinVolume24h() {
                    return altcoinVolume24h;
                }

                public double getDefiVolume24h() {
                    return defiVolume24h;
                }

                public double getStablecoinVolume24h() {
                    return stablecoinVolume24h;
                }

                public double getDerivativesVolume24h() {
                    return derivativesVolume24h;
                }
            }
        }
    }

    public static class Status {
        @SerializedName("timestamp")
        private String timestamp;

        @SerializedName("error_code")
        private int errorCode;

        @SerializedName("error_message")
        private String errorMessage;

        public String getTimestamp() {
            return timestamp;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}

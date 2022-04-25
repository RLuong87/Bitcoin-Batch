package com.bitcoindata.BitcoinProcessor.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitcoinData {

    private String timeStamp;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume_BTC;
    private String volume_Currency;
    private String weighted_Price;

    public BitcoinData() {
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    @JsonProperty("Timestamp")
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getOpen() {
        return open;
    }

    @JsonProperty("Open")
    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    @JsonProperty("High")
    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    @JsonProperty("Low")
    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    @JsonProperty("Close")
    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume_BTC() {
        return volume_BTC;
    }

    @JsonProperty("Volume_(BTC)")
    public void setVolume_BTC(String volume_BTC) {
        this.volume_BTC = volume_BTC;
    }

    public String getVolume_Currency() {
        return volume_Currency;
    }

    @JsonProperty("Volume_(Currency)")
    public void setVolume_Currency(String volume_Currency) {
        this.volume_Currency = volume_Currency;
    }

    public String getWeighted_Price() {
        return weighted_Price;
    }

    @JsonProperty("Weighted_Price")
    public void setWeighted_Price(String weighted_Price) {
        this.weighted_Price = weighted_Price;
    }

    @Override
    public String toString() {
        return "BitcoinData{" +
                "timeStamp='" + timeStamp + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", close='" + close + '\'' +
                ", volume_BTC='" + volume_BTC + '\'' +
                ", volume_Currency='" + volume_Currency + '\'' +
                ", weighted_Price='" + weighted_Price + '\'' +
                '}';
    }
}

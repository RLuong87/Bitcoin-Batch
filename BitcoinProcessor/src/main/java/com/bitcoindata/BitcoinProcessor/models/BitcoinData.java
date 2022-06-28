package com.bitcoindata.BitcoinProcessor.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BitcoinData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String timestamp;
    private String date_time;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume_btc;
    private String volume_currency;
    private String weighted_price;

    public BitcoinData() {
    }

    public BitcoinData(String timestamp, String date_time, String open, String high, String low, String close, String volume_btc, String volume_currency, String weighted_price) {
        this.timestamp = timestamp;
        this.date_time = date_time;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume_btc = volume_btc;
        this.volume_currency = volume_currency;
        this.weighted_price = weighted_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume_btc() {
        return volume_btc;
    }

    public void setVolume_btc(String volume_btc) {
        this.volume_btc = volume_btc;
    }

    public String getVolume_currency() {
        return volume_currency;
    }

    public void setVolume_currency(String volume_currency) {
        this.volume_currency = volume_currency;
    }

    public String getWeighted_price() {
        return weighted_price;
    }

    public void setWeighted_price(String weighted_price) {
        this.weighted_price = weighted_price;
    }

    @Override
    public String toString() {
        return "BitcoinData{" +
                "timeStamp='" + timestamp + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", close='" + close + '\'' +
                ", volume_BTC='" + volume_btc + '\'' +
                ", volume_Currency='" + volume_currency + '\'' +
                ", weighted_Price='" + weighted_price + '\'' +
                '}';
    }
}

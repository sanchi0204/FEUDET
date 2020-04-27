package com.example.fireapp.Model;

public class DeviceDetails {
    private String name;
    private String temp;
    private String humidity;

    public DeviceDetails(String name, String temp, String humidity) {
        this.name = name;
        this.temp = temp;
        this.humidity = humidity;
    }

    public DeviceDetails() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}

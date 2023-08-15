package com.mahmoudadany.skytracker.pojo;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse {
    private Location location;
    private Current current;
    private ForecastModel forecast;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public ForecastModel getForecast() {
        return forecast;
    }

    public void setForecast(ForecastModel forecast) {
        this.forecast = forecast;
    }
}

package com.mahmoudadany.skytracker.pojo;

import java.util.List;

public class ForecastModel {
    private List<ForecastDayModel> forecastday;

    public List<ForecastDayModel> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<ForecastDayModel> forecastday) {
        this.forecastday = forecastday;
    }
}

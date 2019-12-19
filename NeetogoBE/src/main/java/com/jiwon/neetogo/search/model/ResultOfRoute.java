package com.jiwon.neetogo.search.model;

import java.util.*;

public class ResultOfRoute {
    int spendingTime;
    ArrayList<String> stationNms;

    public ResultOfRoute() {
    }

    public ResultOfRoute(int spendingTime, ArrayList<String> stationNms) {
        this.spendingTime = spendingTime;
        this.stationNms = stationNms;
    }

    public int getSpendingTime() {
        return spendingTime;
    }

    public void setSpendingTime(int spendingTime) {
        this.spendingTime = spendingTime;
    }

    public ArrayList<String> getStationNms() {
        return stationNms;
    }

    public void setStationNms(ArrayList<String> stationNms) {
        this.stationNms = stationNms;
    }
}

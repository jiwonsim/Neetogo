package com.jiwon.neetogo.search.model;

import java.util.*;

public class Subway extends ArrayList<Station> implements Cloneable {
    public Subway() {}

    public void addStation(String name, String code, String line, int minute) throws Exception {
        Station s = new Station();
        s.setName(name);
        s.setCode(code);
        s.setLine(line);
        s.setMinute(minute);
        addStation(s);
    }

    public void addStation(Station station) throws Exception {
        if (getStatioByCd(station.getCode()) != null)
            throw new Exception("DUPLICATED_CODE");
        super.add(station);
    }

    public Station getStatioByCd(String code) {
        for (Station station : this) {
            if (station.getCode().equals(code)) return station;
        }
        return null;
    }

    public Station getStationByNm(String name) {
        for (Station station : this) {
            if (station.getName().equals(name)) return station;
        }
        return null;
    }

    @Override
    public Subway clone() {
        try {
            return (Subway) super.clone();
        }
        catch (Throwable e) {
            return null;
        }
    }

}

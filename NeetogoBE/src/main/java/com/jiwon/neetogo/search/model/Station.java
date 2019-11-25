package com.jiwon.neetogo.search.model;


import java.util.ArrayList;

public class Station implements Comparable<Station> {
    String code;
    String name;
    String line;
    int minute;
    ArrayList<Station> next;

    public Station() {
        next = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getNextSize(int index) {
        return this.next.size();
    }

    public void addNext(Station station) {
        if (this.next.contains(station)) return;
        if (station == null) {
            this.next.add(null);
        }
        this.next.add(station);
    }

    public void addNext() {
        this.next.add(null);
    }

    public ArrayList<Station> getNext() {
        return this.next;
    }

    @Override
    public int compareTo(Station station) {
        return this.getMinute() - station.getMinute();
    }

}

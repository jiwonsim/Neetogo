package com.jiwon.neetogo.search.service;



import com.jiwon.neetogo.search.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SubwayBuilder {

    private static SubwayBuilder singletone = null;

    public static SubwayBuilder getInstance() {
        if (SubwayBuilder.singletone == null) SubwayBuilder.singletone = new SubwayBuilder();
        return SubwayBuilder.singletone;
    }

    private Subway subway;
    private SubwayBuilder() {
        this.subway = new Subway();
    }

    public Subway build() {
        return this.subway.clone();
    }

    public SubwayBuilder readFile(File subwayFile, File linkFile, File timeFile) throws Exception {
        readSubway(subwayFile);
        readTime(timeFile);
        readLink(linkFile);

        return this;
    }

    private void readSubway(File file) throws Exception {
        byte[] fileBytes = new byte[(int) file.length()];
        try (FileInputStream in = new FileInputStream(file)) {
            in.read(fileBytes, 0, fileBytes.length);
        }

        String[] datas = new String(fileBytes).split("\n");
        if (datas.length <= 0) new IOException("WRONG_DOCUMENTS_STYLE");

        for (String data : datas) {
            String[] words = data.replace("\r", "").split("\t");
            if (words.length == 0) continue;
            this.subway.addStation(words[1], words[0], words[3], 2);
        }
    }

    private void readTime(File file) throws Exception {
        byte[] fileBytes = new byte[(int) file.length()];
        try (FileInputStream in = new FileInputStream(file)) {
            in.read(fileBytes, 0, fileBytes.length);
        }

        String[] datas = new String(fileBytes).split("\n");
        if (datas.length <= 0) new IOException("WRONG_DOCUMENTS_STYLE");

        for (String data : datas) {
            String[] words = data.replace("\r", "").split(",");
            if (words.length == 0) continue;
            Station station = this.subway.getStationByNm(words[0]);

            if (station == null) continue;
            station.setMinute(Integer.parseInt(words[1]));
//            System.out.printf("%s %s\n", words[0], words[1]);
        }
    }

    private void readLink(File file) throws Exception {
        byte[] fileBytes = new byte[(int) file.length()];
        try (FileInputStream in = new FileInputStream(file)) {
            in.read(fileBytes, 0, fileBytes.length);
        }

        String[] datas = new String(fileBytes).split("\n");
        if (datas.length <= 0) new IOException("WRONG_DOCUMENTS_STYLE");

        for (String data : datas) {
            String[] words = data.replace("\r", "").split(",");
            for (int i = 1; i < words.length; i++) {
                setNextStation(words[0], words[i]);
            }
        }
    }

    public SubwayBuilder setNextStation(String curNm, String nextNm) {
        Station curStn = this.subway.getStationByNm(curNm);
        Station nextStn = this.subway.getStationByNm(nextNm);
        setNextStation(curStn, nextStn);
        return this;
    }

    public SubwayBuilder setNextStation(Station curStn, Station nextStn) {
        if (curStn != null) curStn.addNext(nextStn);
        if (nextStn != null) nextStn.addNext(curStn);
        return this;
    }
}

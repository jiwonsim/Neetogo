package com.jiwon.neetogo.files;

import com.jiwon.neetogo.entity.Station;
import com.jiwon.neetogo.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.text.TabExpander;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
public class ReadCSV {

    @Autowired
    StationService stationService;
//    // 서버 시작 시 제일 먼저 실행되는 놈! 자동실행
//    implements CommandLineRunner
//    @Override
//    public void run(String... args) throws Exception{
//        readFile();
//    }

    public void readFile() {
        List<List<String>> ret = new ArrayList<List<String>>();
        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(Paths.get("/Users/simjiwon/Desktop/Project/Neetogo/NeetogoBE/src/main/resources/static/SeoulStationInfo.csv"));
            Charset.forName("UTF-8");
            String line = "";

            int start = 0;

            while ((line = br.readLine()) != null) {
                if (start == 0) {
                    start++;
                    continue;
                }
                List<String> tmpList = new ArrayList<String>();
                String[] array = line.split(",");
                for (int i = 0; i < array.length; i++) {
                    String[] temp = array[i].split("\"");
                    for (int j = 0; j < temp.length; j++) {
                        array[i] = temp[1];
                    }
                }

                tmpList = Arrays.asList(array);
                System.out.println(tmpList);

                Station station = new Station();
                station.setStationCd(tmpList.get(0));
                station.setStationNm(tmpList.get(1));
                station.setLineNum(tmpList.get(2));
                station.setFrCode(tmpList.get(3));

                stationService.saveStationInfo(station);

                ret.add(tmpList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

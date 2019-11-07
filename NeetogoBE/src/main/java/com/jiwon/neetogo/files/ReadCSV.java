package com.jiwon.neetogo.files;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
public class ReadCSV implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception{
        readFile();
    }

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
                tmpList = Arrays.asList(array);
                System.out.println(tmpList);
                ret.add(tmpList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

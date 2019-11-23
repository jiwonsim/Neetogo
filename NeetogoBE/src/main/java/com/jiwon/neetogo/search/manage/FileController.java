package com.jiwon.neetogo.search.manage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileController {


    public void createLinkFile(File stationFile) throws Exception {
        String path = System.getProperty("user.dir");
        File file = new File(path + "/src/files/resources/linkNm.txt");
        byte[] buffer = new byte[(int) stationFile.length()];
        try (FileInputStream in = new FileInputStream(stationFile)) {
            in.read(buffer, 0, buffer.length);
        }

        String[] lines = new String(buffer).split("\n");

        if (lines.length <= 0) new IOException("WRONG_DOCUMENT");

        HashMap<String, ArrayList<String>> result = new HashMap<>();

        for (String line : lines) {
            String[] datas = line.replace("\r", "").split("\t");
            // data[0] : code
            // data[1] : name
            // data[3] : line

            if (result.get(datas[1]) == null) result.put(datas[1], new ArrayList<String>());
            ArrayList<String> values = result.get(datas[1]);
            if (datas[3].equals("01호선")) {
                if (datas[1].equals("인천")) values.add("동인천");
                else if (datas[1].equals("소요산")) values.add("동두천");
            }
            else if (datas[3].equals("02호선")) {
                if (datas[1].equals("신설동")) values.add("용두");
                else if (datas[1].equals("까치산")) values.add("신정네거리");
            }
            else if (datas[3].equals("03호선")) {
                if (datas[1].equals("대화")) values.add("주엽");
                else if (datas[1].equals("오금")) values.add("경찰병원");
            }
            else if (datas[3].equals("04호선")) {
                if (datas[1].equals("당고개")) values.add("상계");
                else if (datas[1].equals("오이도")) values.add("정왕");
            }

            if (values.size() == 0) { // 끝역이 아닌 경우
                values = search(datas[1], lines);
            }

            result.put(datas[1], values);
        }

        StringBuffer sb = new StringBuffer();
        for (String key : result.keySet()) {
            sb.append(key + ",");
//            System.out.printf("%s => ", key);
            int len = 1;
            for (String value : result.get(key)) {
//                System.out.printf("%s ", value);
                if (len != result.get(key).size()) sb.append(value + ",");
                else sb.append(value + "\n");
                len++;
            }
        }

        FileWriter fw = new FileWriter(file, true);
        fw.write(sb.toString());
        fw.close();
    }

    private ArrayList<String> search(String name, String[] lines) {
        ArrayList<String> res = new ArrayList<>();

        for (String line : lines) {
            String[] datas = line.replace("\r", "").split("\t");
            if (datas[1].equals(name)) {
                int codeNum = Integer.parseInt(datas[0]);

                String codeLeft = String.valueOf(codeNum - 1);
                if (codeLeft.length() == 3) codeLeft = "0" + codeLeft;
                String nameLeft = searchNmByCd(codeLeft, lines);
                if (res.contains(nameLeft)) continue;

                String codeRight = String.valueOf(codeNum + 1);
                if (codeRight.length() == 3) codeRight = "0" + codeRight;
                String nameRight = searchNmByCd(codeRight, lines);
                if (res.contains(nameRight)) continue;

                res.add(nameLeft);

                res.add(nameRight);
            }
        }

        return res;

    }

    private String searchNmByCd(String code, String[] lines) {
        for (String line : lines) {
            String[] datas = line.replace("\r", "").split("\t");
            if (datas[0].equals(code)) {
                return datas[1];
            }
        }
        return null;
    }


}

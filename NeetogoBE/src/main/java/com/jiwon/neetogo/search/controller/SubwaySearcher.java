package com.jiwon.neetogo.search.controller;

import com.jiwon.neetogo.search.model.Station;
import com.jiwon.neetogo.search.model.Subway;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SubwaySearcher {

    private Subway subway;
    public SubwaySearcher(Subway subway) {
        this.subway = subway;
    }

    public String searchByNm(String startNm, String endNm) {
        Station startStn = this.subway.getStationByNm(startNm);
        Station endStn = this.subway.getStationByNm(endNm);
        return searchByStn(startStn, endStn);
    }

    public void printPath() {

    }

    public String searchByStn(Station start, Station end) {
        int[] path = new int[9999];
        int[] weight = new int[9999];
        boolean[] visited = new boolean[9999];

        Arrays.fill(weight, Integer.MAX_VALUE);
        Arrays.fill(path, -1);

        weight[Integer.parseInt(start.getCode())] = 0;

        dijkstra(start, end, weight, path);

        StringBuffer resultSb = new StringBuffer();
        resultSb.append(start.getName() + "부터 " + end.getNext() + "까지\n");

        if (weight[Integer.parseInt(end.getCode())] != Integer.MAX_VALUE) {
            resultSb.append(weight[Integer.parseInt(end.getCode())] + "분 걸림\n");
        }
        else resultSb.append("경로 없음\n");

        return resultSb.toString();
    }

    public void dijkstra(Station start, Station end, int[] weight, int[] path) {
        PriorityQueue<Station> pq = new PriorityQueue<>();
        pq.add(start);

        while (!pq.isEmpty()) {
            Station curStn = pq.poll();

            if (curStn.getNext() == null) continue;
            for (Station nextStn : curStn.getNext()) {
                if (weight[Integer.parseInt(nextStn.getCode())]
                > weight[Integer.parseInt(curStn.getCode())] + nextStn.getMinute()) {
                    weight[Integer.parseInt(nextStn.getCode())]
                            = weight[Integer.parseInt(curStn.getCode())] + nextStn.getMinute();
                }

                path[Integer.parseInt(nextStn.getCode())] = Integer.parseInt(curStn.getCode());
                pq.add(nextStn);
            }
        }
    }
}

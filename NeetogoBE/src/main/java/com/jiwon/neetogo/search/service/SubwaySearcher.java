package com.jiwon.neetogo.search.service;


import com.jiwon.neetogo.search.model.*;

import java.util.*;

public class SubwaySearcher {

    private Subway subway;
    public SubwaySearcher(Subway subway) {
        this.subway = subway;
    }

    public void searchByNm(String startNm, String endNm) {
        Station startStn = this.subway.getStationByNm(startNm);
        Station endStn = this.subway.getStationByNm(endNm);
        searchByStn(startStn, endStn);
    }

    public ArrayList<Station> getPath(Station start, Station end, HashMap<String, String> path) {

        Stack<String> stack = new Stack<>();

        String ele = end.getName();
        stack.add(ele);

        while (true) {
            ele = path.get(ele);
            stack.add(ele);
            if (ele == start.getName()) break;
        }

        ArrayList<Station> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(this.subway.getStationByNm(stack.pop()));
        }

        for (Station station : result) System.out.printf("%s => ", station.getName());

        return result;
    }

    public void searchByStn(Station start, Station end) {
        HashMap<String, Integer> weight = new HashMap<>();
        HashMap<String, String> path = new HashMap<>();

        weight.put(start.getName(), 0);

        dijkstra(start, end, weight, path);

        StringBuffer resultSb = new StringBuffer();

        resultSb.append("\n" + start.getName() + "부터 " + end.getName() + "까지\n");
        if (weight.get(end.getName()) != Integer.MAX_VALUE) {
            resultSb.append(weight.get(end.getName()) + "분 걸림\n");
        }
        else resultSb.append("경로 없음\n");

        System.out.printf("%s", resultSb);
        getPath(start, end, path);
    }

    private void dijkstra(Station start, Station end,
                          HashMap<String, Integer> weight,
                          HashMap<String, String> path) {
        ArrayList<String> visitedPath = new ArrayList<>();
        PriorityQueue<Station> pq = new PriorityQueue<>();
        pq.add(start);
        visitedPath.add(start.getName());


        while (!pq.isEmpty()) {
            Station curStn = pq.poll();

            if (curStn.getName().equals(end.getName())) return;

            for (Station nextStn : curStn.getNext()) {
                if (nextStn == null) continue;
                if (visitedPath.contains(nextStn.getName())) {
                    continue;
                }

                if (weight.get(nextStn.getName()) == null) { // empty
                    weight.put(nextStn.getName(), Integer.MAX_VALUE);
                }

                if (weight.get(nextStn.getName()) > weight.get(curStn.getName()) + nextStn.getMinute()) {
                    weight.put(nextStn.getName(), weight.get(curStn.getName()) + nextStn.getMinute());

                    visitedPath.add(nextStn.getName());
                    path.put(nextStn.getName(), curStn.getName());
                    pq.add(nextStn);
                }


            }
        }
    }

}

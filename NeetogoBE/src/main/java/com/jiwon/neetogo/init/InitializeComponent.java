package com.jiwon.neetogo.init;

import com.jiwon.neetogo.search.model.ResultOfRoute;
import com.jiwon.neetogo.search.model.Station;
import com.jiwon.neetogo.search.model.Subway;
import com.jiwon.neetogo.search.service.SubwayBuilder;
import com.jiwon.neetogo.search.service.SubwaySearcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Component
public class InitializeComponent {

    public static Subway subway;
    public static SubwaySearcher searcher;

    @Autowired
    public InitializeComponent() throws Exception {
        this.initSubway();
    }

    public InitializeComponent(boolean check) throws Exception {
        searcher = new SubwaySearcher(subway);
    }

    public void initSubway() throws Exception {
        String path = "/Users/simjiwon/Desktop/Project/Neetogo/NeetogoBE/src/main/java/com/jiwon/neetogo/search/resource/files";

        log.info(path);
        SubwayBuilder builder = SubwayBuilder.getInstance().readFile(
                new File(path + "/station.txt"),
                new File(path + "/linkNm.txt"),
                new File(path + "/times.txt")
        );
        subway = builder.build();
    }


    public ResultOfRoute searchRoute(String start, String end) throws Exception {
        if (subway == null || searcher == null) {
            if (subway == null)
                log.info("subway is null");
            if (searcher == null) {
                log.info("search is null");
            }
        }
        else {
            return searcher.searchByNm(start, end);
        }
        return null;

    }

}

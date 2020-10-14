package dev.jarcadia.vapor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.jarcadia.redao.Dao;
import dev.jarcadia.redao.Index;
import dev.jarcadia.redao.RedaoCommando;
import io.lettuce.core.RedisClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MediaFileScannerTest {

    private final Logger logger = LoggerFactory.getLogger(MediaFileScannerTest.class);

    @Test
    void basicTest() throws IOException, InterruptedException {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379/5");
        RedaoCommando rcommando = RedaoCommando.create(redisClient);
        Index photos = rcommando.getPrimaryIndex("photos");
        Index videos = rcommando.getPrimaryIndex("videos");

//        MediaFileScanner scanner = new MediaFileScanner();
//        ProbeService probeService = new ProbeService("exiftool", "ffprobe");
//        MediaFileScanner.Result result = scanner.scan(Paths.get("/Volumes/WD/Takeout/Google Photos"));


//        for (Path photo : result.getPhotos()) {
//            Dao dao = photos.get(photo.toString());
//            logger.info("Probing photo {}", photo);
//            String probeOutput = probeService.probePhoto(photo);
//            dao.set("probe", probeOutput);
//        }
//
//        for (Path video : result.getVideos()) {
//            Dao dao = videos.get(video.toString());
//            logger.info("Probing video {}", video);
//            String probeOutput = probeService.probeVideo(video);
//            dao.set("probe", probeOutput);
//        }

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//        ProbeParseService parseService = new ProbeParseService(mapper);
//        for (Dao video : videos) {
//            String probeOutput = video.get("probe").asString();
//
//            ProbeParseService.VideoMetadata md = parseService.parseVideoProbe(probeOutput);
//            logger.info("Video {} {}", video.getId(), md.getFormat().getTags().getCreationTime());
//            System.out.println(probeOutput);
//
//        }

//        for (Path photo : result.getPhotos()) {
//            PhotoMetadata md = photoProbeService.read(photo, true);
//            logger.info("Picture {} - {} {}", photo.getFileName(), md.getLocalDateTime(), md.getZoneOffset());
//        }


    }
}

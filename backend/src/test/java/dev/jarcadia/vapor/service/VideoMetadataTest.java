//package dev.jarcadia.vapor.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import dev.jarcadia.vapor.model.VideoMetadata;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class VideoMetadataTest {
//
//
//    @Test
//    void basicTest() throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//        VideoMetadataService service = new VideoMetadataService(mapper, "ffprobe");
//
//        Path video = Paths.get(getClass().getClassLoader().getResource("videos/file_example_MP4_480_1_5MG.mp4")
//                .getFile());
//
//        VideoMetadata metadata = service.read(video, true);
//
//        System.out.println(metadata.getTimestamp());
//
//    }
//}

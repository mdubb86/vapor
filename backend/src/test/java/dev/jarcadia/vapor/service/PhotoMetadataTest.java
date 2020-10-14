package dev.jarcadia.vapor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PhotoMetadataTest {

    @Test
    void basicTest() throws IOException, InterruptedException {
        ProbeService service = new ProbeService("exiftool", "ffprobe");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        ProbeParseService parseService = new ProbeParseService(mapper);

        Path photoFile = Paths.get(getClass().getClassLoader().getResource("photos/117_3.jpg").getFile());
        String probeOutput = service.probePhoto(photoFile);

        ProbeParseService.PhotoMetadata metadata = parseService.parsePhotoProbe(probeOutput);

        System.out.println(metadata.getDateTimeOriginal());

    }
}

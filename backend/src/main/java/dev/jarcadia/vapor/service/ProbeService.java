package dev.jarcadia.vapor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ProbeService {

    private final Logger logger = LoggerFactory.getLogger(ProbeService.class);

    private final Path exiftool;
    private final Path ffprobe;

    @Autowired
    public ProbeService(@Value("${vapor.exiftool:exiftool}") String exiftoolPath,
            @Value("${vapor.ffprobe:ffprobe}") String ffprobePath) {
        this.exiftool = Paths.get(exiftoolPath);
        this.ffprobe = Paths.get(ffprobePath);
    }

    public String probePhoto(Path photoPath) throws IOException, InterruptedException {
        return ExecUtils.exec(exiftool.toString(), "-json", photoPath.toString());
    }

    public String probeVideo(Path videoFile) throws IOException, InterruptedException {
        return ExecUtils.exec(ffprobe.toString(), "-v", "quiet", "-print_format", "json", "-show_format",
                "-show_streams", videoFile.toString());
    }
}

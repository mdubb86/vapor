package dev.jarcadia.vapor.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProbeParseService {

    private final ObjectMapper mapper;
    private final TypeReference<List<PhotoMetadata>> typeRef;

    public ProbeParseService(ObjectMapper mapper) {
        this.mapper = mapper;
        this.typeRef = new TypeReference<List<PhotoMetadata>>(){};
    }

    public PhotoMetadata parsePhotoProbe(String probeOutput) throws JsonProcessingException {
        return mapper.readValue(probeOutput, typeRef).get(0);
    }

    public VideoMetadata parseVideoProbe(String probeOutput) throws JsonProcessingException {
        return mapper.readValue(probeOutput, VideoMetadata.class);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    public static class PhotoMetadata {

        private PhotoMetadata() {}

        private LocalDateTime dateTimeOriginal;
        private ZoneOffset offsetTimeOriginal;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonDeserialize(using = ExiftoolLocalDateTimeDeserializer.class)
        public LocalDateTime getDateTimeOriginal() {
            return dateTimeOriginal;
        }

        public void setDateTimeOriginal(LocalDateTime dateTimeOriginal) {
            this.dateTimeOriginal = dateTimeOriginal;
        }

        public ZoneOffset getOffsetTimeOriginal() {
            return offsetTimeOriginal;
        }

        public void setOffsetTimeOriginal(ZoneOffset offsetTimeOriginal) {
            this.offsetTimeOriginal = offsetTimeOriginal;
        }
    }

    public static class ExiftoolLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

        private final DateTimeFormatter formatter;

        public ExiftoolLocalDateTimeDeserializer() {
            this(null);
        }

        public ExiftoolLocalDateTimeDeserializer(Class<?> vc) {
            super(vc);
            this.formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
        }

        @Override
        public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            return LocalDateTime.parse(jp.getText(), this.formatter);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class VideoMetadata {

        private VideoMetadata() {}

        private Format format;

        public Format getFormat() {
            return format;
        }

        public void setFormat(Format format) {
            this.format = format;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
        public static class Format {

            private Format() {}

            private Tags tags;

            public Tags getTags() {
                return tags;
            }

            public void setTags(Tags tags) {
                this.tags = tags;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
        public static class Tags {

            private Instant creationTime;

            private Tags() {}

            public Instant getCreationTime() {
                return creationTime;
            }

            public void setCreationTime(Instant creationTime) {
                this.creationTime = creationTime;
            }
        }
    }
}


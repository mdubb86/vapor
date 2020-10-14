//package dev.jarcadia.vapor.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
//import dev.jarcadia.vapor.service.VideoMetadataService;
//
//import java.time.Instant;
//
//public class VideoMetadata {
//
//    private Instant timestamp;
//
//    public static VideoMetadata fromStructuredMetadata(VideoMetadataService.StructuredMetadata sm) {
//        VideoMetadata vm = new VideoMetadata();
//        if (sm.getFormat() != null) {
//            if (sm.getFormat().getTags() != null) {
//                vm.setTimestamp(sm.getFormat().getTags().getCreationTime());
//            }
//        }
//        return vm;
//    }
//
//    private VideoMetadata() { }
//
//    public Instant getTimestamp() {
//        return timestamp;
//    }
//
//    private void setTimestamp(Instant timestamp) {
//        this.timestamp = timestamp;
//    }
//}

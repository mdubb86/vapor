//package dev.jarcadia.vapor.model;
//
//import dev.jarcadia.vapor.service.PhotoProbeParseService;
//
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//
//public class PhotoMetadata {
//
//    private PhotoMetadata() { }
//
//    // Primary Exif dates (such as DateTimeOriginal) do not have timezone information.
//    // They are assumed to be local time where/when they were taken.
//    private LocalDateTime localDateTime;
//
//    private ZoneOffset zoneOffset;
//
//    public static PhotoMetadata fromStructuredMetadata(PhotoProbeParseService.Metadata sm) {
//        PhotoMetadata pm = new PhotoMetadata();
//
//        // Primary date fields and their corresponding timezone offset fields
//        // - DateTimeOriginal: OffsetTimeOriginal
//        // - CreateDate: OffsetTimeDigitized
//        // - ModifyDate: OffsetTime
//        pm.setLocalDateTime(sm.getDateTimeOriginal());
//        pm.setZoneOffset(sm.getOffsetTimeOriginal());
//
//        return pm;
//    }
//
//    public LocalDateTime getLocalDateTime() {
//        return localDateTime;
//    }
//
//    private void setLocalDateTime(LocalDateTime localDateTime) {
//        this.localDateTime = localDateTime;
//    }
//
//    public ZoneOffset getZoneOffset() {
//        return zoneOffset;
//    }
//
//    private void setZoneOffset(ZoneOffset zoneOffset) {
//        this.zoneOffset = zoneOffset;
//    }
//
//
//}

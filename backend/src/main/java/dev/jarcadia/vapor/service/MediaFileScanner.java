package dev.jarcadia.vapor.service;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MediaFileScanner {

    private final static Set<String> PHOTO_EXTENSIONS =
            new HashSet<>(Arrays.asList("jpg", "CR2"));

    private final static Set<String> VIDEO_EXTENSIONS =
            new HashSet<>(Arrays.asList("mp4", "mov"));


    public Result scan(Path directory) throws IOException {
        Walker walker = new Walker();
        Files.walkFileTree(directory, walker);
        return new Result(walker.getPhotos(), walker.getVideos());
    }

    private static class Walker extends SimpleFileVisitor<Path> {

        private final List<Path> photos;
        private final List<Path> videos;

        public Walker() {
            this.photos = new ArrayList<>();
            this.videos = new ArrayList<>();
        }

        public List<Path> getPhotos() {
            return photos;
        }

        public List<Path> getVideos() {
            return videos;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            if (hasExtension(file, PHOTO_EXTENSIONS)) {
                photos.add(file);
            } else if (hasExtension(file, VIDEO_EXTENSIONS)) {
                videos.add(file);
            }

            return FileVisitResult.CONTINUE;
        }

        private boolean hasExtension(Path file, Set<String> extensions) {
            String fn = file.getFileName().toString();
            int i = fn.indexOf(".");
            return i == -1 ? false : extensions.contains(fn.substring(i + 1));
        }
    }

    public static class Result {

        private final List<Path> photos;
        private final List<Path> videos;

        public Result(List<Path> photos, List<Path> videos) {
            this.photos = photos;
            this.videos = videos;
        }

        public List<Path> getPhotos() {
            return photos;
        }

        public List<Path> getVideos() {
            return videos;
        }
    }
}

package dev.jarcadia.vapor.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExecUtils {

    public static String exec(String... command) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder()
                .command(command)
                .redirectErrorStream(true);

        Process p = builder.start();
        p.waitFor();

        InputStream in = p.getInputStream();
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
}

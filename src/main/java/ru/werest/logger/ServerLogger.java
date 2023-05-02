package ru.werest.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ServerLogger {
    private static ServerLogger INSTANCE = null;

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private ServerLogger() {}

    public static ServerLogger getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServerLogger();
        }
        return INSTANCE;
    }

    public void log(String message) {
        //[LocalDate] - nickName >> message
        String text = String.format("[%s] - %s \n", LocalDateTime.now(), message);
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(path, true))){
            printWriter.write(text);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


package com.company.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {

    private String filePath;
    private String content;

    public FileHandler(String filePath) {
        setFilePath(filePath);
    }

    private void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public FileHandler() {}

    public String open() {
        try {
            content = Files.readString(Paths.get(filePath));
        } catch(IOException e) {
            System.out.println("problem with IO");
            e.printStackTrace();
        }
        return content;
    }

    public void save(String content) {
        try {
            Files.writeString(Paths.get(filePath), content);
        } catch(IOException e) {
            System.out.println("problem with IO");
            e.printStackTrace();
        }
    }
}

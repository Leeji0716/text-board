package org.example.base;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileUtill {
    public static void main(String[] args) {
//        write();
//        read();


    }

    public static void write() {
        Path path = Paths.get("example.txt");
        List<String> lines = Arrays.asList("Line 1", "Line 2", "Line 3");

        try {
            Files.write(path, lines);
            System.out.println("Text was written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            Path path = Paths.get("example.txt");
            List<String> lines = Files.readAllLines(path);
            System.out.println("Read from file:");
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

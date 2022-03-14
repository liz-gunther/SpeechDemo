package com.lizgunther.speechdemo;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\lizgu\\Desktop\\JAVA PROJECTS\\speechdemo\\src\\main\\java\\com\\lizgunther\\speechdemo\\Speech.txt");
        File outputFile = new File("C:\\Users\\lizgu\\Desktop\\JAVA PROJECTS\\speechdemo\\src\\main\\java\\com\\lizgunther\\speechdemo\\output.txt");

        Scanner scan = null;

        HashMap<String, Integer> map = new HashMap<>();

        try {
            scan = new Scanner(file);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                String[] words = line.split("[., ]");
                for (String w : words) {
                    if (!w.isEmpty()) {
                        if (map.containsKey(w)) {
                            int count = map.get(w);
                            count++;
                            map.put(w, count);
                        } else {
                            map.put(w, 1);
                        }
                    }
                }
            }
            boolean fileExists = outputFile.exists();
            boolean createSuccess = false;

            if (!fileExists) {
                createSuccess = outputFile.createNewFile();
            }
            if (fileExists || createSuccess) {

                FileWriter writer = new FileWriter(outputFile, false);
                map.forEach((k, v) -> {
                    try {
                        writer.write(k + "\t" + v + "\n");
                        System.out.println(k + "\t" + v);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert scan != null;
            scan.close();
        }
    }
}

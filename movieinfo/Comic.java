/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieinfo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author gnsdu
 */
public class Comic implements Genre {
    private String title;
    private double rating;
    private String plot;

    public Comic(String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
            String line = reader.readLine();
            String[] parts = line.split(",");
            title = parts[0].substring(4);
            rating = Double.parseDouble(parts[1].substring(4));
            plot = parts[2];
            for (int i = 3; i < parts.length; i++) {
            plot += "," + parts[i];
        }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public String getContent() {
        return "COMIC\n\n제목: " + title + "\n" + "평점: " + rating + "\n" + "줄거리: " + plot + "\n\n";
    }
}
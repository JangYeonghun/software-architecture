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
public class Action implements Genre {
    private String title;
    private String time;
    private String plot;

    public Action(String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
            String line = reader.readLine();
            String[] parts = line.split(",");
            title = parts[0].substring(4);
            time = parts[1].substring(4);
            plot = parts[2].substring(5);
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
        return "ACTION\n\n제목: " + title + "\n" + "시간: " + time + "\n" + "줄거리: " + plot + "\n\n";
    }
}

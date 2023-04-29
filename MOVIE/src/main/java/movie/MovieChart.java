/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 *
 * @author 영화 종류
 */
public class MovieChart extends JFrame {

    MovieChart() {
        setTitle("MovieChart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setContentPane(new Main.MainPanel());
        setSize(700, 500);
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE); // 첫 시작 프레임

        setVisible(true);
    }
}


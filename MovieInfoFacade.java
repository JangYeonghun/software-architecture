/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Facade 패턴을 사용함
 * @author 20193190
 */
public class MovieInfoFacade extends JFrame{
    private Map<String, String> movieFiles = new HashMap<>();
    private JFrame mainFrame;
    private JTextArea textArea;
    private JTextField textField;

    public MovieInfoFacade() {
        // 파일 경로 및 장르 설정
        movieFiles.put("Comic", "movie_comic.txt");
        movieFiles.put("Action", "movie_action.txt");
        movieFiles.put("Anime", "movie_anime.txt");

        createAndShowGUI();
    }

    private void createAndShowGUI() {
        mainFrame = new JFrame("MovieInfoFacade");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 500);
        mainFrame.setLocationRelativeTo(null);

        Container contentPane = mainFrame.getContentPane();
        contentPane.setBackground(Color.WHITE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("수정할 장르:");
        panel.add(label);

        textField = new JTextField(10);
        panel.add(textField);

        JButton button = new JButton("확인");
        button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String genre = textField.getText().trim();
        if (!genre.isEmpty() && movieFiles.containsKey(genre)) {
            loadMovieInfo(genre);
            String filePath = movieFiles.get(genre);
            editMovieInfo(filePath, genre);
        } else {
            JOptionPane.showMessageDialog(mainFrame, "잘못된 장르입니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
});

        panel.add(button);

        contentPane.add(panel, BorderLayout.SOUTH);

        mainFrame.setVisible(true);
        
    }

    private void loadMovieInfo(String genre) {
    StringBuilder builder = new StringBuilder();
    String filePath = movieFiles.get(genre);
    if (filePath != null) {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    textArea.setText(builder.toString());
}


    private void editMovieInfo(String filePath, String genre) {
    String movieInfo = textArea.getText();
    JTextArea movieInfoTextArea = new JTextArea(movieInfo);

    int option = JOptionPane.showConfirmDialog(mainFrame, movieInfoTextArea, "영화 정보 수정",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (option == JOptionPane.OK_OPTION) {
        String editedMovieInfo = movieInfoTextArea.getText();
        saveMovieInfo(filePath, editedMovieInfo);
        JOptionPane.showMessageDialog(mainFrame, "영화 정보가 수정되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
        loadMovieInfo(genre); // 선택한 장르의 표시된 영화 정보를 갱신합니다.
    }
}


    private void saveMovieInfo(String filePath, String movieInfo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(movieInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
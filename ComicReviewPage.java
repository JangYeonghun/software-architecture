/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JList;


/**
 *
 * @author gnsdu
 */
public class ComicReviewPage extends ReviewPage {
    private JFrame frame;
    private JList<String> reviewList;
    private DefaultListModel<String> reviewListModel;
    private File reviewFile;

    /**
    * This class represents the comic review page. It displays reviews for comic genre and allows users to add new reviews.
    * Reviews are loaded and saved to a text file.
    */
    public ComicReviewPage() {
        super("comic", "review_comic.txt");
        // comic 장르에 대한 리뷰를 불러오는 코드
        frame = new JFrame();
        reviewListModel = new DefaultListModel<>();
        reviewList = new JList<>(reviewListModel);
        JScrollPane scrollPane = new JScrollPane(reviewList);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        frame.add(scrollPane, BorderLayout.CENTER);

        // 새로운 리뷰 추가 버튼
        JButton addButton = new JButton("새로운 리뷰 추가");
        addButton.addActionListener((ActionEvent e) -> {
            String reviewText = JOptionPane.showInputDialog("새로운 리뷰를 작성하세요:");
            if (reviewText != null && reviewText.trim().length() > 0) {
                Review review = new Review(reviewText);
                reviews.add(review);
                reviewListModel.addElement(reviewText);
                saveReviewsToFile();
            }
        });
        frame.add(addButton, BorderLayout.SOUTH);
        
        // 파일에서 리뷰 불러오기
        reviewFile = new File("review_comic.txt");
        if (reviewFile.exists()) {
            try (Scanner scanner = new Scanner(reviewFile)) {
                while (scanner.hasNextLine()) {
                    String reviewText = scanner.nextLine();
                    Review review = new Review(reviewText);
                    reviews.add(review);
                    reviewListModel.addElement(reviewText);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void displayReviews() {
        reviewListModel.clear();
        for (Review review : reviews) {
            reviewListModel.addElement(review.getReview());
        }
    }

    @Override
    public void moveToPage() {
        // comic 장르 리뷰 페이지로 이동하는 코드
        displayReviews();
    }
    
    // 파일에 리뷰 저장하기
    private void saveReviewsToFile() {
        try (PrintWriter writer = new PrintWriter(reviewFile)) {
            for (Review review : reviews) {
                writer.println(review.getReview());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
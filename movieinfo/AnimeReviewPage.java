/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieinfo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import static movieinfo.ReviewPage.reviews;

/**
 *
 * @author gnsdu
 */
public class AnimeReviewPage extends ReviewPage {
    private JFrame frame;
    private JList<String> reviewList;
    private DefaultListModel<String> reviewListModel;
    private File reviewFile;

    public AnimeReviewPage() {
        super("comic", "review_anime.txt");
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
                String ratingText = JOptionPane.showInputDialog("평점을 입력하세요:");
                if (ratingText != null && ratingText.trim().length() > 0) {
                    try {
                        double rating = Double.parseDouble(ratingText); // rating 변수 선언 및 값 할당
                        Review review = new Review(reviewText, rating);
                        reviews.add(review);
                        reviewListModel.addElement(reviewText + ", " + rating);
                        saveReviewsToFile();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "올바른 숫자 형식의 평점을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        frame.add(addButton, BorderLayout.SOUTH);

        // 파일에서 리뷰 불러오기
        reviewFile = new File("review_anime.txt");
        loadReviews(); // 리뷰 불러오기 추가
        
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void displayReviews() {
        reviewListModel.clear();
        for (Review review : reviews) {
            String reviewText = review.getReview();
            double rating = review.getRating();
            String displayText = "리뷰: " + reviewText + " 평점: " + rating; // 리뷰와 평점을 함께 표시
            reviewListModel.addElement(displayText);
        }
    }

    @Override
    public void moveToPage() {
        // anime 장르 리뷰 페이지로 이동하는 코드
        displayReviews();
    }

    // 파일에서 리뷰 불러오기
    private void loadReviews() {
        if (reviewFile.exists()) {
            try (Scanner scanner = new Scanner(reviewFile)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String reviewText = extractReviewTextFromLine(line);
                    double rating = extractRatingFromReviewText(line);
                    Review review = new Review(reviewText, rating);
                    reviews.add(review);
                    reviewListModel.addElement(reviewText + ", " + rating);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 파일에 리뷰 저장하기
    private void saveReviewsToFile() {
        try (PrintWriter writer = new PrintWriter(reviewFile)) {
            for (Review review : reviews) {
                writer.println(review.getReview() + "," + review.getRating()); // 리뷰와 평점을 함께 저장
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieinfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gnsdu
 */
public abstract class ReviewPage {
    protected static List<Review> reviews = new ArrayList<>();
    private File reviewFile;
    protected String genre;
    
    public ReviewPage(String genre, String fileName) {
        this.genre = genre;
        this.reviewFile = new File(fileName);
        reviews = loadReviews();
    }

    public abstract void displayReviews();
    
    public void addReview(Review review) {
        reviews.add(review);
        saveReviews();
    }
    
    public void clearReviews() {
        reviews.clear();
        saveReviews();
    }
    
    public abstract void moveToPage();
    
    private List<Review> loadReviews() {
        List<Review> loadedReviews = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(reviewFile), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String reviewText = parts[0].trim();
                    double rating = Double.parseDouble(parts[1].trim());
                    Review review = new Review(reviewText, rating);
                    loadedReviews.add(review);
                }
            }
            reviews = loadedReviews; // 리스트 초기화
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        reviews.clear(); // 기존 리뷰 내용 초기화
        reviews.addAll(loadedReviews); // 새로운 리뷰 내용 추가
        return loadedReviews;
    }
    
    private void saveReviews() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(reviewFile), "UTF-8"));
            for (Review review : reviews) {
                String reviewText = review.getReview();
                double rating = review.getRating();
                writer.write(reviewText + "," + rating + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public String extractReviewTextFromLine(String line) {
        String[] parts = line.split(",");
        if (parts.length > 0) {
            return parts[0].trim();
        }
        return "";
    }

    public double extractRatingFromReviewText(String line) {
        String[] parts = line.split(",");
        if (parts.length > 1) {
            String ratingText = parts[1].trim();
            try {
                return Double.parseDouble(ratingText);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0.0;
    }
}
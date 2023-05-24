///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package movieinfo;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//
///**
// *
// * @author gnsdu
// */
//public class ReviewPageModel {
//    private List<Review> reviews = new ArrayList<>();
//
//    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    public void addReview(Review review) {
//        reviews.add(review);
//        saveReviews();
//    }
//
//    public void clearReviews() {
//        reviews.clear();
//        saveReviews();
//    }
//
//    private void saveReviews() {
//        File reviewFile = new File("reviews.txt");
//        BufferedWriter writer = null;
//        try {
//            writer = new BufferedWriter(new FileWriter(reviewFile));
//            for (Review review : reviews) {
//                String reviewText = review.getReview();
//                double rating = review.getRating();
//                writer.write(reviewText + "," + rating + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    private List<Review> loadReviews() {
//        List<Review> loadedReviews = new ArrayList<>();
//        File reviewFile = new File("reviews.txt");
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader(reviewFile));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length == 2) {
//                    String reviewText = parts[0].trim();
//                    double rating = Double.parseDouble(parts[1].trim());
//                    Review review = new Review(reviewText, rating);
//                    loadedReviews.add(review);
//                }
//            }
//            reviews.clear();
//            reviews.addAll(loadedReviews);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return loadedReviews;
//    }
//}
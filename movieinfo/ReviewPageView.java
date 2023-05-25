///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package movieinfo;
//
//import java.util.List;
//
///**
// *
// * @author gnsdu
// */
//// View: ReviewPageView 클래스는 뷰를 나타내며 리뷰 페이지와 사용자 상호작용의 시각적 표현을 담당합니다.
//public class ReviewPageView {
//    private ReviewPageController controller;
//
//    public void setController(ReviewPageController controller) {
//        this.controller = controller;
//    }
//
//    public void displayReviews() {
//        List<Review> reviews = controller.getReviews();
//        // 리뷰를 표시하는 로직을 구현합니다.
//        for (Review review : reviews) {
//            System.out.println("리뷰: " + review.getReview() + ", 평점: " + review.getRating());
//        }
//    }
//
//    public void handleAddReviewButtonClicked(String reviewText, double rating) {
//        Review review = new Review(reviewText, rating);
//        controller.addReview(review);
//    }
//
//    public void handleClearReviewsButtonClicked() {
//        controller.clearReviews();
//    }
//}
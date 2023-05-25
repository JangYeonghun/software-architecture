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
//// Controller: ReviewPageController 클래스는 컨트롤러 역할을 합니다.
//public class ReviewPageController {
//    private ReviewPageView view;
//    private ReviewPageModel model;
//
//    public ReviewPageController(ReviewPageView view, ReviewPageModel model) {
//        this.view = view;
//        this.model = model;
//    }
//    
//    public void setView(ReviewPageView view) {
//        this.view = view;
//    }
//    
//    public void setModel(ReviewPageModel model) {
//        this.model = model;
//    }
//
//    public void initialize() {
//        view.setController(this);
//        view.displayReviews();
//    }
//
//    public void addReview(Review review) {
//        model.addReview(review);
//        view.displayReviews();
//    }
//
//    public void clearReviews() {
//        model.clearReviews();
//        view.displayReviews();
//    }
//    
//    public List<Review> getReviews() {
//        return (List<Review>) model.getReviews();
//    }
//}
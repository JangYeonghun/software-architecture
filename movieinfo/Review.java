/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieinfo;

/**
 *
 * @author gnsdu
 */
public class Review {
    private String review;
    private double rating;

    public Review(String review, double rating) {
        this.review = review;
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public double getRating() {
        return rating;
    }
}
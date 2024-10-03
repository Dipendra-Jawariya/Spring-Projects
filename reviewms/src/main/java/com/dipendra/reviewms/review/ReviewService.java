package com.dipendra.reviewms.review;


import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId,Review review);
    Review getReview(Long reviewId);
    boolean deleteReview(Long reviewId);
    boolean updateReview(Long reviewId, Review review);
}

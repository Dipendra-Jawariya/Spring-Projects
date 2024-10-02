package com.dipendra.jobApp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if(reviews == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> Review(@PathVariable Long companyId,
                                         @RequestBody Review review) {
        boolean isReviewSaved = reviewService.addReview(companyId,review);
        if(isReviewSaved) {
            return new ResponseEntity<>("Review Added sucessfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Review Not Added", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,
                                            @PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReview(companyId,reviewId),
                                    HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review) {
        boolean isReviewUpdated = reviewService.updateReview(companyId,reviewId,review);
        if(isReviewUpdated) {
            return new ResponseEntity<>("Review Updated sucessfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Review Cannot be Updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewService.deleteReview(companyId,reviewId);
        if(isReviewDeleted) {
            return new ResponseEntity<>("Review Deleted sucessfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Review Cannot be Updated", HttpStatus.NOT_FOUND);
    }

}

package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.service.ReviewService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/review")
public class ReviewController {
    private final ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("/getThesisByReviewerId")
    public Result getThesisByReviewerId(@RequestParam("account") String teacherId){
        return Result.success(reviewService.getThesisByReviewerId(teacherId));
    }
}

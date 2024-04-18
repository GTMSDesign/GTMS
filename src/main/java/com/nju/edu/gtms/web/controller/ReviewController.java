package com.nju.edu.gtms.web.controller;

import com.nju.edu.gtms.model.vo.ReviewResultVO;
import com.nju.edu.gtms.service.ReviewService;
import com.nju.edu.gtms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getReviewByThesisId")
    public Result getReviewByThesisId(@RequestParam String thesisId){
        return Result.success(reviewService.getReviewByThesisId(thesisId));
    }

    @GetMapping("/getReviewConclusionByTeacherId")
    public Result getReviewConclusionByTeacherId(@RequestParam String teacherId){
        return Result.success(reviewService.getReviewConclusionByTeacherId(teacherId));
    }


    @GetMapping("/getReviewRules")
    public Result getReviewRules(){
        return Result.success(reviewService.getReviewRules());
    }

    @PostMapping("/submitReview")
    public Result submitReview(@RequestBody ReviewResultVO resultVO){
        reviewService.submitReview(resultVO);
        return Result.success();
    }
}

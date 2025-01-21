package com.example.Book.Store.Application.controller;

import com.example.Book.Store.Application.requestdto.FeedbackRequest;
import com.example.Book.Store.Application.responsedto.FeedbackResponse;
import com.example.Book.Store.Application.serviceimpl.FeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedbackController {

    private final FeedbackServiceImpl feedbackService;

    public FeedbackController(FeedbackServiceImpl feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/send-feedback")
    public ResponseEntity<FeedbackResponse> sendFeedback(@RequestBody FeedbackRequest feedbackRequest)
    {
        FeedbackResponse feedbackResponse=feedbackService.addFeedback(feedbackRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackResponse);
    }

    @GetMapping("get-allFeedback")
    public ResponseEntity<List<FeedbackResponse>> getAllFeedback()
    {
        List<FeedbackResponse> feedbackResponses = feedbackService.getAllFeedback();
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackResponses);
    }

    @GetMapping("get-feedback/{userId}")
    public ResponseEntity<List<FeedbackResponse>> getFeedbackByUserId(@PathVariable Long userId) {
        List<FeedbackResponse> feedbackResponses = feedbackService.getFeedbackByUserId(userId);
        return ResponseEntity.ok(feedbackResponses);
    }
}

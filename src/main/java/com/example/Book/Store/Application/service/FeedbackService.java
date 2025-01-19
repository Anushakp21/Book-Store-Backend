package com.example.Book.Store.Application.service;

import com.example.Book.Store.Application.requestdto.FeedbackRequest;
import com.example.Book.Store.Application.responsedto.FeedbackResponse;

import java.util.List;

public interface FeedbackService {
    FeedbackResponse addFeedback(FeedbackRequest feedbackRequest);
    List<FeedbackResponse> getAllFeedback();
    List<FeedbackResponse> getFeedbackByUserId(Long userId);
}

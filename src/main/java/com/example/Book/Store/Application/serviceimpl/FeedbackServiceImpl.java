package com.example.Book.Store.Application.serviceimpl;

import com.example.Book.Store.Application.Handler.BookNotFoundByIdException;
import com.example.Book.Store.Application.Handler.UserNotFoundByIdException;
import com.example.Book.Store.Application.entity.Book;
import com.example.Book.Store.Application.entity.Feedback;
import com.example.Book.Store.Application.entity.User;
import com.example.Book.Store.Application.repository.BookRepository;
import com.example.Book.Store.Application.repository.FeedbackRepository;
import com.example.Book.Store.Application.repository.UserRepository;
import com.example.Book.Store.Application.requestdto.FeedbackRequest;
import com.example.Book.Store.Application.responsedto.FeedbackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public FeedbackResponse addFeedback(FeedbackRequest feedbackRequest) {
        User user=userRepository.findById(feedbackRequest.getUserId()).orElseThrow(()-> new UserNotFoundByIdException("User Not Found"));
        Book book=bookRepository.findById(feedbackRequest.getBookId()).orElseThrow(()-> new BookNotFoundByIdException("Book not Found"));

        Feedback feedback=new Feedback();
        feedback.setDescription(feedbackRequest.getDescription());
        feedback.setRating(feedbackRequest.getRating());
        feedback.setUser(user);
        feedback.setBook(book);

        feedback =feedbackRepository.save(feedback);

        FeedbackResponse feedbackResponse = new FeedbackResponse();
        feedbackResponse.setDescription(feedback.getDescription());
        feedbackResponse.setRating(feedback.getRating());

        return feedbackResponse;
    }

    public List<FeedbackResponse> getAllFeedback() {
        List<Feedback> feedbackList = feedbackRepository.findAll();

        return feedbackList.stream().map(feedback -> {
            FeedbackResponse response = new FeedbackResponse();
            response.setDescription(feedback.getDescription());
            response.setRating(feedback.getRating());
            return response;
        }).toList();
    }

    public List<FeedbackResponse> getFeedbackByUserId(Long userId) {
        List<Feedback> feedbackList = feedbackRepository.findByUserUserId(userId);

        return feedbackList.stream().map(feedback -> {
            FeedbackResponse response = new FeedbackResponse();
            response.setDescription(feedback.getDescription());
            response.setRating(feedback.getRating());
            return response;
        }).toList();
    }
}

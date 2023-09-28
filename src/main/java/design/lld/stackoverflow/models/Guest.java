package design.lld.stackoverflow.models;

import design.lld.stackoverflow.StackOverflowService;

import java.util.List;

public class Guest {
    public List<Question> searchQuestions(StackOverflowService stackOverflowService) {
        return stackOverflowService.getAllQuestions();
    }

    public Question getQuestion(StackOverflowService stackOverflowService, String questionId) {
        return stackOverflowService.getQuestion(questionId);
    }
}

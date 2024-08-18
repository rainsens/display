package com.rainsen.display.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AnswerUpdateRequest {

    @NotNull
    private Long id;
    @NotNull
    private Long surveyId;
    @NotNull
    private Long questionId;
    @Size(min = 3, max = 512)
    private String answer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswerUpdateRequest{" +
                "id=" + id +
                ", surveyId=" + surveyId +
                ", questionId=" + questionId +
                ", answer='" + answer + '\'' +
                '}';
    }
}

package com.rainsen.display.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class QuestionUpdateRequest {

    @NotNull
    private Long id;

    @NotNull
    private Long surveyId;

    @Size(min = 3, max = 256)
    private String question;

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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuestionUpdateRequest{" +
                "id=" + id +
                ", surveyId=" + surveyId +
                ", question='" + question + '\'' +
                '}';
    }
}

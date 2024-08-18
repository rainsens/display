package com.rainsen.display.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class QuestionCreateRequest {

    @NotNull
    private Long surveyId;

    @NotBlank
    @Size(min = 3, max = 256)
    private String question;

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
        return "QuestionCreateRequest{" +
                "surveyId=" + surveyId +
                ", question='" + question + '\'' +
                '}';
    }
}

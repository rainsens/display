package com.rainsen.display.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AnswerCreateRequest {

    @NotNull
    private Long surveyId;
    @NotNull
    private Long questionId;
    @NotBlank
    @Size(min = 3, max = 512)
    private String answer;

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
        return "AnswerCreateRequest{" +
                "surveyId=" + surveyId +
                ", questionId=" + questionId +
                ", answer='" + answer + '\'' +
                '}';
    }
}

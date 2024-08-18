package com.rainsen.display.model.resource;

public class QuestionResource {

    private Long id;
    private String question;

    private SurveyResource survey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public SurveyResource getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyResource survey) {
        this.survey = survey;
    }
}

package com.rainsen.display.model.resource;

public class AnswerResource {

    private Long id;
    private String answer;

    private UserResource user;
    private SurveyResource survey;
    private QuestionResource question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public UserResource getUser() {
        return user;
    }

    public void setUser(UserResource user) {
        this.user = user;
    }

    public SurveyResource getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyResource survey) {
        this.survey = survey;
    }

    public QuestionResource getQuestion() {
        return question;
    }

    public void setQuestion(QuestionResource question) {
        this.question = question;
    }
}

package com.rainsen.display.model.entity;

import com.rainsen.display.model.resource.AnswerResource;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Survey survey;

    @ManyToOne(fetch = FetchType.EAGER)
    private Question question;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    public void setUserId(Long userId) {
        this.user = new User();
        this.user.setId(userId);
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Long getSurveyId() {
        return survey != null ? survey.getId() : null;
    }

    public void setSurveyId(Long surveyId) {
        this.survey = new Survey();
        this.survey.setId(surveyId);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getQuestionId() {
        return question != null ? question.getId() : null;
    }

    public void setQuestionId(Long questionId) {
        this.question = new Question();
        this.question.setId(questionId);
    }

    public AnswerResource toResource() {
        AnswerResource resource = new AnswerResource();
        BeanUtils.copyProperties(this, resource);
        return resource;
    }
}

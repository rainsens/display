package com.rainsen.display.model.entity;

import com.rainsen.display.model.resource.QuestionResource;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;

    @ManyToOne(fetch = FetchType.EAGER)
    private Survey survey;

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

    public QuestionResource toResource() {
        QuestionResource resource = new QuestionResource();
        BeanUtils.copyProperties(this, resource);
        return resource;
    }
}

package com.rainsen.display.model.repository;

import com.rainsen.display.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    boolean existsById(@NonNull Long id);

    boolean existsBySurvey_IdAndQuestion_IdAndAnswer(@NonNull Long surveyId, Long questionId, String answer);

    void deleteAllBySurvey_Id(Long surveyId);

    void deleteAllByQuestion_Id(Long questionId);
}

package com.rainsen.display.model.repository;

import com.rainsen.display.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    boolean existsById(@NonNull Long id);

    boolean existsBySurvey_IdAndQuestion(@NonNull Long surveyId, @NonNull String question);

    void deleteAllBySurvey_Id(Long surveyId);

    List<Question> findAllBySurvey_Id(Long surveyId);
}

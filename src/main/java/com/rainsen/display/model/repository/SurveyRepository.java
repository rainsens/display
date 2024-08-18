package com.rainsen.display.model.repository;

import com.rainsen.display.model.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    boolean existsById(@NonNull Long id);

    boolean existsByTitle(String title);
}

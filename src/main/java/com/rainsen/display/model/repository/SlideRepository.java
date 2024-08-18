package com.rainsen.display.model.repository;

import com.rainsen.display.model.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface SlideRepository extends JpaRepository<Slide, Long> {

    boolean existsById(@NonNull Long id);

    void deleteAllByProject_Id(Long projectId);
}

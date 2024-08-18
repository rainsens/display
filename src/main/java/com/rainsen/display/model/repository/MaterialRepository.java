package com.rainsen.display.model.repository;

import com.rainsen.display.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    boolean existsById(@NonNull Long id);

    void deleteAllByProject_Id(Long projectId);
}

package com.rainsen.display.model.repository;

import com.rainsen.display.model.entity.Permit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface PermitRepository extends JpaRepository<Permit, Long> {

    boolean existsById(@NonNull Long id);

    boolean existsByProject_IdAndUser_Id(@NonNull Long projectId, @NonNull Long userId);

    void deleteAllByProject_Id(Long projectId);
}

package com.rainsen.display.model.repository;

import com.rainsen.display.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsById(@NonNull Long id);

    boolean existsByProject_IdAndUser_Id(@NonNull Long projectId, @NonNull Long userId);

    void deleteAllByProject_Id(Long projectId);
}

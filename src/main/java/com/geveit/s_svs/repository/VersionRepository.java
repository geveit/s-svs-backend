package com.geveit.s_svs.repository;

import com.geveit.s_svs.entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long> {
    @Query("SELECT MAX(v.versionNumber) FROM Version v WHERE v.project.id = :projectId")
    Integer findLastVersionNumberByProjectId(@Param("projectId") Long projectId);

    Optional<Version> findByIdAndProjectId(Long id, Long projectId);
}

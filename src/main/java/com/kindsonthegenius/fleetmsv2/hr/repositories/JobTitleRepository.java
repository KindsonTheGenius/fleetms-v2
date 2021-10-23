package com.kindsonthegenius.fleetmsv2.hr.repositories;

import com.kindsonthegenius.fleetapp_v2.hr.models.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Integer> {
}

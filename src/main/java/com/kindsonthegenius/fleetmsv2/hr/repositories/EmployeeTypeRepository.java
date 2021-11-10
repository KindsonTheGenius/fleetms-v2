package com.kindsonthegenius.fleetmsv2.hr.repositories;

import com.kindsonthegenius.fleetmsv2.hr.models.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Integer> {

}

package com.kindsonthegenius.fleetmsv2.hr.repositories;

import com.kindsonthegenius.fleetmsv2.hr.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	public Employee findByUsername(String un);
	
	@Query(value="select * from Employee e where e.firstname like %:keyword% or e.lastname like %:keyword%", nativeQuery=true)
	List<Employee> findByKeyword(@Param("keyword") String keyword);
}

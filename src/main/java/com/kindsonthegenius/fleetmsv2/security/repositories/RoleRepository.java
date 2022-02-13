package com.kindsonthegenius.fleetmsv2.security.repositories;

import com.kindsonthegenius.fleetmsv2.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}

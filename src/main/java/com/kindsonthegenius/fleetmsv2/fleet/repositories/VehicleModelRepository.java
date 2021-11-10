package com.kindsonthegenius.fleetmsv2.fleet.repositories;

import com.kindsonthegenius.fleetmsv2.fleet.models.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Integer> {

}

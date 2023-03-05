package com.kindsonthegenius.fleetmsv2.fleet.models;

import com.kindsonthegenius.fleetmsv2.parameters.models.CommonObject;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class VehicleModel extends CommonObject {

}

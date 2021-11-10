package com.kindsonthegenius.fleetmsv2.fleet.models;

import com.kindsonthegenius.fleetmsv2.parameters.models.CommonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class VehicleModel extends CommonObject {

}

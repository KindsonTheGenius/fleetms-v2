package com.kindsonthegenius.fleetmsv2.fleet.models;

import com.kindsonthegenius.fleetmsv2.parameters.models.Client;
import com.kindsonthegenius.fleetmsv2.parameters.models.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Auditable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleHire {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="vehicleid", insertable=false, updatable=false)
	private Vehicle vehicle;
	private Integer vehicleid;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOut;
	
	private String timeOut;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateIn;
	
	private String timeIn;
	
	@ManyToOne
	@JoinColumn(name="clientid", insertable=false, updatable=false)
	private Client client;
	private Integer clientid;
	
	@ManyToOne
	@JoinColumn(name="locationid", insertable=false, updatable=false)
	private Location location;
	private Integer locationid;
	
	private String price;
	
	private String remarks;
}

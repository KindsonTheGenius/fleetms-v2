package com.kindsonthegenius.fleetmsv2.helpdesk.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kindsonthegenius.fleetmsv2.hr.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ticket {	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String subject;
	private String details;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date openDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date closeDate;
	
	@ManyToOne
	@JoinColumn(name="ticketstatusid", insertable=false, updatable=false)	
	private TicketStatus ticketStatus;
	private Integer ticketstatusid;
	
	@ManyToOne
	@JoinColumn(name="employeeid", insertable=false, updatable=false)
	private Employee raisedBy;
	private Integer employeeid;

	@ManyToOne
	@JoinColumn(name="employeeid", insertable=false, updatable=false)
	private Employee asignedTo;

	private String remarks;	
}

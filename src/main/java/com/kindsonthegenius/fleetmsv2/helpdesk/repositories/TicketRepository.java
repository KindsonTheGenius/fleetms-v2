package com.kindsonthegenius.fleetmsv2.helpdesk.repositories;

import com.kindsonthegenius.fleetapp_v2.helpdesk.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}

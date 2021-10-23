package com.kindsonthegenius.fleetmsv2.parameters.repositories;

import com.kindsonthegenius.fleetapp_v2.parameters.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}

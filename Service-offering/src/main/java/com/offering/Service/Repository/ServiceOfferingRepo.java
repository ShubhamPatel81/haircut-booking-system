package com.offering.Service.Repository;

import com.offering.Service.model.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ServiceOfferingRepo extends JpaRepository<ServiceOffering,Long> {

    Set<ServiceOffering> findBySaloonId (Long saloonId);




}

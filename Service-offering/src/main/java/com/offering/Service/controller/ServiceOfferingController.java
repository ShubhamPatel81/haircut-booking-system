package com.offering.Service.controller;

import com.offering.Service.model.ServiceOffering;
import com.offering.Service.service.ServiceOfferingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/service-offering")
public class ServiceOfferingController {

   final private ServiceOfferingService serviceOfferingService;
    public ServiceOfferingController(ServiceOfferingService serviceOfferingService) {
        this.serviceOfferingService = serviceOfferingService;
    }

    @GetMapping("/saloon/{saloonId}")
    public ResponseEntity<Set<ServiceOffering>> getServiceOfferingBySaloonId(@PathVariable Long saloonId, @RequestParam(required = false) Long categoryId){
        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getAllServiceBySaloonId(saloonId,categoryId);
        return ResponseEntity.ok(serviceOfferings);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServiceOfferingById(@PathVariable Long id) throws Exception {
        ServiceOffering serviceOfferings = serviceOfferingService.getServiceById(id);
        return ResponseEntity.ok(serviceOfferings);
    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServiceOfferingByIds(@PathVariable Set<Long> ids){
        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getServicesByIds(ids);
        return ResponseEntity.ok(serviceOfferings);
    }

}

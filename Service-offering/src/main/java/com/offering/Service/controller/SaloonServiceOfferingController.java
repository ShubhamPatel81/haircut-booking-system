package com.offering.Service.controller;

import com.offering.Service.dto.CategoryDto;
import com.offering.Service.dto.SaloonDto;
import com.offering.Service.dto.ServiceDto;
import com.offering.Service.model.ServiceOffering;
import com.offering.Service.service.ServiceOfferingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/service-offering/saloon-owner")
public class SaloonServiceOfferingController {

   private  final ServiceOfferingService serviceOfferingService;

    public SaloonServiceOfferingController(ServiceOfferingService serviceOfferingService) {
        this.serviceOfferingService = serviceOfferingService;
    }

    @PostMapping
    public ResponseEntity< ServiceOffering> createService(
            @RequestBody ServiceDto serviceDto){

        SaloonDto saloonDto = new SaloonDto();
        saloonDto.setId(1L);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(serviceDto.getCategory());
        ServiceOffering serviceOfferings = serviceOfferingService.createService(saloonDto,serviceDto,categoryDto);
        return ResponseEntity.ok(serviceOfferings);
    }


    @PatchMapping("/{serviceId}")
    public ResponseEntity< ServiceOffering> updateService(
            @PathVariable Long serviceId, @RequestBody ServiceOffering serviceOffering) throws Exception {
            ServiceOffering serviceOfferings = serviceOfferingService.updateService(serviceId,serviceOffering);
            return ResponseEntity.ok(serviceOfferings);
    }



}

package com.offering.Service.service;

import com.offering.Service.dto.CategoryDto;
import com.offering.Service.dto.SaloonDto;
import com.offering.Service.dto.ServiceDto;
import com.offering.Service.model.ServiceOffering;

import java.util.List;
import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService (SaloonDto saloonDto, ServiceDto serviceDto, CategoryDto categoryDto) ;

    ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception;

    Set<ServiceOffering> getAllServiceBySaloonId(Long saloonId, Long categoryId);

    Set<ServiceOffering> getServicesByIds(Set<Long> ids);

    ServiceOffering getServiceById(Long id) throws Exception;


}

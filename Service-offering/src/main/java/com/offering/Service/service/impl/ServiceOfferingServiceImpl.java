package com.offering.Service.service.impl;

import com.offering.Service.Repository.ServiceOfferingRepo;
import com.offering.Service.dto.CategoryDto;
import com.offering.Service.dto.SaloonDto;
import com.offering.Service.dto.ServiceDto;
import com.offering.Service.model.ServiceOffering;
import com.offering.Service.service.ServiceOfferingService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

   final private ServiceOfferingRepo serviceOfferingRepo;

    public ServiceOfferingServiceImpl(ServiceOfferingRepo serviceOfferingRepo) {
        this.serviceOfferingRepo = serviceOfferingRepo;
    }


    @Override
    public ServiceOffering createService(SaloonDto saloonDto, ServiceDto serviceDto, CategoryDto categoryDto) {
       ServiceOffering serviceOffering = new ServiceOffering();
       serviceOffering.setImage(serviceDto.getImage());
       serviceOffering.setSaloonId(serviceDto.getSaloonId());
       serviceOffering.setServiceName(serviceDto.getServiceName());
       serviceOffering.setDescription(serviceDto.getDescription() );
       serviceOffering.setPrice(serviceDto.getPrice());
       serviceOffering.setDuration(serviceDto.getDuration());
       serviceOffering.setCategoryId(categoryDto.getId());

        return serviceOfferingRepo.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception {
        ServiceOffering serviceOffering = serviceOfferingRepo.findById(serviceId).orElse(null);
        if (serviceOffering ==null){
            throw new Exception("Service Not Exist in Service Offering service with id "+ serviceId);
        }
        serviceOffering.setImage(service.getImage());
        serviceOffering.setServiceName(service.getServiceName());
        serviceOffering.setDescription(service.getDescription() );
        serviceOffering.setPrice(service.getPrice());
        serviceOffering.setDuration(service.getDuration());

        return serviceOfferingRepo.save(serviceOffering);
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySaloonId(Long saloonId, Long categoryId) {
            Set<ServiceOffering> services = serviceOfferingRepo.findBySaloonId(saloonId);
            if (categoryId != null){
                services=services.stream().filter((service)->service.getCategoryId()!= null &&  service.getCategoryId() == categoryId).collect(Collectors.toSet());
            }
        return services;
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> ids) {
        List<ServiceOffering>services = serviceOfferingRepo.findAllById(ids);
        return new HashSet<>(services);
    }

    @Override
    public ServiceOffering getServiceById(Long id) throws Exception {
       ServiceOffering serviceOffering =serviceOfferingRepo.findById(id).orElse(null);
       if (serviceOffering == null){
           throw  new Exception("Service Not exit in serviceOffering class with  ID "+id);
       }
    return serviceOffering;
    }
}

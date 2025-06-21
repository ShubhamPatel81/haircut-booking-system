package com.saloon.service.impl;

import com.saloon.Repository.SaloonRepository;
import com.saloon.dto.SaloonDto;
import com.saloon.dto.UserDTO;
import com.saloon.modal.Saloon;
import com.saloon.service.SaloonService;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaloonServiceImpl implements SaloonService {


    private final SaloonRepository saloonRepository;

    @Autowired
    public SaloonServiceImpl(SaloonRepository saloonRepository) {
        this.saloonRepository = saloonRepository;
    }

    @Override
    public Saloon createSaloon(SaloonDto saloonDTO, UserDTO userDTO) {
         Saloon saloon= new Saloon();
         saloon.setName(saloonDTO.getName());
         saloon.setAddress(saloonDTO.getAddress());
         saloon.setEmail(saloonDTO.getEmail());
         saloon.setCity(saloonDTO.getCity());
         saloon.setOwnerId(userDTO.getId());
         saloon.setOpenTime(saloonDTO.getOpenTime());
         saloon.setCloseTime(saloonDTO.getCloseTime());
         saloon.setPhoneNumber(saloonDTO.getPhoneNumber());
         saloon.setImages(saloonDTO.getImages());

         return saloonRepository.save(saloon);

    }

    @Override
    public Saloon updateSaloon(SaloonDto saloonDto, UserDTO userDTO, Long saloonId) throws Exception {
        Saloon  existingSaloon = saloonRepository.findById(saloonId).orElse(null);
      if (existingSaloon!=null && saloonDto.getOwnerId().equals(userDTO.getId())){

            existingSaloon.setName(saloonDto.getName());
            existingSaloon.setEmail(saloonDto.getEmail());
            existingSaloon.setAddress(saloonDto.getAddress());
            existingSaloon.setCity(saloonDto.getCity());
            existingSaloon.setOwnerId(saloonDto.getOwnerId());
            existingSaloon.setImages(saloonDto.getImages());
            existingSaloon.setCloseTime(saloonDto.getCloseTime());
            existingSaloon.setPhoneNumber(saloonDto.getPhoneNumber());
            existingSaloon.setOwnerId(userDTO.getId());

            return   saloonRepository.save(existingSaloon);


      }
      throw new Exception("Saloon not find by id ");
    }

    @Override
    public List<Saloon> getAllSaloon() {
         return saloonRepository.findAll();
    }

    @Override
    public Saloon getSaloonById(Long saloonId) throws Exception {
        Saloon saloon=  saloonRepository.findById(saloonId).orElse(null);
        if (saloon ==null)throw new Exception("Saloon By this is not exits");
        return saloon;
    }

    @Override
    public Saloon getSaloonByOwnerId(Long ownerId) {
        return saloonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Saloon> searchSaloonByCityName(String cityName) {
        return saloonRepository.searchSaloon(cityName);
    }
}

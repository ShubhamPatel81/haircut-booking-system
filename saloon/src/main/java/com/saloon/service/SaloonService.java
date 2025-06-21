package com.saloon.service;


import com.saloon.dto.SaloonDto;
import com.saloon.dto.UserDTO;
import com.saloon.modal.Saloon;
import org.apache.catalina.User;

import java.util.List;

public interface SaloonService {

    Saloon createSaloon(SaloonDto saloonDTO, UserDTO userDTO);

    Saloon updateSaloon(SaloonDto saloonDto, UserDTO userDTO, Long saloonId) throws Exception;

    List<Saloon> getAllSaloon();

    Saloon getSaloonById(Long saloonId) throws Exception;

    Saloon getSaloonByOwnerId(Long ownerId);

    List<Saloon> searchSaloonByCityName(String  cityName);

}

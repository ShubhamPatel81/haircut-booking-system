package com.saloon.mapper;

import com.saloon.dto.SaloonDto;
import com.saloon.modal.Saloon;

public class SaloonMapper {

    public static SaloonDto mapToDTO(Saloon saloon){
        SaloonDto saloonDto = new SaloonDto();
        saloonDto.setId(saloon.getId());

        saloonDto.setName(saloon.getName());
        saloonDto.setAddress(saloon.getAddress());
        saloonDto.setCity(saloon.getCity());
        saloonDto.setEmail(saloon.getEmail());
        saloonDto.setImages(saloon.getImages());
        saloonDto.setCloseTime(saloon.getCloseTime());
        saloonDto.setOpenTime(saloon.getOpenTime());
        saloonDto.setPhoneNumber(saloon.getPhoneNumber());
        saloonDto.setOwnerId(saloon.getOwnerId());

        return saloonDto;
    }
}

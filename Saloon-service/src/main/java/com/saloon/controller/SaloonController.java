package com.saloon.controller;

import com.saloon.dto.SaloonDto;
import com.saloon.dto.UserDTO;
import com.saloon.mapper.SaloonMapper;
import com.saloon.modal.Saloon;
import com.saloon.service.SaloonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saloon")
public class SaloonController {

    private final SaloonService saloonService;

    @Autowired
    public SaloonController(SaloonService saloonService) {
        this.saloonService = saloonService;
    }

    @PostMapping
    public ResponseEntity<SaloonDto> createSaloon(@RequestBody SaloonDto saloonDto){
        UserDTO userDTO  = new UserDTO();
        userDTO.setId(1L);
        Saloon saloon = saloonService.createSaloon(saloonDto,userDTO);
        SaloonDto saloonDto1 = SaloonMapper.mapToDTO(saloon);
        return  ResponseEntity.ok(saloonDto1);
    }

    @PatchMapping("/{saloonId}")
    public ResponseEntity<SaloonDto> updateSaloon(@PathVariable("saloonId")Long saloonId, @RequestBody SaloonDto saloonDto) throws Exception {
        UserDTO userDTO  = new UserDTO();
        userDTO.setId(1L);
        Saloon saloon = saloonService.updateSaloon(saloonDto,userDTO,saloonId);
        SaloonDto saloonDto1 = SaloonMapper.mapToDTO(saloon);
        return  ResponseEntity.ok(saloonDto1);
    }
    @GetMapping
    public ResponseEntity<List<SaloonDto>> getAllSaloon( ){
        List<Saloon> saloon = saloonService.getAllSaloon();
       List<SaloonDto> saloonDto=saloon.stream().map((saloonE)-> {
                   SaloonDto saloonDto1=SaloonMapper.mapToDTO(saloonE);
                   return saloonDto1;
               }
               ).toList();

       return ResponseEntity.ok(saloonDto);
    }


    @GetMapping("/{saloonId}")
    public ResponseEntity<SaloonDto> getSaloonById( @PathVariable Long saloonId) throws Exception {
        Saloon saloon = saloonService.getSaloonById(saloonId);
        SaloonDto saloonDto= SaloonMapper.mapToDTO(saloon);

        return ResponseEntity.ok(saloonDto);
    }


    //http://localhost:8081/api/saloon/search?city=banglore
    @GetMapping("/search")
    public ResponseEntity<List<SaloonDto>> searchSaloon(@RequestParam String city ){
        List<Saloon> saloon = saloonService.searchSaloonByCityName(city);
        List<SaloonDto> saloonDto=saloon.stream().map((saloonE)-> {
                    SaloonDto saloonDto1=SaloonMapper.mapToDTO(saloonE);
                    return saloonDto1;
                }
        ).toList();

        return ResponseEntity.ok(saloonDto);
    }


    @GetMapping("/owner/{ownerId}")
    public ResponseEntity <SaloonDto> getSaloonByOwnerId( @PathVariable Long ownerId){
        UserDTO userDTO  = new UserDTO();
        userDTO.setId(1L);
        Saloon saloon = saloonService.getSaloonByOwnerId(userDTO.getId());

        SaloonDto saloonDto = SaloonMapper.mapToDTO(saloon);
        return ResponseEntity.ok(saloonDto);
    }
}

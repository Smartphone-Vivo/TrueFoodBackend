package dev.TrueFood.controllers;

import dev.TrueFood.entity.Adverticement;
import dev.TrueFood.repositories.AdverticementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/general")
@RequiredArgsConstructor
public class GeneralController {

    private final AdverticementRepository adverticementRepository;


    @GetMapping("alladverticement")
    public List<Adverticement> getAllOrder() {
        return adverticementRepository.findAll();
    }

}

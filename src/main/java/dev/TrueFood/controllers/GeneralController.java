package dev.TrueFood.controllers;

import dev.TrueFood.entity.Adverticement;
import dev.TrueFood.repositories.AdverticementRepository;
import dev.TrueFood.services.AdverticementService;
import dev.TrueFood.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/general")
@RequiredArgsConstructor
public class GeneralController {

    private final AdverticementService adverticementService;


    @GetMapping("adverticement/{page}/{size}")
    public Page<Adverticement> getAllAdverticements(
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size")int size,

            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort) {

        PageRequest pageRequest = PageUtils.createPageRequest(page, size, sort);

        return adverticementService.getAdverticementsWithPagination(name, pageRequest);
    }

    @PostMapping("adverticement")
    public void addAdverticement(@RequestBody Adverticement adverticement) {
        adverticementService.addAdverticement(adverticement);
    }


}

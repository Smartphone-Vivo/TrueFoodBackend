package dev.TrueFood.repositories;

import dev.TrueFood.entity.Adverticement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {

    @Autowired
    private AdverticementRepository adverticementRepository;

    public void initial() {
        Adverticement adverticement1 = new Adverticement(
                null,
                "Жареный суп со свиными кишками",
                "опасно",
                123L,
                123,
                "location1",
                123L,
                "itemType1",
                "createdAt1",
                true
        );
        adverticementRepository.save(adverticement1);

        Adverticement adverticement2 = new Adverticement(
                null,
                "Жареный суп со свиными кишками2",
                "опасно",
                123L,
                123,
                "location1",
                123L,
                "itemType1",
                "createdAt1",
                true
        );
        adverticementRepository.save(adverticement2);

        Adverticement adverticement3 = new Adverticement(
                null,
                "Жареный суп со свиными кишками3",
                "опасно",
                123L,
                123,
                "location1",
                123L,
                "itemType1",
                "createdAt1",
                true
        );
        adverticementRepository.save(adverticement3);
    }



}

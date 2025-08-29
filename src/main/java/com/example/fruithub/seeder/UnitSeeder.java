package com.example.fruithub.seeder;

import com.example.fruithub.entity.Status;
import com.example.fruithub.entity.Unit;
import com.example.fruithub.repository.StatusRepository;
import com.example.fruithub.repository.UnitRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitSeeder {

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    StatusRepository statusRepository;


    @PostConstruct
    public void seedUnit() {

        if (unitRepository.count() == 0) {
            Status activeStatus = statusRepository.findByName("Active");
            Unit kgUnit = Unit.builder()
                    .name("Kilogram")
                    .code("kg")
                    .status(activeStatus)
                    .build();

            Unit gramUnit = Unit.builder()
                    .name("Gram")
                    .code("g")
                    .status(activeStatus)
                    .build();

            Unit pcUnit = Unit.builder()
                    .name("Piece")
                    .code("pc")
                    .status(activeStatus)
                    .build();

            Unit bagUnit = Unit.builder()
                    .name("Bag")
                    .code("bag")
                    .status(activeStatus)
                    .build();

            unitRepository.save(kgUnit);
            unitRepository.save(gramUnit);
            unitRepository.save(pcUnit);
            unitRepository.save(bagUnit);
        }
    }
}

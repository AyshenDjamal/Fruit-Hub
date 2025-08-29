package com.example.fruithub.repository;

import com.example.fruithub.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UnitRepository extends JpaRepository<Unit, UUID> {
    Unit findByCode(String code);
}

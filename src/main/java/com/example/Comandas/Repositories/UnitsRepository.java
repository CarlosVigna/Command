package com.example.Comandas.Repositories;

import com.example.Comandas.Models.Units;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitsRepository extends JpaRepository<Units, Long> {
}

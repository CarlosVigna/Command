package com.example.Comandas.Controllers;


import com.example.Comandas.Models.Units;
import com.example.Comandas.Repositories.UnitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/units")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class UnitsController {

    @Autowired
    private UnitsRepository unitsRepository;

    @GetMapping("/get/all")
    public ResponseEntity<List<Units>> getAllUnits() {
        return ResponseEntity.ok(unitsRepository.findAll());
    }

    //método post a unit
    @PostMapping("/add")
    public ResponseEntity<Units> addUnit(@RequestBody Units units) {
        return ResponseEntity.created(URI.create("/api/units/add")).body(unitsRepository.save(units));
    }

    //método para get por idUnit
    @GetMapping("/get/{id}")
    public ResponseEntity<Units> getUnitById(@PathVariable Long id) {
        return unitsRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //método para update unit
    @PutMapping("/update/{id}")
    public ResponseEntity<Units> updateUnit(@PathVariable Long id, @RequestBody Units unitsDetails) {
        return unitsRepository.findById(id).map(unit -> {
            unit.setDetailsUnit(unitsDetails.getDetailsUnit());
            return ResponseEntity.ok(unitsRepository.save(unit));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUnit(@PathVariable Long id) {
        return unitsRepository.findById(id).map(unit -> {
            unitsRepository.delete(unit);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}

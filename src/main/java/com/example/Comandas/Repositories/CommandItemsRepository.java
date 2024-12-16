package com.example.Comandas.Repositories;

import com.example.Comandas.Models.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandItemsRepository extends JpaRepository<Command, Long> {
}

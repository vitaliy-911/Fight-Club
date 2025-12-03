package com.example.fight_club.repository;

import com.example.fight_club.model.Fighter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FightersRepository extends CrudRepository<Fighter, Long> {

    List<Fighter> findAll();

    Optional<Fighter> findById(Long id);

    Optional<Fighter> findByNameContainingIgnoreCase(String lastName);

    Optional<Fighter> deleteFighterById(Long id);
}


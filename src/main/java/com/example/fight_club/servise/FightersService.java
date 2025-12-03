package com.example.fight_club.servise;

import com.example.fight_club.exception.ResourceNotFoundException;
import com.example.fight_club.model.Fighter;
import com.example.fight_club.repository.FightersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FightersService {

    private final FightersRepository fightersRepository;

    @Autowired
    public FightersService(FightersRepository fightersRepository) {
        this.fightersRepository = fightersRepository;

    }

    @Transactional(readOnly = true)
    public List<Fighter> getAll() {
        return fightersRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Fighter> getFighterById(Long id) {
        return fightersRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Fighter> getByNameContainingIgnoreCase(String name) {
        return fightersRepository.findByNameContainingIgnoreCase(name);
    }

    public void deleteFighterById(Long id) {
        fightersRepository.deleteFighterById(id);
    }


    public Fighter updateFighterById(Long id, Fighter fighter) {
        Fighter newFighter = fightersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fighter not found with id: " + id));
        newFighter.setId(fighter.getId());
        newFighter.setAge(fighter.getAge());
        newFighter.setName(fighter.getName());
        newFighter.setBeltColorFighter(fighter.getBeltColorFighter());
        newFighter.setDodgeChance(fighter.getDodgeChance());
        newFighter.setPower(fighter.getPower());
        return fightersRepository.save(newFighter);
    }

    public Fighter createFighter(Fighter fighter) {
        return fightersRepository.save(fighter);
    }
}

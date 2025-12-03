package com.example.fight_club.dto;

import com.example.fight_club.model.Fighter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FighterResponseDto {

    private List<Fighter> fighters;

    public FighterResponseDto(List<Fighter> fighters) {
        this.fighters = fighters;
    }

}

package com.example.fight_club.config;

import com.example.fight_club.model.BeltColor;
import com.example.fight_club.model.Fighter;
import com.example.fight_club.servise.FightersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    private final FightersService fightersService;
    private final Random r = new Random();

    public DataInitializer(FightersService fightersService) {
        this.fightersService = fightersService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> nameList = List.of("Виктор", "Евгений", "Виталий", "Константин", "Артем", "Никита", "Сергей", "Дмитрий", "Федор");
        BeltColor[] beltColors = BeltColor.values();
        List<BeltColor> beltColorList = Arrays.asList(beltColors);

        for (int i = 0; i < 100; i++) {
            Fighter fighter = new Fighter(nameList.get(r.nextInt(nameList.size())), r.nextInt(18, 35), r.nextInt(5, 10),
                    beltColorList.get(r.nextInt(beltColorList.size())), r.nextDouble(0.5, 2.5));
            fightersService.createFighter(fighter);

        }
    }

}

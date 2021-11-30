package com.example.springboot.controller;

import com.example.springboot.model.Hero;
import com.example.springboot.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
public class HeroController {

    @Autowired
    HeroService heroService;

    // метод GET, который по урл '/api/hero/' взвращает json
    @GetMapping("/api/hero")
    public List<Hero> heroes() throws SQLException {
        return heroService.getAllHero();
    }

    // метод POST, который может принять модель Hero и вернуть ее уже с id 100
    @PostMapping("/api/hero")
    public ResponseEntity<String> hero(@Valid @RequestBody Hero hero) throws SQLException {
        hero.setId(100);
        System.out.println(hero);
        heroService.addHero(hero.name, hero.level, hero.ultimate);
        return ResponseEntity.ok("valid");
    }

    // метод GET, возвращает Hero по id
    @GetMapping("/api/hero/{id}")
    public Hero hero(@PathVariable long id) throws SQLException {
        return heroService.getHeroById(id);
    }

    // метод удаляет Hero по id
    @DeleteMapping("/delete/{id}")
    public void deleteHeroById(@PathVariable long id) throws SQLException {
        heroService.removeHeroById(id);
    }

}

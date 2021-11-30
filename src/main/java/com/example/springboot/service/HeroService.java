package com.example.springboot.service;

import com.example.springboot.model.Hero;

import java.sql.SQLException;
import java.util.List;

public interface HeroService {
    void createHeroTable() throws SQLException;

    void dropHeroTable() throws SQLException;

    void addHero(String name, int level, String ultimate) throws SQLException;

    void removeHeroById(long id) throws SQLException;

    List<Hero> getAllHero() throws SQLException;

    Hero getHeroById(long id) throws SQLException;

    void cleanHeroTable() throws SQLException;

    void addHero(Hero hero) throws SQLException;
}

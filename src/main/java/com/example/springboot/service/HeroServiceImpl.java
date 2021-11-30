package com.example.springboot.service;

import com.example.springboot.dao.HeroDAO;
import com.example.springboot.dao.HeroDaoHibernateImpl;
import com.example.springboot.model.Hero;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class HeroServiceImpl implements HeroService {

    private HeroDAO heroDao = new HeroDaoHibernateImpl();

    @Override
    public void createHeroTable() throws SQLException {
        heroDao.createHeroTable();
    }

    @Override
    public void dropHeroTable() throws SQLException {
        heroDao.dropHeroTable();
    }

    @Override
    public void addHero(String name, int level, String ultimate) throws SQLException {
        heroDao.addHero(name, level, ultimate);
    }

    @Override
    public void removeHeroById(long id) throws SQLException {
        heroDao.removeHeroById(id);
    }

    @Override
    public List<Hero> getAllHero() throws SQLException {
        return heroDao.getAllHero();
    }

    @Override
    public Hero getHeroById(long id) throws SQLException {
        return heroDao.getHeroById(id);
    }

    @Override
    public void cleanHeroTable() throws SQLException {
        heroDao.cleanHeroTable();
    }

    @Override
    public void addHero(Hero hero) throws SQLException {
        heroDao.addHero(hero);
    }
}

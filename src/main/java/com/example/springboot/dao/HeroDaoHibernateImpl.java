package com.example.springboot.dao;

import com.example.springboot.model.Hero;
import com.example.springboot.util.Util;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

public class HeroDaoHibernateImpl implements HeroDAO {
    private final SessionFactory sessionFactory = Util.createSessionFactory();

    @Override
    public void createHeroTable() throws SQLException {

        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS heroes (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(14), " +
                    "level INTEGER CHECK(level >0 AND level < 30), " +
                    "ultimate VARCHAR (250)" +
                    ")");

            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void dropHeroTable() throws SQLException {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("DROP TABLE IF EXISTS heroes");
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void addHero(Hero hero) throws SQLException {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(hero);
            transaction.commit();
            session.close();
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void addHero(String name, int level, String ultimate) throws SQLException {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(new Hero(name, level, ultimate));
            transaction.commit();
            session.close();
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void removeHeroById(long id) throws SQLException {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();

            Hero hero = session.load(Hero.class, (long) id);
            session.delete(hero);
            transaction.commit();
            session.close();
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<Hero> getAllHero() throws SQLException {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Hero.class);
            Root<Hero> root = criteriaQuery.from(Hero.class);
            criteriaQuery.select(root);
            List<Hero> heroes = session.createQuery(criteriaQuery).getResultList();
            transaction.commit();
            session.close();
            return heroes;
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Hero getHeroById(long id) throws SQLException {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Hero hero = session.get(Hero.class, id);
            transaction.commit();
            session.close();
            return hero;
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return null;
    }

    @Override
    public void cleanHeroTable() throws SQLException {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM heroes");
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (TransactionException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}

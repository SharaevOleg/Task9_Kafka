package com.example.springboot.util;

import com.example.springboot.model.Hero;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
//    public static Connection getMysqlConnection() {
//        String userName = "root";
//        String password = "mmm333";
//        String nameTable = "hero";
//        String url = "jdbc:mysql://localhost:3306/"+nameTable+"?user="+userName+"&password="+password;
//
//        try {
//            return DriverManager.getConnection(url);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new IllegalStateException();
//        }
//    }

    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Hero.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hero?useUnicode=true&serverTimezone=UTC&useSSL=false");
//                                                               jdbc:mysql://localhost:3306/hero?useLegacyDatetimeCode=false&serverTimezone=Australia/Sydney&useSSL=false
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "mmm333");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "none");
//        configuration.setProperty("hibernate.connection.characterEncoding", "utf8");
        return configuration;
    }

    public static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }
}

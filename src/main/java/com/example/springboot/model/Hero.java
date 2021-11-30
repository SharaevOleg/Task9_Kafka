package com.example.springboot.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "heroes")
public class Hero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "name")
    public String name;

    @Column(name = "level")
    public int level;

    @Column(name = "ultimate")
    public String ultimate;

    public Hero() {
    }

    public Hero(String name, int level, String ultimate) {
        this.name = name;
        this.level = level;
        this.ultimate = ultimate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUltimate() {
        return ultimate;
    }

    public void setUltimate(String ultimate) {
        this.ultimate = ultimate;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Имя: %s | Уровень: %s | Ultimate : %s",
                this.id, this.name, this.level, this.ultimate);
    }
}
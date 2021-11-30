package com.example.springboot;

import com.example.springboot.model.Hero;
import com.example.springboot.service.HeroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.IOException;
import java.sql.SQLException;


@EnableKafka
@SpringBootApplication
public class SimpleKafkaExampleApplication {

    @Autowired
    private KafkaTemplate<Long, Hero> kafkaTemplate;

    @Autowired
    HeroService heroService;


//    "Слушаем" топик "msg". Получаем оттуда "Hero". Добавляем в базу
    @KafkaListener(topics = "msg")
    public void orderListener(ConsumerRecord<Long, String> record) throws IOException, SQLException {

        ObjectMapper mapper = new ObjectMapper();
        Hero hero = mapper.readValue(record.value(), Hero.class);
        heroService.addHero(hero);

// получаем из базы "Hero"
        long idHero = hero.getId();
        heroService.getHeroById(idHero);

// Изменяем "id". Отправляем в другой топик
        hero.setId(6666);
        kafkaTemplate.send("msg2", record.key(), hero);


//        System.out.println("**********************CONSUMER*************************************************");
//        System.out.println("Название топика - " + record.topic());
//        System.out.println("№ Партиции - " + record.partition());
//        System.out.println("Ключ - " + record.key());
//        System.out.println("Значение - " + record.value());
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleKafkaExampleApplication.class, args);
    }

}

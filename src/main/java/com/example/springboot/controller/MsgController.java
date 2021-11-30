package com.example.springboot.controller;

import com.example.springboot.model.Hero;
import com.example.springboot.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("msg")
public class MsgController {

    @Autowired
    private KafkaTemplate<Long, Hero> kafkaTemplate;

    @Autowired
    private ConsumerFactory consumerFactory;

    @Autowired
    HeroService heroService;

//
//    @Autowired
//ConsumerRecord consumerRecord;

//    @PostMapping
//    public void sendOrder(String msgId, String msg){
//        kafkaTemplate.send("msg", msgId, msg);
//    }

    //    Получаем из базы "Hero" по запрошенному id. Пишем в топик "msg"
    @PostMapping("/{id}")
    public void sendMsg(Long msgId, @PathVariable long id) throws SQLException {
        ListenableFuture<SendResult<Long, Hero>> future = kafkaTemplate.send("msg", msgId, heroService.getHeroById(id));
        System.out.println("*******************PRODUSER***********************************");
        future.addCallback(System.out::println, System.err::println);

        kafkaTemplate.flush();

    }


}

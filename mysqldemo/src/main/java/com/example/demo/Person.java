package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import java.io.Serializable;
import java.util.UUID;

@Entity
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;


}

@SpringBootApplication
@RestControllerpublic
class SpringBootMysqlApplication {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("insert")
    public String insert() {
        String id = UUID.randomUUID().toString();
        String sql = "insert into mysql_datasource (id,name) values ('"+id+"','沉默王二')";
        jdbcTemplate.execute(sql);
        return "插入完毕";
    }}
package com.example.yu_01.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
@Table(name = "ageEntity")
public class AgeEntity {
    @Column(name = "id",isId = true,autoGen = true)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    public AgeEntity() {
    }

    public AgeEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AgeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

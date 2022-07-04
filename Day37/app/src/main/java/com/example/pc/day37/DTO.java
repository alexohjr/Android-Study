package com.example.pc.day37;

public class DTO {
    private int num;
    private int type;
    private String category;
    private String name;
    private int cost;
    private int year;
    private int month;
    private int date;
    private String time;


    public DTO(int num, int type, String category, String name, int cost, int year, int month, int date, String time) {
        this.num = num;
        this.type = type;
        this.category = category;
        this.name = name;
        this.cost = cost;
        this.year = year;
        this.month = month;
        this.date = date;
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DTO(){}

}

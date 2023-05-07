/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neu.model;

/**
 *
 * @author pankt
 */
public class Computer {
    private String title;
    private double price;

    public Computer(String title,double price){
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String toString(){
        return " "+this.title + " [$" + this.price + "]"; 
    }
}
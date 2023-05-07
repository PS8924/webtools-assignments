/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neu.utility;

import java.util.Random;

/**
 *
 * @author pankt
 */
public class RandomMessage {
    
    String[] message = {"How are you?", "Hello There!!", "Happy friday", "Khaana Khaa ke jaana!"};
    Random random = new Random();
    
    public String messageGenerator(){
        return getMessageGenerator();
    }
    public String getMessageGenerator(){
        int index = random.nextInt(message.length);
        //System.out.println(message[index]);
        return message[index];
    }
}
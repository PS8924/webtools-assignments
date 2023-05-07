/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/AbstractController.java to edit this template
 */
package com.neu.controllers;

import com.neu.utility.RandomMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.AbstractDocument;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author pankt
 */
public class RandomController extends AbstractController {
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        RandomMessage rm = context.getBean(RandomMessage.class);
        String msg = rm.messageGenerator();
        
        return new ModelAndView("index","message", rm);
    }
}
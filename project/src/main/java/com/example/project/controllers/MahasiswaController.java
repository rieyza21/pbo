package com.example.project.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.DriverManager;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
public class MahasiswaController {
    private String url = "jdbc:postgresql://localhost:5432/simak";
    private String user = "postgres";
    private String password = "123456";

    private String connStatus = "Disconnected";
    private String connMessage = "";

    @RequestMapping("/")
    public String index(Model model) {
        try {
            Class.forName("org.postgresql.Driver");

            java.sql.Connection conn = DriverManager.getConnection(url, user, password);

            connStatus = "Connected";
            connMessage = "Connection established.";
        } catch (Exception e) {
            connMessage = e.getMessage();
        }
        
        model.addAttribute("connStatus", connStatus);
        model.addAttribute("connMessage", connMessage);
        return "index";
    }
}

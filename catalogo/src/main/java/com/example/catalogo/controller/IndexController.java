package com.example.catalogo.controller;

import com.example.catalogo.Service.ArticoloService;
import com.example.catalogo.model.Articolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// localhost:8080
@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private ArticoloService articoloService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("articoli", articoloService.getArticoli());
        return "index";
    }
}

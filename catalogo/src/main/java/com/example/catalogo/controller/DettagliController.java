package com.example.catalogo.controller;


import com.example.catalogo.Service.ArticoloService;
import com.example.catalogo.model.Articolo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/dettagli")
public class DettagliController
{
    @Autowired
    private ArticoloService articoloService;

    @GetMapping
    public String getPage(
            @RequestParam("id") int id,
            @RequestParam(name = "add", required = false) String result,
            Model model)
    {
        Articolo articolo = articoloService.getArticoloById(id);
        model.addAttribute("articolo", articolo);
        model.addAttribute("result", result);
        return "dettagli";
    }

    @GetMapping("/aggiungi")
    public String add(
            @RequestParam("id") int id,
            HttpSession session
    )
    {
        if(!articoloService.aggiungiACarrello(id, session))
            return "redirect:/dettagli?id=" + id + "&add=n";
        return "redirect:/dettagli?id=" + id + "&add=s";
    }
}
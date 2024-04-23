package com.example.catalogo.controller;

import com.example.catalogo.Service.ArticoloService;
import com.example.catalogo.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/riservatautente")

public class RiservataUtenteController
{
    @Autowired
    private ArticoloService articoloService;

    @GetMapping
    public String getPage(HttpSession session, Model model)
    {
        if(session.getAttribute("utente") == null)
            return "redirect:/loginutente";
        Utente utente = (Utente) session.getAttribute("utente");
        model.addAttribute("utente", utente);
        model.addAttribute("carrello", articoloService.getCarrello(session));
        model.addAttribute("totale", articoloService.getTotaleCarrello(session));
        return "riservatautente";

    }
    @GetMapping("/rimuovi")
    public String rimuoviArticolo
            (
                    @RequestParam("id") int idArticolo,
                    HttpSession session
            )
    {
        articoloService.rimuoviDaCarrello(idArticolo, session);
        return "redirect:/riservatautente";
    }

}

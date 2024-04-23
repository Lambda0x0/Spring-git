package com.example.boot02.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// localhost:8080?nome=Davide&eta=45
@Controller
@RequestMapping("/")
public class IndexController
{
  @GetMapping
  public String getPage(
          Model model,
          @RequestParam(name = "nome", required = false, defaultValue = "Ignoto") String nomeUtente,
          @RequestParam(name = "eta", required = false, defaultValue = "10") int etaUtente)
  {
    String titolo = "Pagina Iniziale";
    model.addAttribute("titolo",titolo);
    model.addAttribute("nome", nomeUtente);
    model.addAttribute("eta", etaUtente);
    return "index";
  }
}
package com.example.boot02.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// localhost:8080/about
@Controller
@RequestMapping("/about")
public class AboutController
{
  @GetMapping
  public String getPage(
          @RequestParam("nome") String nomeUtente,
          Model model,
          @RequestParam("eta") int etaUtente
  )
  {
    model.addAttribute("nome", nomeUtente);
    model.addAttribute("eta", etaUtente);
    return "about";
  }
}
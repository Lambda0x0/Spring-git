package com.example.libreria.controller;
import com.example.libreria.model.Utente;
import com.example.libreria.service.UtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// localhost:8080/registrazioneutente
@Controller
@RequestMapping("/registrazioneutente")
public class RegistrazioneUtenteController
{
  @Autowired
  private UtenteService utenteService;

  @GetMapping
  public String getPage(Model model)
  {
    Utente utente = new Utente();
    model.addAttribute("utente", utente);
    return "registrazioneutente";
  }

  @PostMapping
  public String formManager(
          @Valid @ModelAttribute("utente") Utente utente,
          BindingResult result,
          Model model)
  {
    if(result.hasErrors())
      return "registrazioneutente";
    if(!utenteService.controlloUsername(utente.getProfilo().getUsername()))
    {
      model.addAttribute("error", "Username Non Disponibile");
      return "registrazioneutente";
    }
    utenteService.registraUtente(utente);
    return "redirect:/loginutente";
  }
}
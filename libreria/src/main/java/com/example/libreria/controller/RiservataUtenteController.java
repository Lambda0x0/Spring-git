package com.example.libreria.controller;
import com.example.libreria.model.Utente;
import com.example.libreria.service.LibroService;
import com.example.libreria.service.OrdineService;
import com.example.libreria.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// localhost:8080/riservatautente
@Controller
@RequestMapping("/riservatautente")
public class RiservataUtenteController
{
  @Autowired
  private LibroService libroService;

  @Autowired
  private OrdineService ordineService;

  @Autowired
  private UtenteService utenteService;

  @GetMapping
  public String getPage(
          HttpSession session,
          Model model,
          @RequestParam(name = "send", required = false) String send)
  {
    if(session.getAttribute("utente") == null)
      return "redirect:/loginutente";
    Utente utente = (Utente) session.getAttribute("utente");
    model.addAttribute("utente", utente);
    model.addAttribute("carrello", libroService.getCarrello(session));
    model.addAttribute("totale", libroService.getTotaleCarrello(session));
    model.addAttribute("send", send);
    return "riservatautente";
  }

  @GetMapping("/logout")
  public String logoutUtente(HttpSession session)
  {
    // session.invalidate();
    session.removeAttribute("utente");
    return "redirect:/";
  }

  @GetMapping("/rimuovi")
  public String rimuoviLibro(
          @RequestParam("id") int idLibro,
          HttpSession session
  )
  {
    libroService.rimuoviDaCarrello(idLibro, session);
    return "redirect:/riservatautente";
  }

  @GetMapping("/invia")
  public String invia(HttpSession session)
  {
    ordineService.inviaOrdine(session);
    return "redirect:/riservatautente?send";
  }

  @PostMapping
  public String formManager(
          @Valid @ModelAttribute("utente") Utente utente,
          BindingResult result,
          HttpSession session
  )
  {
    if(result.hasErrors())
      return "riservatautente";
    utenteService.registraUtente(utente);
    session.setAttribute("utente", utente);
    return "redirect:/riservatautente";
  }
}
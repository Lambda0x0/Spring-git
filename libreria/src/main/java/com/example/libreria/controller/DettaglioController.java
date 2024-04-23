package com.example.libreria.controller;
import com.example.libreria.model.Libro;
import com.example.libreria.service.LibroService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// localhost:8080/dettaglio
@Controller
@RequestMapping("/dettaglio")
public class DettaglioController
{
  @Autowired
  private LibroService libroService;

  @GetMapping
  public String getPage(
          @RequestParam("id") int id,
          Model model,
          @RequestParam(name = "add", required = false) String add)
  {
    Libro libro = libroService.getLibroById(id);
    model.addAttribute("libro", libro);
    model.addAttribute("add", add);
    return "dettaglio";
  }

  @GetMapping("/aggiungi")
  public String aggiungi(
          @RequestParam("id") int id,
          HttpSession session
  )
  {
    if(!libroService.aggiungiACarrello(id, session))
      return "redirect:/dettaglio?id=" + id + "&add=n";
    return "redirect:/dettaglio?id=" + id + "&add=y";
  }
}
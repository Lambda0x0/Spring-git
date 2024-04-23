package com.example.libreria.controller;
import com.example.libreria.model.Categoria;
import com.example.libreria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// localhost:8080/admincategorie
@Controller
@RequestMapping("/admincategorie")
public class AdminCategorieController
{
  @Autowired
  private CategoriaService categoriaService;

  @GetMapping
  public String getPage(
          Model model,
          @RequestParam(name = "id", required = false) Integer id)
  {
    List<Categoria> categorie = categoriaService.getCategorie();
    Categoria categoria = id == null ? new Categoria() : categoriaService.getCategoriaById(id);
    model.addAttribute("categorie", categorie);
    model.addAttribute("categoria", categoria);
    return "admin-categorie";
  }

  @PostMapping
  public String formManager(@ModelAttribute("categoria") Categoria categoria)
  {
    categoriaService.registraCategoria(categoria);
    return "redirect:/admincategorie";
  }

  @GetMapping("/elimina")
  public String eliminaCategoria(@RequestParam("id") int id)
  {
    categoriaService.cancellaCategoria(id);
    return "redirect:/admincategorie";
  }
}
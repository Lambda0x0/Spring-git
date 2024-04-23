package com.example.libreria.controller;
import com.example.libreria.model.Autore;
import com.example.libreria.model.Categoria;
import com.example.libreria.model.Libro;
import com.example.libreria.service.AutoreService;
import com.example.libreria.service.CategoriaService;
import com.example.libreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
@RequestMapping("/adminlibri")
public class AdminLibriController
{
  @Autowired
  private LibroService libroService;

  @Autowired
  private AutoreService autoreService;

  @Autowired
  private CategoriaService categoriaService;

  private Libro libro;

  @GetMapping
  public String getPage(
          Model model,
          @RequestParam(name = "id", required = false) Integer id)
  {
    List<Libro> libri = libroService.getLibri();
    List<Autore> autori = autoreService.getAutori();
    List<Categoria> categorie = categoriaService.getCategorie();
    libro = id == null ? new Libro() : libroService.getLibroById(id);
    model.addAttribute("libri", libri);
    model.addAttribute("autori", autori);
    model.addAttribute("categorie", categorie);
    model.addAttribute("libro", libro);
    return "admin-libri";
  }

  @PostMapping
  public String formManager(
          @RequestParam("titolo") String titolo,
          @RequestParam("prezzo") String prezzo,
          @RequestParam("autore") int idAutore,
          @RequestParam("categoria") int idCategoria,
          @RequestParam(name = "copertina", required = false) MultipartFile copertina
          )
  {
    libroService.registraLibro(libro, titolo, prezzo, idAutore, idCategoria, copertina);
    return "redirect:/adminlibri";
  }

  @GetMapping("/elimina")
  public String eliminaLibro(@RequestParam("id") int id)
  {
    libroService.cancellaLibro(id);
    return "redirect:/adminlibri";
  }
}
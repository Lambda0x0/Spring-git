package com.example.libreria.service;
import com.example.libreria.model.Libro;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface LibroService
{
  List<Libro> getLibri();
  Libro getLibroById(int id);
  boolean aggiungiACarrello(int idLibro, HttpSession session);
  List<Libro> getCarrello(HttpSession session);
  void rimuoviDaCarrello(int idLibro, HttpSession session);
  double getTotaleCarrello(HttpSession session);
  void registraLibro(Libro libro, String titolo, String prezzo, int idAutore,
                     int idCategoria, MultipartFile copertina);
  void cancellaLibro(int id);
}
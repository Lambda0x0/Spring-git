package com.example.libreria.service;
import com.example.libreria.model.Autore;
import java.util.List;

public interface AutoreService
{
  List<Autore> getAutori();
  Autore getAutoreById(int id);
}
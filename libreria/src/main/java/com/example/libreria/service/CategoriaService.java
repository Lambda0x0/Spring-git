package com.example.libreria.service;
import com.example.libreria.model.Categoria;
import java.util.List;

public interface CategoriaService
{
  void registraCategoria(Categoria categoria);
  List<Categoria> getCategorie();
  Categoria getCategoriaById(int id);
  void cancellaCategoria(int id);
}
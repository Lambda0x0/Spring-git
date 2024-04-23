package com.example.libreria.dao;
import com.example.libreria.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaDao extends CrudRepository<Categoria, Integer>
{
}
package com.example.libreria.dao;
import com.example.libreria.model.Libro;
import org.springframework.data.repository.CrudRepository;

public interface LibroDao extends CrudRepository<Libro, Integer>
{
}
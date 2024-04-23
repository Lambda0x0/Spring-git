package com.example.libreria.dao;
import com.example.libreria.model.Autore;
import org.springframework.data.repository.CrudRepository;

public interface AutoreDao extends CrudRepository<Autore, Integer>
{
}
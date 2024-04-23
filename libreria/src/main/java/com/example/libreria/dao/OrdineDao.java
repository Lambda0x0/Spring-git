package com.example.libreria.dao;
import com.example.libreria.model.Ordine;
import org.springframework.data.repository.CrudRepository;

public interface OrdineDao extends CrudRepository<Ordine, Integer>
{
}
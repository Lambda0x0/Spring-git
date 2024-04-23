package com.example.catalogo.DAO;

import com.example.catalogo.model.Articolo;
import org.springframework.data.repository.CrudRepository;

public interface ArticoloDAO extends CrudRepository <Articolo, Integer>
{
}

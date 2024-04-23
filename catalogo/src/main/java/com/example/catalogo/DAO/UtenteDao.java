package com.example.catalogo.DAO;

import com.example.catalogo.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteDao extends CrudRepository<Utente, Integer>
{
    Utente findByProfilo_UsernameAndProfilo_Password(String username, String password);
}

package com.example.catalogo.Service;

import com.example.catalogo.DAO.UtenteDao;
import com.example.catalogo.model.Utente;
import jakarta.servlet.http.HttpSession;

public interface UtenteService

{
    boolean loginUtente(String username, String password, HttpSession session);


}

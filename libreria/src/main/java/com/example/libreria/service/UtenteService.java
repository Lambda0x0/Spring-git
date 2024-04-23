package com.example.libreria.service;
import com.example.libreria.model.Utente;
import jakarta.servlet.http.HttpSession;

public interface UtenteService
{
  boolean loginUtente(String username, String password, HttpSession session);
  void registraUtente(Utente utente);
  boolean controlloUsername(String username);
}
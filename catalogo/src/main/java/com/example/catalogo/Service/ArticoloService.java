package com.example.catalogo.Service;

import com.example.catalogo.model.Articolo;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface ArticoloService
    {
        List<Articolo> getArticoli();

        Articolo getArticoloById(int id);

        boolean aggiungiACarrello(int id, HttpSession session);

        List<Articolo> getCarrello(HttpSession session);

        void rimuoviDaCarrello(int id, HttpSession session);

        double getTotaleCarrello(HttpSession session);
    }

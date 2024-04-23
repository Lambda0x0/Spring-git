package com.example.catalogo.Service;

import com.example.catalogo.DAO.ArticoloDAO;
import com.example.catalogo.model.Articolo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticoloServiceImpl implements ArticoloService

{
    @Autowired
    ArticoloDAO ArticoloDAO;

    @Override
    public List<Articolo> getArticoli() {
        return (List<Articolo>) ArticoloDAO.findAll();
    }

    @Override
    public Articolo getArticoloById(int id) {
        return ArticoloDAO.findById(id).get();
    }

    @Override
    public boolean aggiungiACarrello(int id, HttpSession session)
    {

        Articolo articolo = getArticoloById(id);
        List<Articolo> carrello;
        if(session.getAttribute("carrello") != null)
        {
            carrello = (List<Articolo>) session.getAttribute("carrello");
            for(Articolo a : carrello)
                if(a.getId() == articolo.getId())
                    return false;
            carrello.add(articolo);
            session.setAttribute("carrello", carrello);
            return true;
        }
        else
        {
            carrello = new ArrayList<>();
            carrello.add(articolo);
            session.setAttribute("carrello", carrello);
            return true;
        }

    }

    @Override
    public List<Articolo> getCarrello(HttpSession session)
    {
        if(session.getAttribute("carrello") != null)
            return (List<Articolo>) session.getAttribute("carrello");
        return null;
    }

    @Override
    public void rimuoviDaCarrello(int id, HttpSession session)
    {
        List<Articolo> carrello = (List<Articolo>) session.getAttribute("carrello");
        carrello = carrello
                .stream()
                .filter(a -> a.getId() != id)
                .collect(Collectors.toList());
        if(carrello.size() > 0)
            session.setAttribute("carrello", carrello);
        else
            session.removeAttribute("carrello");

    }

    @Override
    public double getTotaleCarrello(HttpSession session)
    {

        List<Articolo> carrello = (List<Articolo>) session.getAttribute("carrello");
        if(carrello != null)
            return carrello
                    .stream()
                    .mapToDouble(Articolo::getPrezzo)
                    .reduce(0,Double::sum);
        return 0.0;
    }
}

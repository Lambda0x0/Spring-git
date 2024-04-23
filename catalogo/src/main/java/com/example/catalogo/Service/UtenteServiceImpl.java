package com.example.catalogo.Service;

import com.example.catalogo.DAO.UtenteDao;
import com.example.catalogo.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteServiceImpl implements UtenteService
{

    @Autowired
    private UtenteDao utenteDao;

    @Override
    public boolean loginUtente(String username, String password, HttpSession session)
    {
        Utente utente = utenteDao.findByProfilo_UsernameAndProfilo_Password(username, password);
        if(utente!= null)
        {
            session.setAttribute("utente", utente);
            return true;
        }
        return false;
    }
}

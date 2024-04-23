package com.example.libreria.service;
import com.example.libreria.dao.OrdineDao;
import com.example.libreria.model.Libro;
import com.example.libreria.model.Ordine;
import com.example.libreria.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrdineServiceImpl implements OrdineService
{
  @Autowired
  private OrdineDao ordineDao;

  @Autowired
  private LibroService libroService;

  @Override
  public void inviaOrdine(HttpSession session)
  {
    // otteniamo dalla sessione il carrello e l'utente loggato
    List<Libro> carrello = libroService.getCarrello(session);
    Utente utente = (Utente) session.getAttribute("utente");
    // verifichiamo che carrello e utente siano validi
    if(carrello != null && utente != null)
    {
      // creiamo un oggetto di tipo Ordine
      Ordine ordine = new Ordine();
      // impostiamo data ordine
      ordine.setData(LocalDate.now());
      // impostiamo importo ordine
      ordine.setImporto(libroService.getTotaleCarrello(session));
      // impostiamo utente ordine
      ordine.setUtente(utente);
      // impostiamo lista libri ordine
      ordine.setLibri(carrello);
      // passiamo l'ordine al metodo save per la registrazione nel database
      ordineDao.save(ordine);
      // rimuoviamo il carrello
      session.removeAttribute("carrello");
    }
  }
}
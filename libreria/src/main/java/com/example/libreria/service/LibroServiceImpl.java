package com.example.libreria.service;
import com.example.libreria.dao.LibroDao;
import com.example.libreria.model.Libro;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl implements LibroService
{
  @Autowired
  private LibroDao libroDao;

  @Autowired
  private AutoreService autoreService;

  @Autowired
  private CategoriaService categoriaService;

  @Override
  public List<Libro> getLibri()
  {
    return (List<Libro>) libroDao.findAll();
  }

  @Override
  public Libro getLibroById(int id)
  {
    Optional<Libro> optionalLibro = libroDao.findById(id);
    if(optionalLibro.isPresent())
      return optionalLibro.get();
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean aggiungiACarrello(int idLibro, HttpSession session)
  {
    // recuperiamo il libro da aggiungere al carrello
    Libro libro = getLibroById(idLibro);
    // dichiariamo una lista di libri "carrello"
    List<Libro> carrello;
    // capiamo se l'utente ha un carrello oppure no
    if(session.getAttribute("carrello") != null) // esiste già
    {
      // recuperare il carrello dalla sessione
      carrello = (List<Libro>) session.getAttribute("carrello");
      // capiamo se il libro è già presente nel carrello
      for(Libro l : carrello)
        if(l.getId() == libro.getId())
          return false;
      // se il libro non è già presente lo aggiungiamo
      carrello.add(libro);
      // sovrascriviamo l'attributo di sessione "carrello"
      session.setAttribute("carrello", carrello);
      return true;
    }
    else // non esiste un carrello (primo acquisto)
    {
      // creiamo un nuovo carrello e ci aggiungiamo il libro
      carrello = new ArrayList<>();
      carrello.add(libro);
      // salviamo il carrello come attributo di sessione
      session.setAttribute("carrello", carrello);
      return true;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Libro> getCarrello(HttpSession session)
  {
    if(session.getAttribute("carrello") != null)
      return (List<Libro>) session.getAttribute("carrello");
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void rimuoviDaCarrello(int idLibro, HttpSession session)
  {
    // otteniamo il carrello dalla sessione
    List<Libro> carrello = (List<Libro>) session.getAttribute("carrello");
    // rimozione libro carrello (modalità tradizionale)
    for(Libro l : carrello)
      if(l.getId() == idLibro)
      {
        carrello.remove(l);
        break;
      }
    // rimozione libro carrello (modalità avanzata)
    carrello = carrello
            .stream()
            .filter(l -> l.getId() != idLibro)
            .collect(Collectors.toList());
    // se ho ancora qualche libro nel carrello
    if(carrello.size() > 0)
      session.setAttribute("carrello", carrello); // sovrascrivo
    else
      session.removeAttribute("carrello"); // elimino
  }

  @SuppressWarnings("unchecked")
  @Override
  public double getTotaleCarrello(HttpSession session)
  {
    // cerchiamo di ottenere il carrello dalla sessione
    List<Libro> carrello = (List<Libro>) session.getAttribute("carrello");
    if(carrello != null)
    {
      double totale = 0;
      // metodo standard
//      for(Libro l : carrello)
//        totale += l.getPrezzo();
      // metodo avanzato
      totale = carrello
              .stream()
              .mapToDouble(Libro::getPrezzo)
              .reduce(0,(p1,p2) -> p1 + p2);
      return totale;
    }
    return 0;
  }

  @Override
  public void registraLibro(Libro libro, String titolo, String prezzo, int idAutore,
                            int idCategoria, MultipartFile copertina)
  {
    libro.setTitolo(titolo);
    libro.setPrezzo(Double.parseDouble(prezzo));
    libro.setAutore(autoreService.getAutoreById(idAutore));
    libro.setCategoria(categoriaService.getCategoriaById(idCategoria));
    if(copertina != null && !copertina.isEmpty())
    {
      try
      {
        //data:image/png;base64,.......
        String formato = copertina.getContentType();
        String copertinaCodificata = "data:" + formato + ";base64," +
                Base64.getEncoder().encodeToString(copertina.getBytes());
        libro.setCopertina(copertinaCodificata);
      } catch (Exception e)
      {
        System.out.println(e.getMessage());
      }
    }
    libroDao.save(libro);
  }

  @Override
  public void cancellaLibro(int id)
  {
    libroDao.deleteById(id);
  }
}
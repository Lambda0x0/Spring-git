package com.example.libreria.dao;
import com.example.libreria.model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UtenteDao extends CrudRepository<Utente, Integer>
{
  // SELECT * FROM utenti JOIN profili ON utenti.id_profilo=profili.id WHERE profili.username=?
  // AND profili.password=?
  Utente findByProfiloUsernameAndProfiloPassword(String username, String password);
  Utente findByProfiloUsername(String username);

  @Query
          (
                  value = "SELECT * FROM utenti WHERE nome=:n",
                  nativeQuery = true
          )
  List<Utente> trovaPerNome(@Param("n") String nome);
}
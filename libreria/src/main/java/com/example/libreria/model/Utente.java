package com.example.libreria.model;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utenti")
public class Utente
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  @Pattern(regexp = "[a-zA-Z\\sàèìòù']{1,50}", message = "Caratteri non ammessi")
  private String nome;

  @Column
  @Pattern(regexp = "[a-zA-Z\\sàèìòù']{1,50}", message = "Caratteri non ammessi")
  private String cognome;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_profilo", referencedColumnName = "id")
  @Valid
  private Profilo profilo;

  @OneToMany
          (
                  mappedBy = "utente",
                  cascade = CascadeType.REMOVE,
                  fetch = FetchType.EAGER,
                  orphanRemoval = true
          )
  private List<Ordine> ordini = new ArrayList<>();

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getNome()
  {
    return nome;
  }

  public void setNome(String nome)
  {
    this.nome = nome;
  }

  public String getCognome()
  {
    return cognome;
  }

  public void setCognome(String cognome)
  {
    this.cognome = cognome;
  }

  public Profilo getProfilo()
  {
    return profilo;
  }

  public void setProfilo(Profilo profilo)
  {
    this.profilo = profilo;
  }

  public List<Ordine> getOrdini()
  {
    return ordini;
  }

  public void setOrdini(List<Ordine> ordini)
  {
    this.ordini = ordini;
  }
}
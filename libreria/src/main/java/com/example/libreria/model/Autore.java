package com.example.libreria.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autori")
public class Autore
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String nome;

  @Column
  private String cognome;

  @OneToMany
          (
                  mappedBy = "autore",
                  cascade = CascadeType.REMOVE,
                  fetch = FetchType.EAGER,
                  orphanRemoval = true
          )
  private List<Libro> libri = new ArrayList<>();

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

  public List<Libro> getLibri()
  {
    return libri;
  }

  public void setLibri(List<Libro> libri)
  {
    this.libri = libri;
  }
}
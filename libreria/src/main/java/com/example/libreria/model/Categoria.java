package com.example.libreria.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorie")
public class Categoria
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String descrizione;

  @OneToMany
          (
                  mappedBy = "categoria",
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

  public String getDescrizione()
  {
    return descrizione;
  }

  public void setDescrizione(String descrizione)
  {
    this.descrizione = descrizione;
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
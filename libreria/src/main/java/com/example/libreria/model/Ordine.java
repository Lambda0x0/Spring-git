package com.example.libreria.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordini")
public class Ordine
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private LocalDate data;

  @Column
  private double importo;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "id_utente", referencedColumnName = "id")
  private Utente utente;

  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinTable
          (
                  name = "ordini_libri",
                  joinColumns = @JoinColumn(name = "id_ordine", referencedColumnName = "id"),
                  inverseJoinColumns = @JoinColumn(name = "id_libro", referencedColumnName = "id")
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

  public LocalDate getData()
  {
    return data;
  }

  public void setData(LocalDate data)
  {
    this.data = data;
  }

  public double getImporto()
  {
    return importo;
  }

  public void setImporto(double importo)
  {
    this.importo = importo;
  }

  public Utente getUtente()
  {
    return utente;
  }

  public void setUtente(Utente utente)
  {
    this.utente = utente;
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
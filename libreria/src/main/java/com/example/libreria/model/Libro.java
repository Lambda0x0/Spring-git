package com.example.libreria.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libri")
public class Libro
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String titolo;

  @Column
  private String copertina;

  @Column(name = "prezzo")
  private double prezzo;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "id_autore", referencedColumnName = "id")
  private Autore autore;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "id_categoria", referencedColumnName = "id")
  private Categoria categoria;

  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinTable
          (
                  name = "ordini_libri",
                  joinColumns = @JoinColumn(name = "id_libro", referencedColumnName = "id"),
                  inverseJoinColumns = @JoinColumn(name = "id_ordine", referencedColumnName = "id")
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

  public String getTitolo()
  {
    return titolo;
  }

  public void setTitolo(String titolo)
  {
    this.titolo = titolo;
  }

  public String getCopertina()
  {
    return copertina;
  }

  public void setCopertina(String copertina)
  {
    this.copertina = copertina;
  }

  public double getPrezzo()
  {
    return prezzo;
  }

  public void setPrezzo(double prezzo)
  {
    this.prezzo = prezzo;
  }

  public Autore getAutore()
  {
    return autore;
  }

  public void setAutore(Autore autore)
  {
    this.autore = autore;
  }

  public Categoria getCategoria()
  {
    return categoria;
  }

  public void setCategoria(Categoria categoria)
  {
    this.categoria = categoria;
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
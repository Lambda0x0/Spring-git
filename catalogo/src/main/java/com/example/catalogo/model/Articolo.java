package com.example.catalogo.model;

import jakarta.persistence.*;



    @Entity
    @Table(name = "articoli")

    public class Articolo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @ManyToOne(cascade = CascadeType.REFRESH)
        @JoinColumn(name = "id_marchio", referencedColumnName = "id")
        private Marchio marchio;

        @Column
        private String modello;

        @ManyToOne(cascade = CascadeType.REFRESH)
        @JoinColumn(name = "id_sistema_operativo", referencedColumnName = "id")
        private SistemaOperativo sistemaOperativo;

        @Column
        private String memoria;

        @Column
        private double prezzo;

        @Column
        private String descrizione;

        @Column
        private String immagine;

        public String getImmagine() {
            return immagine;
        }

        public void setImmagine(String immagine) {
            this.immagine = immagine;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Marchio getMarchio() {
            return marchio;
        }

        public void setMarchio(Marchio marchio) {
            this.marchio = marchio;
        }

        public String getModello() {
            return modello;
        }

        public void setModello(String modello) {
            this.modello = modello;
        }

        public SistemaOperativo getSistemaOperativo() {
            return sistemaOperativo;
        }

        public void setSistemaOperativo(SistemaOperativo sistemaOperativo) {
            this.sistemaOperativo = sistemaOperativo;
        }

        public String getMemoria() {
            return memoria;
        }

        public void setMemoria(String memoria) {
            this.memoria = memoria;
        }

        public double getPrezzo() {
            return prezzo;
        }

        public void setPrezzo(double prezzo) {
            this.prezzo = prezzo;
        }

        public String getDescrizione() {
            return descrizione;
        }

        public void setDescrizione(String descrizione) {
            this.descrizione = descrizione;
        }
    }
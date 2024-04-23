package com.example.boot01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// localhost:8080/test
@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping
    @ResponseBody
    public String metodoUno() {
        return "Benvenuto in questa applicazione";
    }

    //localhost:8080/test/due
    @GetMapping("/due")
    @ResponseBody
    public String metodoDue() {
        return "<h1>Benvenuto</1>";
    }

    //localhost:8080/test/tre?nome=Mario&cognome=Rossi
    @GetMapping("/tre")
    @ResponseBody
    public String metodoTre(
            @RequestParam("nome") String nomeUtente,
            @RequestParam("cognome") String cognomeUtente
    ) {
        return "<h1>Benvenuto " + nomeUtente + " " + cognomeUtente + "</h1>";
    }

    //localhost:8080/test/quattro?nome=Mario&cognome=Rossi
    @GetMapping("/quattro")
    @ResponseBody
    public String metodoQuattro(
            @RequestParam(name = "nome", required = false, defaultValue = "Pippo") String nomeUtente,
            @RequestParam(name = "cognome", required = false) String cognomeUtente
    ) {
        if (cognomeUtente == null)
            cognomeUtente = "Sconosciuto";
        return "<h1>Benvenuto " + nomeUtente + " " + cognomeUtente + "</h1>";
    }

    //localhost:8080/test/cinque?numero=10
    @GetMapping("/cinque")
    @ResponseBody
    public String metodoCinque(@RequestParam(name = "numero", required = false, defaultValue = "1") int numeroRicevuto) {
        numeroRicevuto++;
        return "il numero incrementato è " + numeroRicevuto;
    }

    //localhost:8080/test/sei?numero=10
    @GetMapping("/sei")
    @ResponseBody
    public String metodoSei(@RequestParam(name = "numero", required = false) String numeroRicevuto) {
     String risposta = "";
     try {
         int numero = Integer.parseInt(numeroRicevuto); // converto la stringa in numero
         numero++;
         risposta = "il numero incrementato è " + numero;

     }catch (Exception e){
         risposta = "ci hai fornito un valore non corretto";
     }
 return risposta;
    }
}
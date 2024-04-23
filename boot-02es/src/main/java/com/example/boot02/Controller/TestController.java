package com.example.boot02.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/benvenuto")
public class TestController {

    @GetMapping
    @ResponseBody
    //localhost:8080/benvenuto?nome=luciano&genere=uomo
    public String benvenuto(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "genere", required = false) String genere
    ) {
        if (nome.isEmpty() || genere.isEmpty()) {
            return "La richiesta inviata non è corretta";
        }

        String saluto;
        switch (genere.toLowerCase()) {
            case "uomo":
                saluto = "Benvenuto";
                break;
            case "donna":
                saluto = "Benvenuta";
                break;
            default:
                saluto = "Benvenut*";
                break;
        }

        return "<h1>" + saluto + " " + nome + "</h1>";
    }
}

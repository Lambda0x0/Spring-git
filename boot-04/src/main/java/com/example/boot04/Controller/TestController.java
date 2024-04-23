package com.example.boot04.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/trova")
public class TestController {

    @GetMapping
    @ResponseBody
    public String calculateNumbers(
            @RequestParam(name = "n1", required = true) String n1,
            @RequestParam(name = "n2", required = true) String n2,
            @RequestParam(name = "n3", required = true) String n3) {

//localhost:8080/trova?n1=2&n2=6&n3=8
        if (n1 == null || n2 == null || n3 == null) { //controllo se i parametri sono stati passati
            return "parametri mancanti";
        }

        try {

            int numero1 = Integer.parseInt(n1); //converto da stringa a intero
            int numero2 = Integer.parseInt(n2);
            int numero3 = Integer.parseInt(n3);


            int max = Math.max(Math.max(numero1, numero2), numero3); // calcolo il valore massimo


            int min = Math.min(Math.min(numero1, numero2), numero3); // calcolo il valore minimo


            double media = (numero1 + numero2 + numero3) / 3.0; // calcolo la media aritmetica( double perchè potrebbe essere un valore decimale)


            return "<html><body>" //creo htm per l'output
                    + "<p>Il valore massimo è: " + max + "</p>"
                    + "<p>Il valore minimo è: " + min + "</p>"
                    + "<p>La media aritmetica è: " + media + "</p>"
                    + "</body></html>";
        } catch (NumberFormatException e) { // lancio eccezzione  se il parametro non è un numero
            return "parametri non corretti";
        }
    }
}

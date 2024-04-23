package com.example.boot03.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/primo")
public class TestController {

    @GetMapping
    @ResponseBody
    public String verificaNumero(@RequestParam(name = "numero", required = false) String numeroRicevuto) {
        if (numeroRicevuto == null) {
            return "Non hai inviato alcun dato";
        }
//localhost:8080/primo?numero=1
        try {
            int numero = Integer.parseInt(numeroRicevuto);
            if (siNumeroPrimo(numero)) {
                return "Il numero comunicato è un numero primo";
            } else {
                return "Il numero comunicato non è un numero primo";
            }
        } catch (NumberFormatException e) {
            return "Il dato inviato non è corretto";
        }
    }

    private boolean siNumeroPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}

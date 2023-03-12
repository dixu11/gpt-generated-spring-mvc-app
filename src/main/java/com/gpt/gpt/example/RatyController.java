package com.gpt.gpt.example;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RatyController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cena", 0);
        model.addAttribute("raty", 6);
        return "index";
    }

    @PostMapping("/oblicz")
    public String oblicz(@RequestParam double cena, @RequestParam int raty, Model model) {
        if (cena < 100 || cena > 10000) {
            model.addAttribute("error", "Błędna cena towaru!");
            model.addAttribute("cena", cena);
            model.addAttribute("raty", raty);
            return "index";
        }
        if (raty < 6 || raty > 48) {
            model.addAttribute("error", "Błędna liczba rat!");
            model.addAttribute("cena", cena);
            model.addAttribute("raty", raty);
            return "index";
        }

        double oprocentowanie;
        if (raty >= 6 && raty <= 12) {
            oprocentowanie = 0.025;
        } else if (raty >= 13 && raty <= 24) {
            oprocentowanie = 0.05;
        } else {
            oprocentowanie = 0.1;
        }

        double oplata = cena * oprocentowanie;
        double rata = (cena + oplata) / raty;

        model.addAttribute("cena", cena);
        model.addAttribute("raty", raty);
        model.addAttribute("rata", rata);

        return "wynik";
    }
}

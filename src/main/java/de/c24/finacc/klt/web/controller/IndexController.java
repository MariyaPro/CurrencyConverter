package de.c24.finacc.klt.web.controller;

import de.c24.finacc.klt.rest.RestService;
import de.c24.finacc.klt.rest.ConverterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * IndexController
 */
@Controller
public class IndexController {

    /**
     * Index endpoint to show the index page
     *
     * @param model Spring's view model
     * @return view name
     */

    private final ConverterService converterService;
    private final RestService restService;

    public IndexController(ConverterService converterService, RestService restService) {
        this.converterService = converterService;
        this.restService = restService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", "Karten&Konten KLT");
        model.addAttribute("welcome", "Welcome to Check24");
        model.addAttribute("applicationTitle", "Check24 Currency Converter");
        model.addAttribute("listCurrency", converterService.getCurrenciesNames());
        return "index";
    }


    @PostMapping({"/", "/index"})
    public String convert(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount") String amount,
            //  BindingResult bindingResult,
            Model model) {


        model.addAttribute("title", "Karten&Konten KLT");
        model.addAttribute("welcome", "Welcome to Check24");
        model.addAttribute("applicationTitle", "Check24 Currency Converter");
        model.addAttribute("listCurrency", converterService.getCurrenciesNames());
        model.addAttribute("total",
                String.valueOf(converterService.convert(amount.isEmpty() ? 0.00 : Double.parseDouble(amount.replace(',', '.')), from, to)));
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("amount", amount.replace(',', '.'));

        return "index";
    }
}

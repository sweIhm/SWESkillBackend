package hm.cs.swe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hm.cs.swe.models.SWEFact;
import hm.cs.swe.services.SWEService;

@Controller
public class HomeController {

    @Autowired
    private SWEService sweService;


    @RequestMapping(value = {"/", "/home"})
    public String home(@RequestParam(name = "SWEJahr", required = false, defaultValue = "0") int year, Model model) {
        SWEFact fact;
        if (year == 0){
            fact = sweService.getSWEDefintion();
        } else {
            fact = sweService.getYearFact(year);
        }
        model.addAttribute("fact", fact);

        return "home";
    }
}

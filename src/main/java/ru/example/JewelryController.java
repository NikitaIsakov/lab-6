package ru.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jewelry")
public class JewelryController {

    @Autowired
    private JewelryService jewelryService;

    @GetMapping("/new")
    public String showAddJewelryForm(Model model) {
        model.addAttribute("jewelry", new Jewelry());
        return "jewelry-add";
    }

    @PostMapping("/new")
    public String addJewelry(@ModelAttribute Jewelry jewelry) {
        jewelryService.saveJewelry(jewelry);
        return "redirect:/jewelry";
    }

    @GetMapping("/edit/{id}")
    public String showEditJewelryForm(@PathVariable Long id, Model model) {
        Jewelry jewelry = jewelryService.getJewelryById(id);
        if (jewelry != null) {
            model.addAttribute("jewelry", jewelry);
            return "jewelry-edit";
        } else {
            return "redirect:/jewelry";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateJewelry(@PathVariable Long id, @ModelAttribute Jewelry jewelryDetails) {
        Jewelry updatedJewelry = jewelryService.updateJewelry(id, jewelryDetails);
        if (updatedJewelry != null) {
            return "redirect:/jewelry";
        } else {
            return "redirect:/jewelry";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteJewelry(@PathVariable Long id) {
        jewelryService.deleteJewelry(id);
        return "redirect:/jewelry";
    }

    @GetMapping
    public String showAllJewelry(Model model) {
        model.addAttribute("jewelries", jewelryService.getAllJewelry());
        return "jewelry-list";
    }

    @GetMapping("/search")
    public String searchJewelryByMaterial(@RequestParam(value = "material", required = false) String material, Model model) {
        List<Jewelry> jewelryList = new ArrayList<>();
        
        if (material != null && !material.isEmpty()) {
            jewelryList = jewelryService.searchByMaterial(material);
        }
        
        model.addAttribute("jewelryList", jewelryList);
        model.addAttribute("searchMaterial", material);
        return "jewelry-search";
    }
}

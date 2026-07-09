package lv.bootcamp.shelter.controller;

import lombok.RequiredArgsConstructor;
import lv.bootcamp.shelter.form.AnimalForm;
import lv.bootcamp.shelter.model.AnimalType;
import lv.bootcamp.shelter.service.AnimalService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AnimalPageController {

    private final AnimalService animalService;

    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    private boolean isUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER"));
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("isAdmin", isAdmin());
        model.addAttribute("isUser", isUser());
        return "index";
    }

    @GetMapping("/animals")
    public String listAnimals(Model model) {
        model.addAttribute("animals", animalService.findAll());
        model.addAttribute("isAdmin", isAdmin());
        model.addAttribute("isUser", isUser());
        return "animals";
    }

    @GetMapping("/animals/new")
    public String newAnimalForm(Model model) {
        if (!isAdmin()) {
            return "redirect:/animals";
        }
        model.addAttribute("form", new AnimalForm(null,
                null,
                null,
                null,
                null,
                null));
        model.addAttribute("types", AnimalType.values());
        model.addAttribute("isAdmin", true);
        model.addAttribute("isUser", isUser());
        return "animals-new";
    }

    @PostMapping("/animals")
    public String createAnimal(
            @Valid @ModelAttribute("form") AnimalForm form,
            BindingResult result,
            Model model
    ) {
        if (!isAdmin()) {
            return "redirect:/animals";
        }

        if (result.hasErrors()) {
            model.addAttribute("types", AnimalType.values());
            model.addAttribute("isAdmin", true);
            model.addAttribute("isUser", isUser());
            return "animals-new";
        }

        animalService.createFromForm(form);
        return "redirect:/animals";
    }
}
package se.systementor.javasecstart.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.services.DogService;
@Controller
public class AdminDogController {

    @Autowired
    private DogService dogService;

    @RequestMapping(path = "/admin/dogs/edit")
    String editView(Model model, @RequestParam(defaultValue = "1") @Min(1) @Max(100) int id) {
        Dog dog = dogService.findById(id);
        //model.addAttribute("dog", dog);  // Lägg till hela Dog-objektet i modellen
        model.addAttribute("dog", dog);  // Lägg till hela Dog-objektet i modellen
        //return "admin/dogs/edit";
        return "admin/dogs/edit";
    }

    @PostMapping(path = "/admin/dogs/update")
    String updateDog(Model model, Dog dog) {
        dogService.editDog(dog.getId(), dog.getName(), dog.getBreed(), dog.getSize(), dog.getAge(), dog.getPrice());
        return "redirect:/admin/dogs";
    }

    @GetMapping(path = "/admin/dogs")
    String list(Model model,
                @RequestParam(defaultValue = "1") @Min(1) @Max(100) int pageNo,
                @RequestParam(defaultValue = "name") String sortCol,
                @RequestParam(defaultValue = "asc") String sortOrder,
                @RequestParam(defaultValue = "10") @Min(1) @Max(100) int pageSize,
                @RequestParam(defaultValue = "") String searchString) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortCol);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Dog> page;

        if (searchString != null && !searchString.trim().isEmpty()) {
            page = dogService.searchAllAttributes(searchString.trim(), pageable);
        } else {
            page = dogService.getAll(pageable);
        }

        //model.addAttribute("activeFunction", "home");
        model.addAttribute("dogs", page);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortCol", sortCol);
        model.addAttribute("searchString", searchString);

        return "admin/dogs/list";
    }


}

package se.systementor.javasecstart.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.services.DogService;
//@RequestMapping("/admin/dogs")
@Controller
public class AdminDogController {

    @Autowired
    private DogService dogService;



    //@GetMapping(path="/admin/dogs")
    @GetMapping(path="/admin/dogs")
    //@ResponseBody
    String list(Model model,
                @RequestParam(defaultValue = "1") @Min(1) @Max(100) int pageNo,
                @RequestParam(defaultValue = "name") String sortCol,
                @RequestParam(defaultValue = "asc") String sortOrder,
                @RequestParam(defaultValue = "10") @Min(1) @Max(100) int pageSize,
                @RequestParam(required = false) String searchString) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortCol);
        Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);

        Page<Dog> page;
        if (searchString != null && !searchString.trim().isEmpty()) {
            page = dogService.searchAllAttributes(searchString.trim(), pageable);
        } else {
            page = dogService.getAll(pageable);
        }

        model.addAttribute("activeFunction", "home");

        model.addAttribute("dogs", page);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortCol", sortCol);
        model.addAttribute("searchString", searchString);
        return "admin/dogs/list";
    }


/*
    //VÅR KOD (EJ STEFAN)
    @RequestMapping(path="/")
    String viewAllSortSearch(Model model, @RequestParam(defaultValue = "1") int pageNo,
                             @RequestParam(defaultValue = "50") int pageSize,
                             @RequestParam(defaultValue = "dogName") String sortCol,
                             @RequestParam(defaultValue = "asc") String sortOrder,
                             @RequestParam(defaultValue = "") String searchString)
    {
        //model.addAttribute("activeFunction", "home");
        searchString = searchString.trim();

        model.addAttribute("searchString",searchString);
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortCol);
        Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);

        model.addAttribute("dogName", "Dog name");
        model.addAttribute("breed", "Country");
        model.addAttribute("age", "Age");
        model.addAttribute("size", "Size");
        model.addAttribute("price", "Price");

        if(!searchString.isEmpty()){
            Page<Dog> page = dogService.getAll(pageable);

            model.addAttribute("dogs", page);
            model.addAttribute("totalPages",page.getTotalPages());
            model.addAttribute("pageNo",pageNo);
            model.addAttribute("sortOrder", sortOrder);

        }else if (!sortCol.isEmpty() && !sortOrder.isEmpty()){
            model.addAttribute("sortOrder", sortOrder);
            Page<Dog> page = dogService.getAll(pageable);
            model.addAttribute("pageNo", pageNo);
            model.addAttribute("totalPages",page.getTotalPages());
            model.addAttribute("dogs", page);
        }

        return "admin/dogs/list";
    }

 */

}

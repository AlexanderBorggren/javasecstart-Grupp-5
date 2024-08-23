package se.systementor.javasecstart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.model.DogRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DogService {
    @Autowired
    DogRepository dogRepository;

    // getAll method
    public Page<Dog> getAll(Pageable pageable) {
        return dogRepository.findAll(pageable);
    }

    // searchAllAttributes method
    public Page<Dog> searchAllAttributes(String searchString, Pageable pageable) {
        List<Dog> result = new ArrayList<>();

        // Search by name, breed, size, and age (as string)
        List<Dog> nameBreedSizeAgeResults = dogRepository.findByNameContainingIgnoreCaseOrBreedContainingIgnoreCaseOrSizeContainingIgnoreCaseOrAgeContainingIgnoreCase(
                searchString, searchString, searchString, searchString, Pageable.unpaged());
        result.addAll(nameBreedSizeAgeResults);

        // Search by price (as double)
        try {
            Double price = Double.parseDouble(searchString);
            result.addAll(dogRepository.findByPrice(price, Pageable.unpaged()));
        } catch (NumberFormatException ignored) {}

        // Manually apply pagination
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), result.size());
        List<Dog> subList = result.subList(start, end);

        return new PageImpl<>(subList, pageable, result.size());
    }
}


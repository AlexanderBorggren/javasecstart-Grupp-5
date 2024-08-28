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
import java.util.Optional;

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
        // Search by name, breed, size, and age (as string)
        Page<Dog> nameBreedSizeAgeResults = dogRepository.findByNameContainingIgnoreCaseOrBreedContainingIgnoreCaseOrSizeContainingIgnoreCaseOrAgeContainingIgnoreCase(
                searchString, searchString, searchString, searchString, pageable);

        // Search by price (as double)
        try {
            Double price = Double.parseDouble(searchString);
            Page<Dog> priceResults = dogRepository.findByPrice(price, pageable);
            // Combine results if needed
            List<Dog> combinedResults = new ArrayList<>();
            combinedResults.addAll(nameBreedSizeAgeResults.getContent());
            combinedResults.addAll(priceResults.getContent());
            return new PageImpl<>(combinedResults, pageable, combinedResults.size());
        } catch (NumberFormatException ignored) {
            return nameBreedSizeAgeResults;
        }
    }

    public void editDog(long id, String name, String breed, String size, String age, int price) {
        Dog selectedDog = dogRepository.findById(id);
        if (selectedDog == null) {
            return;
        }

        selectedDog.setName(name);
        selectedDog.setBreed(breed);
        selectedDog.setSize(size);
        selectedDog.setAge(age);
        selectedDog.setPrice(price);

        dogRepository.save(selectedDog);
    }

    public Dog findById(long id) {
        return dogRepository.findById(id);
    }

}


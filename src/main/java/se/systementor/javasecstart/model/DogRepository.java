package se.systementor.javasecstart.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface DogRepository extends JpaRepository<Dog, Long> {

    Page<Dog> findByNameContainingIgnoreCaseOrBreedContainingIgnoreCaseOrSizeContainingIgnoreCaseOrAgeContainingIgnoreCase(
            String name, String breed, String size, String age, Pageable pageable);

    Page<Dog> findByPrice(Double price, Pageable pageable);

    Page<Dog> findAll(Pageable pageable);
}
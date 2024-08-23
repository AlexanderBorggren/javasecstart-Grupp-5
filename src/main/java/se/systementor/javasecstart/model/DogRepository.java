package se.systementor.javasecstart.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DogRepository extends CrudRepository<Dog, Long> {

    List<Dog> findAllBySoldToIsNull();
    Page<Dog> findAll(Pageable pageable);
    //List<Dog> findAll();
}
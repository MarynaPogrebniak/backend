package de.aittr.dogmvcproject;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {

    private DogRepository repository;

    public DogService(DogRepository repository) {
        this.repository = repository;
    }

    public List<Dog> findAll() {
        return repository.findAll();
    }
}

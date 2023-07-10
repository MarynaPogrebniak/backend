package aittr.autoproject;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService {

    private AutoRepository repository;

    public AutoService(AutoRepository repository) {
        this.repository = repository;
    }

    List<Auto> getAllAutos() {
        return repository.findAll();
    }
}

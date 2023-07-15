package de.aittr.auto;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoService {

    private AutoRepository repository;

    public AutoService(AutoRepository repository) {
        this.repository = repository;
    }

    public List<Auto> getAllAutos() {
        return repository.findAll();
    }

    public void saveAuto(Auto auto) {
        repository.save(auto);
    }

    public Optional<Auto> getAutoById(Long id) {
        Optional<Auto> auto = repository.findByID(id);
        return auto;
    }

    public Optional<Auto> getAutoByOwner(String owner) {
        Optional<Auto> owners = repository.findByOwner(owner);
        return owners;
    }

    public Optional<Auto> getAllAutosByOwner(String owner) {
        Optional<Auto> owners = repository.findAllAutosByOneOwner(owner);
        return owners;
    }

}

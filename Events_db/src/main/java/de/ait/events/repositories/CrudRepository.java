package de.ait.events.repositories;

import java.util.List;

public interface CrudRepository<T> {

    List<T> findAll();
}

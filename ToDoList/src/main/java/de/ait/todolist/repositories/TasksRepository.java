package de.ait.todolist.repositories;

import de.ait.todolist.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Task, Long> {

}

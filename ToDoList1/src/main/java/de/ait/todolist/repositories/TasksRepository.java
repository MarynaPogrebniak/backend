package de.ait.todolist.repositories;

import de.ait.todolist.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TasksRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllByStartDate(LocalDate startDate, Pageable pageable);
    Page<Task> findAllByFinishDate (LocalDate finishDate, Pageable pageable);
}

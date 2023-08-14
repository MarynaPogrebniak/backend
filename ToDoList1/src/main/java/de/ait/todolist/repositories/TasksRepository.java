package de.ait.todolist.repositories;

import de.ait.todolist.models.Task;
import de.ait.todolist.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TasksRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllByStartDate(LocalDate startDate, Pageable pageable);
    Page<Task> findAllByFinishDate (LocalDate finishDate, Pageable pageable);
    Page<Task> findAllByExecutorAndState(Pageable pageable, User executor, Task.State state);

    boolean existsByStartDateAndExecutor(LocalDate date, User executor);

    int countByStartDateBetween(LocalDate from, LocalDate to);

    void deleteByStartDateBefore(LocalDate date);

    List<Task> findAllByExecutor_RoleAndExecutor_State(User.Role role, User.State state);
}

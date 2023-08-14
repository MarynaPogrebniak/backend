package de.ait.todolist.repositories;

import de.ait.todolist.models.Task;
import de.ait.todolist.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {

    Page<User> findAllByRole(User.Role role, Pageable pageable);
    Page<User> findAllByState(User.State state, Pageable pageable);

    // показать всех пользователей, у которых есть задачи в заданном промежутке
    List<User> findAllByTasks_StartDateBetween(LocalDate from, LocalDate to);

    // JPQL
    @Query(value = "select distinct user from User user left join user.tasks task where task.state = 'PUBLISHED'")
    Page<User> findAllWithPublishedTasks(Pageable pageable);
}

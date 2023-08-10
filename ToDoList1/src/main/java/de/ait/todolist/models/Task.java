package de.ait.todolist.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String description;

    public String title;

    @Getter
    private LocalDate startDate;

    @Getter
    private LocalDate finishDate;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    User executor;


}

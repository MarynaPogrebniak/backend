package de.ait.todolist.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "tasks")
@ToString(exclude = "tasks")
@Entity
@Table(name = "account")
public class User {

    public enum Role {
        ADMIN,
        USER,
        MANAGER
    }

    public enum State {
        NOT_CONFIRMED,
        CONFIRMED,
        BANNED,
        DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "executor")
    private List<Task> tasks;

    @OneToMany(mappedBy = "executor")
    @Where(clause = "state = 'PUBLISHED'")
    private List<Task> publishedTasks;
}

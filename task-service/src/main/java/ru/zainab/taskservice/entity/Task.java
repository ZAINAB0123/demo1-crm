package ru.zainab.taskservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor()
public class Task extends BaseEntity {
    @Column(nullable = false, length = 255)
    private String title;
    @JdbcTypeCode(Types.LONGVARCHAR)
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.NEW;
    }


}

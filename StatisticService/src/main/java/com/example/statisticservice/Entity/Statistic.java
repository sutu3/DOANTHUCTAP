package com.example.statisticservice.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name="message")
    String message;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    LocalDateTime createdDate;


}

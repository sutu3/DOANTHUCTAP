package com.example.serviceteacschedule_and_makeup_schedule_service.Entity;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class Lop {
    @Id
    @Column(columnDefinition = "char(8) COMMENT 'ma lop hoc'",nullable = false,unique = true)
    String maLop;
    @Column(columnDefinition = "char(8) COMMENT 'ten lop hoc'",nullable = false)
    String tenLop;
    @OneToMany(mappedBy = "lop", cascade = CascadeType.ALL,orphanRemoval = true)
    List<Dangkybu> dangkybu;
    @OneToMany(mappedBy = "lop", cascade = CascadeType.ALL,orphanRemoval = true)
    List<Lichgiangday> lichgiangday;
    @PrePersist
    public void generateId() {
        if (maLop == null) {
            maLop = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        }
    }
}

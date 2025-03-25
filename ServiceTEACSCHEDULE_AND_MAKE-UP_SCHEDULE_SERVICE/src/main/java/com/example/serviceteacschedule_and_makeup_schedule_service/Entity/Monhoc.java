package com.example.serviceteacschedule_and_makeup_schedule_service.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class Monhoc {
    @Id
    @Column(columnDefinition = "char(8) COMMENT 'ma mon hoc'",nullable = false,unique = true)
    String maMh;
    @Column(columnDefinition = "varchar(255) COMMENT 'ten mon hoc'",nullable = false)
    String tenMh;
    @OneToMany(mappedBy = "monhoc",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Dangkybu> dangkybu;
    @OneToMany(mappedBy = "monhoc",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Lichgiangday> lichgiangday;
    @PrePersist
    public void generateId() {
        if (maMh == null) {
            maMh = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        }
    }
}

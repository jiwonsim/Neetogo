package com.jiwon.neetogo.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num;
    private String name;
    private String code;
    @Nullable
    private String latitude;
    @Nullable
    private String longitude;

    @CreationTimestamp
    private Timestamp createdAt;
}

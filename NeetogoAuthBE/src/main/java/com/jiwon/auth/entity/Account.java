package com.jiwon.auth.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@NoArgsConstructor
@Data
@AllArgsConstructor
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long num;

    private String authority;

    public Account(String authority) {
        this.authority = authority;
    }

}

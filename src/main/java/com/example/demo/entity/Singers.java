package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Singers {
    @Id
    @Column(name = "singer_pos",length = 3)
    @SequenceGenerator(sequenceName = "singer_gen1",allocationSize = 10,initialValue = 100,name="gen1")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gen1")
    private Integer SingersPosition;
    @Column(name = "singer_name",length = 10)
    private String SingersName;
    @Column(name = "singer_renumer",length = 10)
    private Double singersRemuneration;
}

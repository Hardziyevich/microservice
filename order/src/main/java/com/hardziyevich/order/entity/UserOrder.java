package com.hardziyevich.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_orders", schema = "user_order")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "groomer_id")
    private Long groomerId;

    @Column(name = "pet_name")
    private String petName;

    @Column(name = "service_type")
    private String service;

    @Column(name = "day")
    private LocalDate day;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "duration")
    private LocalTime duration;

}

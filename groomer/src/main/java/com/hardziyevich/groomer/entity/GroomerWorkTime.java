package com.hardziyevich.groomer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="groomer_work_times", schema = "groomer")
public class GroomerWorkTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="groomer_id")
    private Long groomerId;

    @Column(name = "day")
    private LocalDate day;

    @Column(name = "start_work")
    private LocalTime startWork;

    @Column(name = "end_work")
    private LocalTime endWork;

    @ManyToOne
    @JoinColumn(name = "service_type_id")
    private ServiceType serviceTypes;

}

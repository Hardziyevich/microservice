package com.hardziyevich.groomer.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "service_types",schema = "groomer")
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "duration")
    private LocalTime duration;

    @OneToMany(mappedBy = "serviceTypes")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<GroomerWorkTime> groomerWorkTimes;

}

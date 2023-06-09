package com.pixels.parquediversiones.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;
    private String nombre;

    @OneToMany(mappedBy = "rol")
    List<Empleado> empleados;
}

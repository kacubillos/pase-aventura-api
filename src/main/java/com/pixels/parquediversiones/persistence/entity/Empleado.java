package com.pixels.parquediversiones.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "numero_documento")
    private Integer numeroDocumento;

    private String nombre;
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private LocalDateTime fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "id_rol", insertable = false, updatable = false)
    private Rol rol;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}

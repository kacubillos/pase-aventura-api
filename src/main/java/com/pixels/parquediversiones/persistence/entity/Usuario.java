package com.pixels.parquediversiones.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    private String clave;
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @OneToOne(mappedBy = "usuario")
    private Empleado empleado;
}

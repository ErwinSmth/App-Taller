package com.Innovacion.Taller.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Embeddable
@Data
public class TallerPK implements Serializable {

    @Column(name="organizador_id")
    private Integer organizadorId;

    @Column(name="profesor_id")
    private Integer profesorId;

    @Column(name="categoria_id")
    private Integer categoriaId;

}

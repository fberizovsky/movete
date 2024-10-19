package com.example.movete.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDto {
    public long id;
    public String startLocation;
    public String endLocation;
    public Date startTime;
    public String description;
    public int maxPassengers;
    public Date fechaCreacion;
    public Date fechaModificacion;
    public UsuarioDto usuario;
}

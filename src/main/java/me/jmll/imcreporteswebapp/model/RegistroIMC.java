package me.jmll.imcreporteswebapp.model;

import java.time.LocalDate;

public class RegistroIMC {
    private int id;
    private int usuarioId;
    private double peso;
    private double altura;
    private int edad;
    private LocalDate fecha;
    private String fechaRegistro;
    private double imc;


   public RegistroIMC(int id, int usuarioId, double peso, double altura, LocalDate fecha, int edad) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.peso = peso;
        this.altura = altura;
        this.fecha = fecha;
        this.edad = edad;
    }

    public RegistroIMC() {
    // Constructor vacío necesario para instanciación sin argumentos
    }
    
    
    public int getEdad() {
    return edad;
    }

    public void setEdad(int edad) {
    this.edad = edad;
    }


    public String getFechaRegistro() {
        return fechaRegistro;}
    

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;}

    public double getImc() {
        return imc;}

    public void setImc(double imc) {
        this.imc = imc;}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}

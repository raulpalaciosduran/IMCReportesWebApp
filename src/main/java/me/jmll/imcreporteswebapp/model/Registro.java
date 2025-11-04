package me.jmll.imcreporteswebapp.model;

public class Registro {
    private int id;
    private int usuarioId;
    private double peso;
    private double altura;
    private double imc;
    private int edad;
    private String fechaRegistro;

    public Registro() {
        // Constructor vacío necesario para JSON y JDBC
    }

    // Getters y setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }
    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getImc() {
        return imc;
    }
    public void setImc(double imc) {
        this.imc = imc;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}

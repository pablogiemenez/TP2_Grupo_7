package Entidades;

import java.util.Date;

public class Contacto {
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private Date fecha_nacimiento;
    private String estudios;
    private String [] intereses;
    private boolean recibir_info;

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getEstudios() {
        return estudios;
    }

    public String[] getIntereses() {
        return intereses;
    }

    public boolean isRecibir_info() {
        return recibir_info;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public void setIntereses(String[] intereses) {
        this.intereses = intereses;
    }

    public void setRecibir_info(boolean recibir_info) {
        this.recibir_info = recibir_info;
    }


    public String toStringResume() {
        return this.nombre+ " "+ this.apellido+ " - "+this.email;
    }
}

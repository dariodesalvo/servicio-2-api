package ar.edu.utn.frba.dds.entities;

import java.util.List;

public class Comunidad {
    private Long id;
    private double puntaje;
    private List<Miembro> miembros;

    public Comunidad() {
    }

    public Comunidad(Long id, double puntaje, List<Miembro> miembros) {
        this.id = id;
        this.puntaje = puntaje;
        this.miembros = miembros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Miembro> miembros) {
        this.miembros = miembros;
    }
}

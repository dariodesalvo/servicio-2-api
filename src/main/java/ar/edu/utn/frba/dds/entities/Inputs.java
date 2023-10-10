package ar.edu.utn.frba.dds.entities;

import java.util.List;

public class Inputs {
    private List<Incidente> incidentes;
    private Comunidad comunidad;

    public Inputs(){
    }
    public Inputs(List<Incidente> incidentes, Comunidad comunidad) {
        this.incidentes = incidentes;
        this.comunidad = comunidad;
    }

    public List<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(List<Incidente> incidentes) {
        this.incidentes = incidentes;
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

}


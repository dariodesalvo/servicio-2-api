package ar.edu.utn.frba.dds.entities;

public class Miembro {
    private Long id;
    private double puntaje;


    public Miembro(){
    }
    public Miembro(Long id, double puntaje) {
        this.id = id;
        this.puntaje = puntaje;
    }

    public Long getId() {
        return id;
    }


    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public void addPuntaje(double puntaje){
        this.setPuntaje(this.getPuntaje() + puntaje);
    }
}

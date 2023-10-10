package ar.edu.utn.frba.dds.entities;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

public class CalculadorGradoConfianza {
    private Comunidad comunidad;

    public CalculadorGradoConfianza() {

    }

    public void asignarPuntajeAMiembro(double puntaje, Long id){
            Miembro miembro = this.findById(id);
            miembro.addPuntaje(puntaje);
    }

    public boolean exists(Long id) {
        return this.comunidad.getMiembros().stream().anyMatch(
                x -> x.getId().equals(id)); }
    public Miembro findById(Long id) {
        return this.comunidad.getMiembros().stream().filter(
                x -> x.getId().equals(id)
        ).findFirst().get(); }
    public Collection<Miembro> all() {
        return this.comunidad.getMiembros(); }


    public boolean incidentesMismoServicio(Incidente unIncidente, Incidente otroIncidente){
        return unIncidente.getCodigoServicio().equals(otroIncidente.getCodigoServicio());
    }

    public boolean mismoIncidente(Incidente incidente, Incidente otroIncidente){
        return otroIncidente.getIncidenteId().equals(incidente.getIncidenteId());
    }
    public boolean condiciones(Incidente incidente, Incidente otroIncidente){

        return (this.diferenciaEnMinutos(otroIncidente.getFechaApertura(),incidente.getFechaCierre())<3)
                && otroIncidente.getCodigoServicio().equals(incidente.getCodigoServicio())
                && !mismoIncidente(otroIncidente, incidente);
    }

    public boolean aperturaSimilar(Incidente incidente, List<Incidente> incidentes){
        return incidentes.stream()
                 .anyMatch(incidente1 -> this.condiciones(incidente, incidente1));
             }
/*
    public List<Incidente> incidentesFraudulentos(List<Incidente> incidentes){

        List<Incidente> incidentesAux = incidentes;

        return incidentes.stream()
                .filter(incidente-> aperturaSimilar(incidente, incidentesAux))
                .collect(Collectors.toList());
    }

 */

    public long diferenciaEnMinutos(String unaFecha, String otraFecha) {
        return ChronoUnit.MINUTES.between(LocalDateTime.parse(unaFecha), LocalDateTime.parse(otraFecha));
    }

    public boolean miembroConReservas(Miembro miembro){
        return 2<=miembro.getPuntaje() && miembro.getPuntaje()<=3;
    }

    public void calcularPuntajeComunidad(Comunidad comunidad){
        Double promedioUsuarios;
        Double descuento;

        promedioUsuarios = comunidad.getMiembros().stream()
                .mapToDouble(miembro -> miembro.getPuntaje())
                .sum() / comunidad.getMiembros().size();

        descuento = comunidad.getMiembros().stream()
                .filter(miembro -> miembroConReservas(miembro))
                .count() * 0.2;

        comunidad.setPuntaje(promedioUsuarios-descuento);
    }

    public Comunidad calcularPuntaje(Inputs inputs) {
        boolean incidenteOK;
        String fechaApertura;
        String fechaCierre;
        List<Incidente> incidentes = inputs.getIncidentes();

        this.comunidad = inputs.getComunidad();
        for (Incidente incidente : incidentes) {

            incidenteOK=true;
            fechaApertura = incidente.getFechaApertura();
            fechaCierre = incidente.getFechaCierre();

            //Apertura Fraudulenta
            if ((this.diferenciaEnMinutos(fechaApertura, fechaCierre) < 3)) {
                this.asignarPuntajeAMiembro(-0.2, incidente.getAbiertoPorId());
                incidenteOK = false;
                }

            //Cierre Fraudulento
            if (aperturaSimilar(incidente, incidentes)){
                incidenteOK = false;
                this.asignarPuntajeAMiembro(-0.2, incidente.getCerradoPorId());
                this.asignarPuntajeAMiembro(0.5, incidente.getAbiertoPorId());
                }

            //Incidente feliz
            if(incidenteOK){
                this.asignarPuntajeAMiembro(0.5, incidente.getAbiertoPorId());
                this.asignarPuntajeAMiembro(0.5, incidente.getCerradoPorId());
                }

            this.calcularPuntajeComunidad(comunidad);

        } return comunidad;
    }
}

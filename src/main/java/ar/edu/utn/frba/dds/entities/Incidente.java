package ar.edu.utn.frba.dds.entities;

public class Incidente {
    private Long incidenteId;
    private Long codigoServicio;
    private Long abiertoPorId;
    private Long cerradoPorId;
    private String fechaApertura;
    private String fechaCierre;

    public Incidente() {
    }

    public Incidente(Long incidenteId, Long codigoServicio, Long abiertoPorId, Long cerradoPorId, String fechaApertura, String fechaCierre) {
        this.incidenteId = incidenteId;
        this.codigoServicio = codigoServicio;
        this.abiertoPorId = abiertoPorId;
        this.cerradoPorId = cerradoPorId;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
    }
    public Long getIncidenteId() {
        return incidenteId;
    }
    public Long getCodigoServicio() {
        return codigoServicio;
    }

    public Long getAbiertoPorId() {
        return abiertoPorId;
    }

    public Long getCerradoPorId() {
        return cerradoPorId;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

}

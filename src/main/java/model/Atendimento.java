package model;

import java.time.LocalDate;

public class Atendimento {

    private String id;
    private String agenciaId;
    private String tecnicoId;
    private String clienteId;
    private LocalDate dataInicio;
    private LocalDate dataPrazo;

    // constructor
    public Atendimento(
            String id,
            String agenciaId,
            String tecnicoId,
            String clienteId,
            LocalDate dataInicio,
            LocalDate dataPrazo
    ) {
        this.id = id;
        this.agenciaId = agenciaId;
        this.tecnicoId = tecnicoId;
        this.clienteId = clienteId;
        this.dataInicio = dataInicio;
        this.dataPrazo = dataPrazo;
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public String getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(String agenciaId) {
        this.agenciaId = agenciaId;
    }

    public String getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(String tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataPrazo() {
        return dataPrazo;
    }

    public void setDataPrazo(LocalDate dataPrazo) {
        this.dataPrazo = dataPrazo;
    }
}

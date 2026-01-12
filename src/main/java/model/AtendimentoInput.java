package model;

import java.time.LocalDate;

public class AtendimentoInput {
    private String agenciaId;
    private String tecnicoId;
    private String clienteId;
    private LocalDate dataInicio;
    private LocalDate dataPrazo;
    private String protocoloId;

    public AtendimentoInput(
            LocalDate dataInicio,
            String clienteId,
            String tecnicoId,
            String agenciaId
    ) {
        this.dataInicio = dataInicio;
        this.clienteId = clienteId;
        this.tecnicoId = tecnicoId;
        this.agenciaId = agenciaId;
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

    public String getProtocoloId() {
        return protocoloId;
    }

    public void setProtocoloId(String protocoloId) {
        this.protocoloId = protocoloId;
    }
}

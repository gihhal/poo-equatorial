package model;

import java.util.Date;

public class Atendimento {

    private String id;
    private String agenciaId;
    private String tecnicoId;
    private String clienteId;
    private Date dataInicio;
    private Date dataPrazo;
    private String protocoloId;

    // constructor
    public Atendimento(
            String id,
            String agenciaId,
            String tecnicoId,
            String clienteId,
            Date dataInicio,
            Date dataPrazo,
            String protocoloId
    ) {
        this.id = id;
        this.agenciaId = agenciaId;
        this.tecnicoId = tecnicoId;
        this.clienteId = clienteId;
        this.dataInicio = dataInicio;
        this.dataPrazo = dataPrazo;
        this.protocoloId = protocoloId;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataPrazo() {
        return dataPrazo;
    }

    public void setDataPrazo(Date dataPrazo) {
        this.dataPrazo = dataPrazo;
    }

    public String getProtocoloId() {
        return protocoloId;
    }

    public void setProtocoloId(String protocoloId) {
        this.protocoloId = protocoloId;
    }
}

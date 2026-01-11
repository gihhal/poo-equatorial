package model;

import java.util.Date;

public class Protocolo {
    private String id;
    private StatusProtocolo status;
    private Date dataAbertura;
    private Date dataPrevista;
    private Date dataEncerramento;
    private String clienteId;
    private String atendimentoId;
    private String equipeId;

    // constructor
    public Protocolo(
            String id,
            StatusProtocolo status,
            Date dataAbertura,
            Date dataPrevista,
            Date dataEncerramento,
            String clienteId,
            String atendimentoId,
            String equipeId
            ) {
        this.id = id;
        this.status = status;
        this.dataAbertura = dataAbertura;
        this.dataPrevista = dataPrevista;
        this.dataEncerramento = dataEncerramento;
        this.clienteId = clienteId;
        this.atendimentoId = atendimentoId;
        this.equipeId = equipeId;
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public StatusProtocolo getStatus() {
        return this.status;
    }

    public void setStatus(StatusProtocolo newStatus) {
        this.status = newStatus;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbetura(Date dataAbetura) {
        this.dataAbertura = dataAbetura;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getAtendimentoId() {
        return atendimentoId;
    }

    public void setAtendimentoId(String atendimentoId) {
        this.atendimentoId = atendimentoId;
    }

    public String getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(String equipeId) {
        this.equipeId = equipeId;
    }

    public void agendar(Date dataPrevista, EquipeCampo equipe) {
        this.dataPrevista = dataPrevista;

        if (equipe != null) {
            this.equipeId = String.valueOf(equipe.getId());

        }

        //atualiza o status para AGENDADO se não estiver finalizado
        if (this.status != StatusProtocolo.FINALIZADO) {
            this.status = StatusProtocolo.AGENDADO;
        }
    }
}

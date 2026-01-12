package model;

import java.time.LocalDate;

public class Protocolo {
    private String id;
    private StatusProtocolo status;
    private LocalDate dataAbertura;
    private LocalDate dataPrevista;
    private LocalDate dataEncerramento;
    private String idCliente;
    private String idAtendimento;
    private String idEquipe;

    // constructor
    public Protocolo(
            String id,
            StatusProtocolo status,
            LocalDate dataAbertura,
            LocalDate dataPrevista,
            LocalDate dataEncerramento,
            String clienteId,
            String atendimentoId,
            String equipeId
            ) {
        this.id = id;
        this.status = status;
        this.dataAbertura = dataAbertura;
        this.dataPrevista = dataPrevista;
        this.dataEncerramento = dataEncerramento;
        this.idCliente = clienteId;
        this.idAtendimento = atendimentoId;
        this.idEquipe = equipeId;
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

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbetura(LocalDate dataAbetura) {
        this.dataAbertura = dataAbetura;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(LocalDate dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public LocalDate getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDate dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public String getClienteId() {
        return idCliente;
    }

    public void setClienteId(String clienteId) {
        this.idCliente = clienteId;
    }

    public String getAtendimentoId() {
        return idAtendimento;
    }

    public void setAtendimentoId(String atendimentoId) {
        this.idAtendimento = atendimentoId;
    }

    public String getEquipeId() {
        return idEquipe;
    }

    public void setEquipeId(String equipeId) {
        this.idEquipe = equipeId;
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

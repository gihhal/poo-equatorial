package model;


import java.time.LocalDate;

public class ProtocoloInput {
    private StatusProtocolo status;
    private LocalDate dataAbertura;
    private LocalDate dataPrevista;
    private String idCliente;
    private String idAtendimento;
    private String idEquipe;

    // constructor
    public ProtocoloInput(
            StatusProtocolo status,
            LocalDate dataAbertura,
            LocalDate dataPrevista,
            String clienteId,
            String atendimentoId,
            String equipeId
    ) {

        this.status = status;
        this.dataAbertura = dataAbertura;
        this.dataPrevista = dataPrevista;
        this.idCliente = clienteId;
        this.idAtendimento = atendimentoId;
        this.idEquipe = equipeId;
    }

    // getters and setters

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
}

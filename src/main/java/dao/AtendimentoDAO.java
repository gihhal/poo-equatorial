package dao;

import model.Atendimento;
import model.AtendimentoInput;
import model.Protocolo;
import model.StatusProtocolo;
import util.exception.BusinessException;

import java.time.LocalDate;
import java.util.List;

public interface AtendimentoDAO {

    // CREATE
    public Atendimento abrirAtendimento(AtendimentoInput atendimento) throws BusinessException;

    // READ
    public Atendimento buscarPorId(String id) throws BusinessException;

    public List<Atendimento> listarTodos();

    public List<Atendimento> listarPorCliente(String clienteId);

    public List<Atendimento> listarPorStatus(StatusProtocolo status);

    // UPDATE
    public void atualizar(
            String idAgendamento,
            LocalDate dataInicio,
            LocalDate dataPrazo,
            String equipeId,
            String protocoloId
    ) throws BusinessException;
}

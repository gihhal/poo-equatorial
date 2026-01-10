package dao;

import model.Atendimento;
import model.Protocolo;
import model.StatusProtocolo;
import util.exception.BusinessException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AtendimentoDAO {

    // CREATE
    void abrirAtendimento(Atendimento atendimento) throws BusinessException;

    // READ
    Atendimento buscarPorId(String id) throws BusinessException;

    List<Atendimento> listarTodos();

    List<Atendimento> listarPorCliente(String clienteId);

    List<Atendimento> listarPorStatus(StatusProtocolo status);

    // UPDATE
    public void agendarAtendimento(
            int idProtocolo,
            LocalDate dataPrevista,
            int idEquipe
    ) throws BusinessException;

    public void atualizarStatus(
            int idProtocolo,
            StatusProtocolo novoStatus
    ) throws BusinessException;

    public void atualizarEquipe(
            String idProtocolo,
            Date dataPrazo
    ) throws  BusinessException;
}

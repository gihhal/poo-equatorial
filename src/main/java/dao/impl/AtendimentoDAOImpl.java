package dao.impl;

import dao.AtendimentoDAO;
import dao.ProtocoloDAO;
import model.Atendimento;
import model.EquipeCampo;
import model.Protocolo;
import model.StatusProtocolo;
import util.ConnectionManager;
import util.exception.BusinessException;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtendimentoDAOImpl implements AtendimentoDAO {

    private final ProtocoloDAO protocoloDAO; // DAO de protocolos injetado

    public AtendimentoDAOImpl(ProtocoloDAO protocoloDAO) {
        this.protocoloDAO = protocoloDAO;
    }

    private Connection getConnection() {
        return ConnectionManager.getConnection();
    }

    // ========================
    // CREATE
    // ========================
    @Override
    public void abrirAtendimento(Atendimento atendimento) throws BusinessException {
        String sql = """
            INSERT INTO atendimento
            (id, agencia_id, tecnico_id, cliente_id, data_inicio, data_prazo, protocolo_id)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, atendimento.getId());
            ps.setString(2, atendimento.getAgenciaId());
            ps.setString(3, atendimento.getTecnicoId());
            ps.setString(4, atendimento.getClienteId());
            ps.setDate(5, new java.sql.Date(atendimento.getDataInicio().getTime()));
            ps.setDate(6, new java.sql.Date(atendimento.getDataPrazo().getTime()));
            ps.setString(7, atendimento.getProtocoloId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar atendimento: ", e);
        }
    }

    // ========================
    // READ
    // ========================
    @Override
    public Atendimento buscarPorId(String id) throws BusinessException {
        String sql = "SELECT * FROM atendimento WHERE id = ?";
        List<Atendimento> atendimentos = executarConsultaLista(sql, id);

        if (atendimentos.isEmpty()) {
            throw new BusinessException("Atendimento não encontrado para o ID: " + id);
        }

        return atendimentos.get(0);
    }

    @Override
    public List<Atendimento> listarTodos() {
        String sql = "SELECT * FROM atendimento";
        return executarConsultaLista(sql);
    }

    @Override
    public List<Atendimento> listarPorCliente(String idCliente) {
        String sql = "SELECT * FROM atendimento WHERE cliente_id = ?";
        return executarConsultaLista(sql, idCliente);
    }

    @Override
    public List<Atendimento> listarPorStatus(StatusProtocolo status) {
        String sql = "SELECT * FROM atendimento WHERE status = ?";
        return executarConsultaLista(sql, status);
    }

    // ========================
    // UPDATE
    // ========================
    @Override
    public void agendarAtendimento(
            int idProtocolo,
            LocalDate dataPrevista,
            int idEquipe
    ) throws BusinessException {

        // Buscar protocolo (int -> String)
        Protocolo protocolo = protocoloDAO.buscarPorId(String.valueOf(idProtocolo));

        if (protocolo.getStatus() != StatusProtocolo.ABERTO) {
            throw new BusinessException("Somente atendimentos ABERTOS podem ser agendados.");
        }

        if (dataPrevista == null || dataPrevista.isBefore(LocalDate.now())) {
            throw new BusinessException("Data prevista inválida.");
        }

        // Criar equipe simulada (int -> String)
        EquipeCampo equipe = new EquipeCampo();
        equipe.setId(idEquipe);

        // Converter LocalDate -> java.util.Date
        Date dataPrevistaDate = Date.from(dataPrevista.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Chamar método agendar do Protocolo
        protocolo.agendar(dataPrevistaDate, equipe);

        // Atualizar protocolo no DAO
        protocoloDAO.atualizar(protocolo);
    }

    @Override
    public void atualizarStatus(
            int idProtocolo,
            StatusProtocolo novoStatus
    ) throws BusinessException {

        Protocolo protocolo = protocoloDAO.buscarPorId(String.valueOf(idProtocolo));

        if (novoStatus == null) {
            throw new BusinessException("Status inválido.");
        }

        if (protocolo.getStatus() == StatusProtocolo.FINALIZADO) {
            throw new BusinessException("Atendimento já finalizado.");
        }

        protocolo.setStatus(novoStatus);
        protocoloDAO.atualizar(protocolo);
    }

    @Override
    public void atualizarEquipe(String idProtocolo, Date dataPrazo) throws BusinessException {
        throw new BusinessException("Erro ao atualizar equipe.");
    }

    // =========================
    // MÉTODO AUXILIAR
    // =========================
    private List<Atendimento> executarConsultaLista(String sql, Object... params) {

        List<Atendimento> atendimentos = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                atendimentos.add(mapearResultadoParaAtendimento(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar atendimentos: ", e);
        }

        return atendimentos;
    }

    // =========================
    // MAPEAMENTO RESULTSET
    // =========================
    private Atendimento mapearResultadoParaAtendimento(ResultSet rs) throws SQLException {
        return new Atendimento(
                rs.getString("id"),
                rs.getString("agencia_id"),
                rs.getString("tecnico_id"),
                rs.getString("cliente_id"),
                rs.getDate("data_inicio"),
                rs.getDate("data_prazo"),
                rs.getString("protocolo_id")
        );
    }
}

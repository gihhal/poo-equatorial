package dao.impl;

import dao.AtendimentoDAO;
import dao.EquipeDAO;
import dao.ProtocoloDAO;
import factory.DAOFactory;
import model.*;
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

    public AtendimentoDAOImpl() {
        this.protocoloDAO = DAOFactory.getProtocoloDAO();
    }

    private Connection getConnection() {
        return ConnectionManager.getConnection();
    }

    private final ProtocoloDAO protocoloDAO;
    private final EquipeDAO equipeDAO;

    public AtendimentoDAOImpl() {
        this.protocoloDAO = DAOFactory.getProtocoloDAO();
        this.equipeDAO = DAOFactory.getEquipeDAO();
    }

    // ========================
    // CREATE
    // ========================
    @Override
    public Atendimento abrirAtendimento(AtendimentoInput atendimentoData) throws BusinessException {
        String sql = """
            INSERT INTO atendimento
            (agencia_id, tecnico_id, cliente_id, data_inicio, data_prazo, protocolo_id)
            VALUES (?, ?, ?, ?, ?, ?)        
        """;

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setString(1, atendimentoData.getAgenciaId());
            ps.setString(2, atendimentoData.getTecnicoId());
            ps.setString(3, atendimentoData.getClienteId());
            ps.setDate(4, java.sql.Date.valueOf(atendimentoData.getDataInicio()));
            ps.setDate(5, java.sql.Date.valueOf(atendimentoData.getDataPrazo()));
            ps.setString(6, atendimentoData.getProtocoloId());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    String idGerado = rs.getString(1);

                    return new Atendimento(
                            idGerado,
                            atendimentoData.getAgenciaId(),
                            atendimentoData.getTecnicoId(),
                            atendimentoData.getClienteId(),
                            atendimentoData.getDataInicio(),
                            atendimentoData.getDataPrazo(),
                            atendimentoData.getProtocoloId()
                    );
                }
            }

            throw new RuntimeException("Falha ao obter ID do protocolo criado.");

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
    public void atualizar(
            String idAgendamento,
            LocalDate dataInicio,
            LocalDate dataPrazo,
            String equipeId,
            String protocoloId
    ) {

        String sql = """
            UPDATE atendimento
            SET data_inicio = ?,
                data_prazo = ?,
                id_equipe = ?,
                id_protocolo = ?
            WHERE id = ?
        """;

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            // data_inicio
            if (dataInicio != null) {
                ps.setDate(1, java.sql.Date.valueOf(dataInicio));
            } else {
                ps.setNull(1, java.sql.Types.DATE);
            }

            // data_prazo
            if (dataPrazo != null) {
                ps.setDate(2, java.sql.Date.valueOf(dataPrazo));
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }

            // equipe
            if (equipeId != null && !equipeId.isBlank()) {
                // buscar deve lançar exceção se não existir
                equipeDAO.buscarPorId(equipeId);
                ps.setString(3, equipeId);
            } else {
                ps.setNull(3, java.sql.Types.VARCHAR);
            }

            // protocolo
            if (protocoloId != null && !protocoloId.isBlank()) {
                // buscar deve lançar exceção se não existir
                protocoloDAO.buscarPorId(protocoloId);
                ps.setString(4, protocoloId);
            } else {
                ps.setNull(4, java.sql.Types.VARCHAR);
            }

            ps.setString(5, idAgendamento);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar atendimento", e);
        }
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
                rs.getDate("data_abertura").toLocalDate(),
                rs.getDate("data_prazo").toLocalDate(),
                rs.getString("protocolo_id")
        );

        return a;
    }
}

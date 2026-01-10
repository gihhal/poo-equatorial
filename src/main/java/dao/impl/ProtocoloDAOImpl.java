package dao.impl;

import dao.ProtocoloDAO;
import model.Atendimento;
import model.Protocolo;
import model.StatusProtocolo;
import util.ConnectionManager;
import util.exception.BusinessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProtocoloDAOImpl implements ProtocoloDAO {

    private Connection getConnection() {
        return ConnectionManager.getConnection();
    }

    // =========================
    // CREATE
    // =========================
    @Override
    public void criar(Protocolo protocolo) {

        String sql = """
            INSERT INTO protocolo
            (data_abertura, data_prevista, status, id_cliente, cpf_cliente, id_tecnico)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setDate(1, (Date) protocolo.getDataAbertura());
            ps.setDate(2, (Date) protocolo.getDataPrevista());
            ps.setString(3, protocolo.getStatus().name());
            ps.setString(4, protocolo.getClienteId());
            ps.setString(5, protocolo.getAtendimentoId());
            ps.setString(6, protocolo.getEquipeId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar protocolo", e);
        }
    }

    // =========================
    // READ
    // =========================
    @Override
    public Protocolo buscarPorId(String id) {
        String sql = "SELECT * FROM protocolo WHERE id = ?";

        List<Protocolo> protocolos = executarConsultaLista(sql, id);

        if (protocolos.isEmpty()) {
            throw new BusinessException("Protocolo não encontrado para o ID: " + id);
        }

        return protocolos.get(0);
    }

    @Override
    public List<Protocolo> listarTodos() {
        String sql = "SELECT * FROM protocolo";
        return executarConsultaLista(sql);
    }

    @Override
    public List<Protocolo> listarPorDia(java.util.Date data) {
        String sql = "SELECT * FROM protocolo WHERE data_prevista = ?";
        return executarConsultaLista(sql, data);
    }

    @Override
    public List<Protocolo> listarPorCliente(String idCliente) {
        String sql = "SELECT * FROM protocolo WHERE id_cliente = ?";
        return executarConsultaLista(sql, idCliente);
    }

    @Override
    public List<Protocolo> listarPorCpfCliente(String cpfCliente) {
        String sql = "SELECT * FROM protocolo WHERE cpf_cliente = ?";
        return executarConsultaLista(sql, cpfCliente);
    }

    @Override
    public List<Protocolo> listarPorTecnico(String idTecnico) {
        String sql = "SELECT * FROM protocolo WHERE id_tecnico = ?";
        return executarConsultaLista(sql, idTecnico);
    }

    // =========================
    // UPDATE
    // =========================
    @Override
    public void atualizar(Protocolo protocolo) {

        String sql = """
            UPDATE protocolo
               SET data_prevista = ?,
                   status = ?,
                   id_tecnico = ?
             WHERE id = ?
        """;

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setDate(1, (Date) protocolo.getDataAbertura());
            ps.setString(2, protocolo.getStatus().name());
            ps.setString(3, protocolo.getEquipeId());
            ps.setString(4, protocolo.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar protocolo", e);
        }
    }

    // =========================
    // DELETE
    // =========================
    @Override
    public void deletar(String id) {

        String sql = "DELETE FROM protocolo WHERE id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar protocolo", e);
        }
    }

    // =========================
    // MÉTODO AUXILIAR
    // =========================
    private List<Protocolo> executarConsultaLista(String sql, Object... params) {

        List<Protocolo> protocolos = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                protocolos.add(mapearResultadoParaProtocolo(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar protocolos: ", e);
        }

        return protocolos;
    }

    // =========================
    // MAPEAMENTO RESULTSET
    // =========================
    private Protocolo mapearResultadoParaProtocolo(ResultSet rs) throws SQLException {
        Protocolo p = new Protocolo(
            rs.getString("id"),
            StatusProtocolo.valueOf(rs.getString("status")),
            rs.getDate("data_abertura"),
            rs.getDate("data_prevista"),
            rs.getDate("data_encerramento"),
            rs.getString("id_client"),
            rs.getString("id_atendimento"),
            rs.getString("id_equipe")
        );

        return p;
    }
}

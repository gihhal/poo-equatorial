package dao.impl;

import dao.ClienteDAO;
import model.Atendimento;
import model.Cliente;
import model.ClienteInput;
import util.ConnectionManager;
import util.exception.BusinessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    private Connection getConnection() {
        return ConnectionManager.getConnection();
    }

    // CREATE
    @Override
    public Cliente criar(ClienteInput clienteData) {
        String sql = """
                INSERT INTO cliente
                (nome, cpf, contato, endereco)
                VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, clienteData.getNome());
            ps.setString(2, clienteData.getCpf());
            ps.setString(3, clienteData.getContato());
            ps.setString(4, clienteData.getEndereco());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    String idGerado = rs.getString(1);

                    return new Cliente(
                            idGerado,
                            clienteData.getNome(),
                            clienteData.getCpf(),
                            clienteData.getContato(),
                            clienteData.getEndereco()
                    );
                }
            }

            throw new RuntimeException("Falha ao obter ID do protocolo criado.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar cliente: ", e);
        }
    }

    // READ
    public List<Cliente> listarTodos() {
        String sql = "SELECT * FROM cliente";
        return executarConsultaLista(sql);
    }

    public Cliente buscarPorId(String id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";

        List<Cliente> clientes = executarConsultaLista(sql, id);

        if (clientes.isEmpty()) {
            throw new BusinessException("Cliente não encontrado para o ID: " + id);
        }

        return clientes.get(0);
    }

    public Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";

        List<Cliente> clientes = executarConsultaLista(sql, cpf);

        if (clientes.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado para o protocolo: " + cpf);
        }

        return clientes.get(0);
    }

    public Cliente buscarPorProtocolo(String protocoloId) {
        String sql = """
            SELECT c.*
            FROM cliente c
            JOIN protocolo p ON p.id_cliente = c.id
            WHERE p.id = ?
        """;

        List<Cliente> clientes = executarConsultaLista(sql, protocoloId);

        if (clientes.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado para o protocolo: " + protocoloId);
        }

        return clientes.get(0);
    }

    // UPDATE
    public void atualizar(Cliente cliente) {

    }

    // DELETE
    public void deletar(String id) {

    }

    // =========================
    // MÉTODO AUXILIAR
    // =========================
    private List<Cliente> executarConsultaLista(String sql, Object... params) {

        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                clientes.add(mapearResultadoParaAtendimento(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes: ", e);
        }

        return clientes;
    }

    // =========================
    // MAPEAMENTO RESULTSET
    // =========================
    private Cliente mapearResultadoParaAtendimento(ResultSet rs) throws SQLException {
        Cliente c = new Cliente(
                rs.getString("id"),
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("contato"),
                rs.getString("endereco")
        );

        return c;
    }
}

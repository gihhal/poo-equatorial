package dao.impl;

import dao.ClienteDAO;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    private final List<Cliente> clientes = new ArrayList<>();

    @Override
    public void criar(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public List<Cliente> listarTodos() {
        return clientes;
    }

    @Override
    public Cliente listarPorId(String id) {
        return null;
    }

    @Override
    public Cliente listarPorCpf(String cpf) {
        return null;
    }

    @Override
    public Cliente listarPorProtocolo(String protocoloId) {
        return null;
    }

    @Override
    public void salvar(Cliente cliente) {
        // salvar ou atualizar
    }

    @Override
    public void deletar(String id) {
        // deletar cliente
    }
}

package dao;

import model.Cliente;

import java.util.List;

public interface ClienteDAO {
    // CREATE
    void criar(Cliente cliente);

    // READ
    List<Cliente> listarTodos();
    Cliente listarPorId(String id);
    Cliente listarPorCpf(String cpf);
    Cliente listarPorProtocolo(String protocoloId);

    // UPDATE
    void salvar(Cliente cliente);

    // DELETE
    void deletar(String id);
}

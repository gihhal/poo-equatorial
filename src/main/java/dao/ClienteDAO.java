package dao;

import model.Cliente;
import model.ClienteInput;

import java.util.List;

public interface ClienteDAO {
    // CREATE
    public Cliente criar(ClienteInput cliente);

    // READ
    public Cliente buscarPorId(String id);
    public List<Cliente> listarTodos();
    public Cliente buscarPorCpf(String cpf);
    public Cliente buscarPorProtocolo(String protocoloId);

    // UPDATE
    public void atualizar(Cliente cliente);

    // DELETE
    public void deletar(String id);
}

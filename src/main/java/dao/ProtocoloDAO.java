package dao;

import model.Protocolo;

import java.util.Date;
import java.util.List;

public interface ProtocoloDAO {
    // CREATE
    void criar(Protocolo protocolo);

    // READ
    public Protocolo buscarPorId(String id);
    public List<Protocolo> listarTodos();
    public List<Protocolo> listarPorDia(Date data);
    public List<Protocolo> listarPorCliente(String idCliente);
    public List<Protocolo> listarPorCpfCliente(String cpfCliente);
    public List<Protocolo> listarPorTecnico(String idTecnico);

    // UPDATE
    void atualizar(Protocolo protocolo);

    // DELETE
    void deletar(String id);
}
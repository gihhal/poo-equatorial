package dao;

import model.Protocolo;
import model.ProtocoloInput;

import java.time.LocalDate;
import java.util.List;

public interface ProtocoloDAO {
    // CREATE
    public Protocolo criar(ProtocoloInput protocolo);

    // READ
    public Protocolo buscarPorId(String id);
    public List<Protocolo> listarTodos();
    public List<Protocolo> listarPorDia(LocalDate data);
    public List<Protocolo> listarPorCliente(String idCliente);
    public List<Protocolo> listarPorCpfCliente(String cpfCliente);
    public List<Protocolo> listarPorTecnico(String idTecnico);

    // UPDATE
    void atualizar(Protocolo protocolo);

    // DELETE
    void deletar(String id);
}
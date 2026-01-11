package dao;

import model.Pessoa;
import java.util.List;

public interface TecnicoDAO {

    // CREATE
    void criar(Tecnico tecnico);

    // READ
    Tecnico buscarPorId(int id);
    Tecnico buscarPorCpf(String cpf);
    List<Tecnico> listarTodos();
    List<Tecnico> listarAtivos();

    // UPDATE
    void atualizar(Tecnico tecnico);

    // DELETE
    void deletar(int id);
}

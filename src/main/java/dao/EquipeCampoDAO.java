package dao;

import model.EquipeCampo;
import java.util.List;

public interface EquipeCampoDAO {

    // CREATE
    void criar(EquipeCampo equipe);

    // READ
    EquipeCampo buscarPorId(int id);
    List<EquipeCampo> listarTodos();

    // UPDATE
    void atualizar(EquipeCampo equipe);

    // DELETE
    void deletar(int id);
}
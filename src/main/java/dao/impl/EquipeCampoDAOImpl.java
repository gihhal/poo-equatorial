package dao.impl;

import dao.EquipeCampoDAO;
import model.EquipeCampo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipeCampoDAOImpl implements EquipeCampoDAO {

    private final List<EquipeCampo> equipes = new ArrayList<>();

    @Override
    public void criar(EquipeCampo equipe) {
        equipes.add(equipe);
        System.out.println("Equipe criada: " + equipe.getNomeEquipe());
    }

    @Override
    public EquipeCampo buscarPorId(String id) {
        for (EquipeCampo e : equipes) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public List<EquipeCampo> listarTodos() {
        return new ArrayList<>(equipes); // retorna uma cópia da lista
    }

    @Override
    public void atualizar(EquipeCampo equipe) {
        for (int i = 0; i < equipes.size(); i++) {
            if (equipes.get(i).getId() == equipe.getId()) {
                equipes.set(i, equipe);
                System.out.println("Equipe atualizada: " + equipe.getNomeEquipe());
                return;
            }
        }
        System.out.println("Equipe não encontrada para atualização: " + equipe.getId());
    }

    @Override
    public void deletar(int id) {
        Optional<EquipeCampo> equipeParaRemover = equipes.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        if (equipeParaRemover.isPresent()) {
            equipes.remove(equipeParaRemover.get());
            System.out.println("Equipe deletada: " + id);
        } else {
            System.out.println("Equipe não encontrada para deletar: " + id);
        }
    }
}

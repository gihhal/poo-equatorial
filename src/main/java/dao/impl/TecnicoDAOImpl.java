package dao.impl;

import dao.TecnicoDAO;
import model.Tecnico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TecnicoDAOImpl implements TecnicoDAO {

    private final List<Tecnico> tecnicos = new ArrayList<>();

    @Override
    public void criar(Tecnico tecnico) {
        tecnicos.add(tecnico);
        System.out.println("Técnico criado: " + tecnico.getId());
    }

    @Override
    public Tecnico buscarPorId(int id) {
        for (Tecnico t : tecnicos) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    @Override
    public Tecnico buscarPorCpf(String cpf) {
        for (Tecnico t : tecnicos) {
            if (t.getCpf().equals(cpf)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public List<Tecnico> listarTodos() {
        return new ArrayList<>(tecnicos);
    }

    @Override
    public List<Tecnico> listarAtivos() {
        List<Tecnico> ativos = new ArrayList<>();
        for (Tecnico t : tecnicos) {
            if (t.isAtivo()) {
                ativos.add(t);
            }
        }
        return ativos;
    }

    @Override
    public void atualizar(Tecnico tecnico) {
        for (int i = 0; i < tecnicos.size(); i++) {
            if (tecnicos.get(i).getId() == tecnico.getId()) {
                tecnicos.set(i, tecnico);
                System.out.println("Técnico atualizado: " + tecnico.getId());
                return;
            }
        }
        System.out.println("Técnico não encontrado para atualização: " + tecnico.getId());
    }

    @Override
    public void deletar(int id) {
        Optional<Tecnico> tecnicoParaRemover = tecnicos.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
        if (tecnicoParaRemover.isPresent()) {
            tecnicos.remove(tecnicoParaRemover.get());
            System.out.println("Técnico deletado: " + id);
        } else {
            System.out.println("Técnico não encontrado para deletar: " + id);
        }
    }
}

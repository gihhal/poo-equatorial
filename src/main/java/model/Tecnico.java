package model;

public class Tecnico extends Pessoa{

    private int id;
    private String especialidade;
    private boolean ativo;

    public Tecnico(
            int id,
            String nome,
            String cpf,
            String contato,
            String especialidade,
            boolean ativo
    ) {
        super(nome, cpf, contato);
        this.id = id;
        this.especialidade = especialidade;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public boolean isAtivo() {
        return ativo;
    }
}

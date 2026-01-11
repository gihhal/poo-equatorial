package model;

public class Cliente extends Pessoa {

    private int id;
    private String endereco;

    public Cliente(
            String nome,
            String cpf,
            String contato,
            String endereco
    ) {
        super(nome, cpf, contato);
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }
}

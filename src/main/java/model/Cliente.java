package model;

public class Cliente extends Pessoa {

    private String id;
    private String endereco;

    public Cliente(
            String id,
            String nome,
            String cpf,
            String contato,
            String endereco
    ) {
        super(nome, cpf, contato);
        this.id = id;
        this.endereco = endereco;
    }

    public String getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}

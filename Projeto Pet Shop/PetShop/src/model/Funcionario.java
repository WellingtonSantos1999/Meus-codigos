package model;

public class Funcionario {

    private Long idFuncionario;
    private String nome;
    private String cpf;
    private String telefone;
    private String cargo;

    // Construtor vazio
    public Funcionario() {
    }

    // Construtor
    public Funcionario(String nome,
                       String cpf,
                       String telefone,
                       String cargo) {

        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cargo = cargo;
    }

    // GETTERS E SETTERS

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return nome;
    }
}
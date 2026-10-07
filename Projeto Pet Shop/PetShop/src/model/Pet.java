package model;

public class Pet {

    private Long idPet;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private String sexo;

    private Long idCliente;


    
    // CONSTRUTOR VAZIO
    

    public Pet() {
    }


    
    // CONSTRUTOR
    

    public Pet(String nome,
               String especie,
               String raca,
               int idade,
               String sexo,
               Long idCliente) {

        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.sexo = sexo;
        this.idCliente = idCliente;
    }


    
    // GETTERS E SETTERS
    

    public Long getIdPet() {
        return idPet;
    }

    public void setIdPet(Long idPet) {
        this.idPet = idPet;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }


    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }


    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }


    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return nome;
    }
}
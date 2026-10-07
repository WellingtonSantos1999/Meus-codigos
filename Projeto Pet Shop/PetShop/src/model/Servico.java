package model;

public class Servico {

    private Long idServico;
    private String nome;
    private String descricao;
    private double valor;
    private int duracao;


    
    // CONSTRUTOR VAZIO
    

    public Servico() {
    }


    
    // CONSTRUTOR
    

    public Servico(String nome,
                   String descricao,
                   double valor,
                   int duracao) {

        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.duracao = duracao;
    }


    
    // GETTERS E SETTERS
    

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }


    // Será usado no JComboBox do Agendamento
    @Override
    public String toString() {
        return nome;
    }
}
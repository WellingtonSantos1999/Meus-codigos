package model;

import java.time.LocalDate;

public class Pagamento {

    private Long idPagamento;

    private double valor;

    private LocalDate data;

    private String formaPagamento;

    private String status;

    private Long idAgendamento;


    // ==========================================
    // CONSTRUTOR VAZIO
    // ==========================================

    public Pagamento() {
    }


    // ==========================================
    // CONSTRUTOR
    // ==========================================

    public Pagamento(double valor,
                     LocalDate data,
                     String formaPagamento,
                     String status,
                     Long idAgendamento) {

        this.valor = valor;
        this.data = data;
        this.formaPagamento = formaPagamento;
        this.status = status;
        this.idAgendamento = idAgendamento;
    }


    // ==========================================
    // GETTERS E SETTERS
    // ==========================================

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }


    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }
}
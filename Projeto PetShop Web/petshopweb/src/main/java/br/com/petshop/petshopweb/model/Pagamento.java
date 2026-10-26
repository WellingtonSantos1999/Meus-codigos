
package br.com.petshop.petshopweb.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    // IDENTIFICADOR DO PAGAMENTO

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Long idPagamento;


    // VALOR DO PAGAMENTO

    @Column(name = "valor", precision = 10, scale = 2)
    private BigDecimal valor;


    // DATA DO PAGAMENTO

    @Column(name = "data")
    private LocalDate data;


    // FORMA DE PAGAMENTO

    @Column(name = "forma_pagamento", length = 30)
    private String formaPagamento;


    // STATUS DO PAGAMENTO

    @Column(name = "status", length = 30)
    private String status;


    // RELACIONAMENTO COM AGENDAMENTO

    @ManyToOne
    @JoinColumn(name = "id_agendamento", nullable = false)
    private Agendamento agendamento;


    // CONSTRUTOR VAZIO

    public Pagamento() {
    }


    // GETTERS E SETTERS

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }


    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
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


    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
}

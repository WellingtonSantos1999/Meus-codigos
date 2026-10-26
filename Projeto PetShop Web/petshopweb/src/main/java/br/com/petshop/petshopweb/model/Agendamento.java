
package br.com.petshop.petshopweb.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "agendamento")
public class Agendamento {

    // IDENTIFICADOR DO AGENDAMENTO

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Long idAgendamento;


    // DATA DO AGENDAMENTO

    @Column(name = "data", nullable = false)
    private LocalDate data;


    // HORARIO DO AGENDAMENTO

    @Column(name = "horario", nullable = false)
    private LocalTime horario;


    // STATUS DO AGENDAMENTO

    @Column(name = "status", length = 30)
    private String status;


    // RELACIONAMENTO COM PET

    @ManyToOne
    @JoinColumn(name = "id_pet", nullable = false)
    private Pet pet;


    // RELACIONAMENTO COM SERVICO

    @ManyToOne
    @JoinColumn(name = "id_servico", nullable = false)
    private Servico servico;


    // RELACIONAMENTO COM FUNCIONARIO

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;


    // CONSTRUTOR VAZIO

    public Agendamento() {
    }


    // GETTERS E SETTERS

    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }


    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }


    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}

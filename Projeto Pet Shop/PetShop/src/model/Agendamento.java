package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {

    private Long idAgendamento;

    private LocalDate data;
    private LocalTime horario;
    private String status;

    private Long idPet;
    private Long idServico;
    private Long idFuncionario;


    
    // CONSTRUTOR VAZIO
    

    public Agendamento() {
    }


    
    // CONSTRUTOR
    

    public Agendamento(LocalDate data,
                       LocalTime horario,
                       String status,
                       Long idPet,
                       Long idServico,
                       Long idFuncionario) {

        this.data = data;
        this.horario = horario;
        this.status = status;
        this.idPet = idPet;
        this.idServico = idServico;
        this.idFuncionario = idFuncionario;
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


    public Long getIdPet() {
        return idPet;
    }

    public void setIdPet(Long idPet) {
        this.idPet = idPet;
    }


    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }


    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    @Override
    public String toString() {
        return "Agendamento #" + idAgendamento
                + " - " + data
                + " - " + horario;
    }
}
package controller;

import dao.AgendamentoDAO;
import model.Agendamento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AgendamentoController {

    private AgendamentoDAO agendamentoDAO;


    // ==========================================
    // CONSTRUTOR
    // ==========================================

    public AgendamentoController() {

        agendamentoDAO =
                new AgendamentoDAO();
    }


    // ==========================================
    // CADASTRAR
    // ==========================================

    public void cadastrar(
            LocalDate data,
            LocalTime horario,
            String status,
            Long idPet,
            Long idServico,
            Long idFuncionario) {


        Agendamento agendamento =
                new Agendamento(
                        data,
                        horario,
                        status,
                        idPet,
                        idServico,
                        idFuncionario
                );


        agendamentoDAO.cadastrar(
                agendamento
        );
    }


    // ==========================================
    // LISTAR
    // ==========================================

    public List<Agendamento> listar() {

        return agendamentoDAO.listar();
    }


    // ==========================================
    // BUSCAR POR ID
    // ==========================================

    public Agendamento buscarPorId(
            Long id
    ) {

        return agendamentoDAO.buscarPorId(
                id
        );
    }


    // ==========================================
    // ATUALIZAR
    // ==========================================

    public void atualizar(
            Long idAgendamento,
            LocalDate data,
            LocalTime horario,
            String status,
            Long idPet,
            Long idServico,
            Long idFuncionario) {


        Agendamento agendamento =
                new Agendamento();


        agendamento.setIdAgendamento(
                idAgendamento
        );

        agendamento.setData(
                data
        );

        agendamento.setHorario(
                horario
        );

        agendamento.setStatus(
                status
        );

        agendamento.setIdPet(
                idPet
        );

        agendamento.setIdServico(
                idServico
        );

        agendamento.setIdFuncionario(
                idFuncionario
        );


        agendamentoDAO.atualizar(
                agendamento
        );
    }


    // ==========================================
    // EXCLUIR
    // ==========================================

    public void excluir(Long id) {

        agendamentoDAO.excluir(id);
    }
}
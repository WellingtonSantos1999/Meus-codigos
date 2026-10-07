package controller;

import dao.PagamentoDAO;
import model.Pagamento;

import java.time.LocalDate;
import java.util.List;

public class PagamentoController {

    private PagamentoDAO pagamentoDAO;


    // ==========================================
    // CONSTRUTOR
    // ==========================================

    public PagamentoController() {

        pagamentoDAO =
                new PagamentoDAO();
    }


    // ==========================================
    // CADASTRAR
    // ==========================================

    public void cadastrar(
            double valor,
            LocalDate data,
            String formaPagamento,
            String status,
            Long idAgendamento) {


        Pagamento pagamento =
                new Pagamento(
                        valor,
                        data,
                        formaPagamento,
                        status,
                        idAgendamento
                );


        pagamentoDAO.cadastrar(
                pagamento
        );
    }


    // ==========================================
    // LISTAR
    // ==========================================

    public List<Pagamento> listar() {

        return pagamentoDAO.listar();
    }


    // ==========================================
    // BUSCAR POR ID
    // ==========================================

    public Pagamento buscarPorId(
            Long id
    ) {

        return pagamentoDAO.buscarPorId(
                id
        );
    }


    // ==========================================
    // ATUALIZAR
    // ==========================================

    public void atualizar(
            Long idPagamento,
            double valor,
            LocalDate data,
            String formaPagamento,
            String status,
            Long idAgendamento) {


        Pagamento pagamento =
                new Pagamento();


        pagamento.setIdPagamento(
                idPagamento
        );

        pagamento.setValor(
                valor
        );

        pagamento.setData(
                data
        );

        pagamento.setFormaPagamento(
                formaPagamento
        );

        pagamento.setStatus(
                status
        );

        pagamento.setIdAgendamento(
                idAgendamento
        );


        pagamentoDAO.atualizar(
                pagamento
        );
    }


    // ==========================================
    // EXCLUIR
    // ==========================================

    public void excluir(Long id) {

        pagamentoDAO.excluir(id);
    }
}
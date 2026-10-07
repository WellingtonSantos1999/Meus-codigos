package controller;

import dao.ServicoDAO;
import model.Servico;

import java.util.List;

public class ServicoController {

    private ServicoDAO servicoDAO;


    // ==========================================
    // CONSTRUTOR
    // ==========================================

    public ServicoController() {

        servicoDAO =
                new ServicoDAO();
    }


    // ==========================================
    // CADASTRAR
    // ==========================================

    public void cadastrar(String nome,
                          String descricao,
                          double valor,
                          int duracao) {

        Servico servico =
                new Servico(
                        nome,
                        descricao,
                        valor,
                        duracao
                );


        servicoDAO.cadastrar(
                servico
        );
    }


    // ==========================================
    // LISTAR
    // ==========================================

    public List<Servico> listar() {

        return servicoDAO.listar();
    }


    // ==========================================
    // BUSCAR POR ID
    // ==========================================

    public Servico buscarPorId(Long id) {

        return servicoDAO.buscarPorId(id);
    }


    // ==========================================
    // ATUALIZAR
    // ==========================================

    public void atualizar(Long id,
                          String nome,
                          String descricao,
                          double valor,
                          int duracao) {

        Servico servico =
                new Servico();


        servico.setIdServico(id);

        servico.setNome(nome);

        servico.setDescricao(descricao);

        servico.setValor(valor);

        servico.setDuracao(duracao);


        servicoDAO.atualizar(
                servico
        );
    }


    // ==========================================
    // EXCLUIR
    // ==========================================

    public void excluir(Long id) {

        servicoDAO.excluir(id);
    }
}
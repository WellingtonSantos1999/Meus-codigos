package controller;

import dao.FuncionarioDAO;
import model.Funcionario;

import java.util.List;

public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO;

    public FuncionarioController() {

        funcionarioDAO =
                new FuncionarioDAO();
    }


    
    // CADASTRAR
    

    public void cadastrar(String nome,
                          String cpf,
                          String telefone,
                          String cargo) {

        Funcionario funcionario =
                new Funcionario(
                        nome,
                        cpf,
                        telefone,
                        cargo
                );

        funcionarioDAO.cadastrar(
                funcionario
        );
    }


    
    // LISTAR
    

    public List<Funcionario> listar() {

        return funcionarioDAO.listar();
    }


    
    // BUSCAR POR ID
    

    public Funcionario buscarPorId(Long id) {

        return funcionarioDAO.buscarPorId(id);
    }


    
    // ATUALIZAR
    

    public void atualizar(Long id,
                          String nome,
                          String cpf,
                          String telefone,
                          String cargo) {

        Funcionario funcionario =
                new Funcionario();

        funcionario.setIdFuncionario(id);

        funcionario.setNome(nome);

        funcionario.setCpf(cpf);

        funcionario.setTelefone(telefone);

        funcionario.setCargo(cargo);


        funcionarioDAO.atualizar(
                funcionario
        );
    }


    
    // EXCLUIR
    

    public boolean excluir(Long id) {
        return funcionarioDAO.excluir(id);
    }
}
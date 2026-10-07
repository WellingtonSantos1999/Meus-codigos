package controller;

import dao.ClienteDAO;
import model.Cliente;

import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        clienteDAO = new ClienteDAO();
    }


    // ==========================================
    // CADASTRAR
    // ==========================================

    public void cadastrar(String nome,
                          String cpf,
                          String telefone,
                          String email,
                          String endereco) {

        Cliente cliente = new Cliente(
                nome,
                cpf,
                telefone,
                email,
                endereco
        );

        clienteDAO.cadastrar(cliente);
    }


    // ==========================================
    // LISTAR
    // ==========================================

    public List<Cliente> listar() {

        return clienteDAO.listar();
    }


    // ==========================================
    // BUSCAR POR ID
    // ==========================================

    public Cliente buscarPorId(Long id) {

        return clienteDAO.buscarPorId(id);
    }


    // ==========================================
    // ATUALIZAR
    // ==========================================

    public void atualizar(Long id,
                          String nome,
                          String cpf,
                          String telefone,
                          String email,
                          String endereco) {

        Cliente cliente = new Cliente();

        cliente.setIdCliente(id);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setEndereco(endereco);

        clienteDAO.atualizar(cliente);
    }


    // ==========================================
    // EXCLUIR
    // ==========================================

    public void excluir(Long id) {

        clienteDAO.excluir(id);
    }
}
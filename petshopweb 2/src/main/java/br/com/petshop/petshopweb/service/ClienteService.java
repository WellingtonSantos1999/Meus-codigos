
package br.com.petshop.petshopweb.service;

import br.com.petshop.petshopweb.model.Cliente;
import br.com.petshop.petshopweb.repository.ClienteRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> obterTodos() {
        return repository.findAll();
    }

    public void salvar(Cliente cliente) {

        String nome = cliente.getNome();

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do cliente é obrigatório."
            );
        }

        if (!nome.matches("[\\p{L} ]+")) {
            throw new IllegalArgumentException(
                    "O nome deve conter apenas letras."
            );
        }

        String telefone = cliente.getTelefone();

        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException(
                    "O telefone é obrigatório."
            );
        }

        if (!telefone.matches("[0-9]+")) {
            throw new IllegalArgumentException(
                    "O telefone deve conter apenas números."
            );
        }

        if (telefone.length() != 10 && telefone.length() != 11) {
            throw new IllegalArgumentException(
                    "O telefone deve conter 10 ou 11 números."
            );
        }

        String cpf = cliente.getCpf();

        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException(
                    "O CPF é obrigatório."
            );
        }

        if (!cpf.matches("[0-9]+")) {
            throw new IllegalArgumentException(
                    "O CPF deve conter apenas números."
            );
        }

        if (cpf.length() != 11) {
            throw new IllegalArgumentException(
                    "O CPF deve conter exatamente 11 números."
            );
        }

        repository.save(cliente);
    }

    public Optional<Cliente> obterPeloId(Long id) {
        return repository.findById(id);
    }

    public void deletarPeloId(Long id) {
        repository.deleteById(id);
        repository.flush();
    }

    public long contar() {
        return repository.count();
    }
}


package br.com.petshop.petshopweb.service;

import br.com.petshop.petshopweb.model.Funcionario;
import br.com.petshop.petshopweb.repository.FuncionarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> obterTodos() {
        return repository.findAll();
    }

    public void salvar(Funcionario funcionario) {

        String nome = funcionario.getNome();

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do funcionário é obrigatório."
            );
        }

        if (!nome.matches("[\\p{L} ]+")) {
            throw new IllegalArgumentException(
                    "O nome deve conter apenas letras."
            );
        }

        String cpf = funcionario.getCpf();

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

        String telefone = funcionario.getTelefone();

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

        if (telefone.length() != 10 &&
                telefone.length() != 11) {

            throw new IllegalArgumentException(
                    "O telefone deve conter 10 ou 11 números."
            );
        }

        String cargo = funcionario.getCargo();

        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException(
                    "O cargo é obrigatório."
            );
        }

        if (!cargo.matches("[\\p{L} ]+")) {
            throw new IllegalArgumentException(
                    "O cargo deve conter apenas letras."
            );
        }

        repository.save(funcionario);
    }

    public Optional<Funcionario> obterPeloId(Long id) {
        return repository.findById(id);
    }

    public boolean existePeloId(Long id) {
        return repository.existsById(id);
    }

    public void deletarPeloId(Long id) {
        repository.deleteById(id);
        repository.flush();
    }

    public long contar() {
        return repository.count();
    }
}

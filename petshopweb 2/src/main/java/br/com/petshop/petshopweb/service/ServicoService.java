
package br.com.petshop.petshopweb.service;

import br.com.petshop.petshopweb.model.Servico;
import br.com.petshop.petshopweb.repository.ServicoRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }

    public List<Servico> obterTodos() {
        return repository.findAll();
    }

    public void salvar(Servico servico) {

        String nome = servico.getNome();

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do serviço é obrigatório."
            );
        }

        if (!nome.matches("[\\p{L} ]+")) {
            throw new IllegalArgumentException(
                    "O nome do serviço deve conter apenas letras."
            );
        }

        if (nome.length() > 100) {
            throw new IllegalArgumentException(
                    "O nome deve conter no máximo 100 caracteres."
            );
        }

        String descricao = servico.getDescricao();

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException(
                    "A descrição do serviço é obrigatória."
            );
        }

        if (descricao.length() > 200) {
            throw new IllegalArgumentException(
                    "A descrição deve conter no máximo 200 caracteres."
            );
        }

        BigDecimal valor = servico.getValor();

        if (valor == null) {
            throw new IllegalArgumentException(
                    "O valor do serviço é obrigatório."
            );
        }

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor do serviço deve ser maior que zero."
            );
        }

        if (valor.compareTo(
                new BigDecimal("99999999.99")) > 0) {

            throw new IllegalArgumentException(
                    "O valor informado ultrapassa o limite permitido."
            );
        }

        Integer duracao = servico.getDuracao();

        if (duracao == null) {
            throw new IllegalArgumentException(
                    "A duração do serviço é obrigatória."
            );
        }

        if (duracao <= 0) {
            throw new IllegalArgumentException(
                    "A duração deve ser maior que zero."
            );
        }

        repository.save(servico);
    }

    public Optional<Servico> obterPeloId(Long id) {
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


package br.com.petshop.petshopweb.service;

import br.com.petshop.petshopweb.model.Pagamento;
import br.com.petshop.petshopweb.repository.PagamentoRepository;
import br.com.petshop.petshopweb.repository.AgendamentoRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;
    private final AgendamentoRepository agendamentoRepository;

    public PagamentoService(
            PagamentoRepository repository,
            AgendamentoRepository agendamentoRepository) {

        this.repository = repository;
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<Pagamento> obterTodos() {
        return repository.findAll();
    }

    public void salvar(Pagamento pagamento) {

        BigDecimal valor = pagamento.getValor();

        if (valor == null) {
            throw new IllegalArgumentException(
                    "O valor do pagamento é obrigatório."
            );
        }

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor do pagamento deve ser maior que zero."
            );
        }

        if (valor.compareTo(
                new BigDecimal("99999999.99")) > 0) {

            throw new IllegalArgumentException(
                    "O valor do pagamento ultrapassa o limite permitido."
            );
        }

        if (valor.stripTrailingZeros().scale() > 2) {
            throw new IllegalArgumentException(
                    "O valor deve ter no máximo duas casas decimais."
            );
        }

        LocalDate data = pagamento.getData();

        if (data == null) {
            throw new IllegalArgumentException(
                    "A data do pagamento é obrigatória."
            );
        }

        if (data.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "A data do pagamento não pode estar no futuro."
            );
        }

        String formaPagamento = pagamento.getFormaPagamento();

        if (formaPagamento == null ||
                formaPagamento.isBlank()) {

            throw new IllegalArgumentException(
                    "A forma de pagamento é obrigatória."
            );
        }

        if (formaPagamento.length() > 30) {
            throw new IllegalArgumentException(
                    "A forma de pagamento deve conter no máximo 30 caracteres."
            );
        }

        String status = pagamento.getStatus();

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException(
                    "O status do pagamento é obrigatório."
            );
        }

        if (status.length() > 30) {
            throw new IllegalArgumentException(
                    "O status deve conter no máximo 30 caracteres."
            );
        }

        if (pagamento.getAgendamento() == null ||
                pagamento.getAgendamento()
                        .getIdAgendamento() == null) {

            throw new IllegalArgumentException(
                    "Selecione um agendamento para o pagamento."
            );
        }

        Long idAgendamento = pagamento
                .getAgendamento()
                .getIdAgendamento();

        if (!agendamentoRepository.existsById(idAgendamento)) {
            throw new IllegalArgumentException(
                    "O agendamento selecionado não existe."
            );
        }

        repository.save(pagamento);
    }

    public Optional<Pagamento> obterPeloId(Long id) {
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

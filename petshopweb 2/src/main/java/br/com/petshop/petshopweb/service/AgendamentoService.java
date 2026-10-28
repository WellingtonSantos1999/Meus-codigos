
package br.com.petshop.petshopweb.service;

import br.com.petshop.petshopweb.model.Agendamento;
import br.com.petshop.petshopweb.repository.AgendamentoRepository;
import br.com.petshop.petshopweb.repository.PetRepository;
import br.com.petshop.petshopweb.repository.ServicoRepository;
import br.com.petshop.petshopweb.repository.FuncionarioRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final PetRepository petRepository;
    private final ServicoRepository servicoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public AgendamentoService(
            AgendamentoRepository repository,
            PetRepository petRepository,
            ServicoRepository servicoRepository,
            FuncionarioRepository funcionarioRepository) {

        this.repository = repository;
        this.petRepository = petRepository;
        this.servicoRepository = servicoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Agendamento> obterTodos() {
        return repository.findAll();
    }

    public void salvar(Agendamento agendamento) {

        if (agendamento.getData() == null) {
            throw new IllegalArgumentException(
                    "A data do agendamento é obrigatória."
            );
        }

        if (agendamento.getHorario() == null) {
            throw new IllegalArgumentException(
                    "O horário do agendamento é obrigatório."
            );
        }

        LocalDateTime dataHora = LocalDateTime.of(
                agendamento.getData(),
                agendamento.getHorario()
        );

        if (dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "Não é permitido agendar para uma data ou horário que já passou."
            );
        }

        String status = agendamento.getStatus();

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException(
                    "O status do agendamento é obrigatório."
            );
        }

        if (status.length() > 30) {
            throw new IllegalArgumentException(
                    "O status deve conter no máximo 30 caracteres."
            );
        }

        if (agendamento.getPet() == null ||
                agendamento.getPet().getIdPet() == null) {

            throw new IllegalArgumentException(
                    "Selecione um Pet para o agendamento."
            );
        }

        if (!petRepository.existsById(
                agendamento.getPet().getIdPet())) {

            throw new IllegalArgumentException(
                    "O Pet selecionado não existe."
            );
        }

        if (agendamento.getServico() == null ||
                agendamento.getServico().getIdServico() == null) {

            throw new IllegalArgumentException(
                    "Selecione um serviço para o agendamento."
            );
        }

        if (!servicoRepository.existsById(
                agendamento.getServico().getIdServico())) {

            throw new IllegalArgumentException(
                    "O serviço selecionado não existe."
            );
        }

        if (agendamento.getFuncionario() == null ||
                agendamento.getFuncionario().getIdFuncionario() == null) {

            throw new IllegalArgumentException(
                    "Selecione um funcionário para o agendamento."
            );
        }

        if (!funcionarioRepository.existsById(
                agendamento.getFuncionario().getIdFuncionario())) {

            throw new IllegalArgumentException(
                    "O funcionário selecionado não existe."
            );
        }

        repository.save(agendamento);
    }

    public Optional<Agendamento> obterPeloId(Long id) {
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

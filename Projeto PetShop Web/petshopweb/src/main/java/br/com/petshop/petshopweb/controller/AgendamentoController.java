
package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.model.Agendamento;
import br.com.petshop.petshopweb.model.Pet;
import br.com.petshop.petshopweb.model.Servico;
import br.com.petshop.petshopweb.model.Funcionario;

import br.com.petshop.petshopweb.repository.AgendamentoRepository;
import br.com.petshop.petshopweb.repository.PetRepository;
import br.com.petshop.petshopweb.repository.ServicoRepository;
import br.com.petshop.petshopweb.repository.FuncionarioRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoRepository agendamentoRepository;
    private final PetRepository petRepository;
    private final ServicoRepository servicoRepository;
    private final FuncionarioRepository funcionarioRepository;

    // CONSTRUTOR

    public AgendamentoController(
            AgendamentoRepository agendamentoRepository,
            PetRepository petRepository,
            ServicoRepository servicoRepository,
            FuncionarioRepository funcionarioRepository) {

        this.agendamentoRepository = agendamentoRepository;
        this.petRepository = petRepository;
        this.servicoRepository = servicoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    // LISTAR AGENDAMENTOS

    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "agendamentos",
                agendamentoRepository.findAll()
        );

        return "agendamentos/lista";
    }

    // ABRIR FORMULARIO DE CADASTRO

    @GetMapping("/novo")
    public String novo(Model model) {

        Agendamento agendamento = new Agendamento();
        agendamento.setStatus("Agendado");

        model.addAttribute("agendamento", agendamento);

        carregarListas(model);

        return "agendamentos/formulario";
    }

    // SALVAR AGENDAMENTO

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Agendamento agendamento,
            @RequestParam Long idPet,
            @RequestParam Long idServico,
            @RequestParam Long idFuncionario,
            RedirectAttributes redirectAttributes) {

        Optional<Pet> pet = petRepository.findById(idPet);

        Optional<Servico> servico =
                servicoRepository.findById(idServico);

        Optional<Funcionario> funcionario =
                funcionarioRepository.findById(idFuncionario);

        if (pet.isEmpty()
                || servico.isEmpty()
                || funcionario.isEmpty()) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Pet, serviço ou funcionário não encontrado."
            );

            return "redirect:/agendamentos";
        }

        agendamento.setPet(pet.get());
        agendamento.setServico(servico.get());
        agendamento.setFuncionario(funcionario.get());

        if (agendamento.getStatus() == null
                || agendamento.getStatus().isBlank()) {

            agendamento.setStatus("Agendado");
        }

        agendamentoRepository.save(agendamento);

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "Agendamento salvo com sucesso!"
        );

        return "redirect:/agendamentos";
    }

    // ABRIR FORMULARIO DE EDICAO

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {

        Agendamento agendamento =
                agendamentoRepository.findById(id).orElse(null);

        if (agendamento == null) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Agendamento não encontrado."
            );

            return "redirect:/agendamentos";
        }

        model.addAttribute("agendamento", agendamento);

        carregarListas(model);

        return "agendamentos/formulario";
    }

    // EXCLUIR AGENDAMENTO

    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        if (!agendamentoRepository.existsById(id)) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Agendamento não encontrado."
            );

            return "redirect:/agendamentos";
        }

        try {

            agendamentoRepository.deleteById(id);
            agendamentoRepository.flush();

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Agendamento excluído com sucesso!"
            );

        } catch (DataIntegrityViolationException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Não é possível excluir este agendamento, " +
                            "pois existem registros vinculados a ele."
            );
        }

        return "redirect:/agendamentos";
    }

    // CARREGAR PETS, SERVICOS E FUNCIONARIOS

    private void carregarListas(Model model) {

        model.addAttribute(
                "pets",
                petRepository.findAll()
        );

        model.addAttribute(
                "servicos",
                servicoRepository.findAll()
        );

        model.addAttribute(
                "funcionarios",
                funcionarioRepository.findAll()
        );
    }
}

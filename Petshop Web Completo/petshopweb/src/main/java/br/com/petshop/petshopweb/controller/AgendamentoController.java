
package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.model.Agendamento;
import br.com.petshop.petshopweb.model.Pet;
import br.com.petshop.petshopweb.model.Servico;
import br.com.petshop.petshopweb.model.Funcionario;

import br.com.petshop.petshopweb.service.AgendamentoService;
import br.com.petshop.petshopweb.service.PetService;
import br.com.petshop.petshopweb.service.ServicoService;
import br.com.petshop.petshopweb.service.FuncionarioService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final PetService petService;
    private final ServicoService servicoService;
    private final FuncionarioService funcionarioService;


    public AgendamentoController(
            AgendamentoService agendamentoService,
            PetService petService,
            ServicoService servicoService,
            FuncionarioService funcionarioService) {

        this.agendamentoService = agendamentoService;
        this.petService = petService;
        this.servicoService = servicoService;
        this.funcionarioService = funcionarioService;
    }


    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "agendamentos",
                agendamentoService.obterTodos()
        );

        return "agendamentos/lista";
    }


    @GetMapping("/novo")
    public String novo(Model model) {

        if (!model.containsAttribute("agendamento")) {
            model.addAttribute(
                    "agendamento",
                    new Agendamento()
            );
        }

        model.addAttribute("pets", petService.obterTodos());
        model.addAttribute("servicos", servicoService.obterTodos());
        model.addAttribute("funcionarios", funcionarioService.obterTodos());

        return "agendamentos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Agendamento agendamento,
            @RequestParam(required = false) Long idPet,
            @RequestParam(required = false) Long idServico,
            @RequestParam(required = false) Long idFuncionario,
            RedirectAttributes redirectAttributes) {

        try {

            if (idPet != null) {
                agendamento.setPet(
                        petService.obterPeloId(idPet).orElse(null)
                );
            }

            if (idServico != null) {
                agendamento.setServico(
                        servicoService.obterPeloId(idServico).orElse(null)
                );
            }

            if (idFuncionario != null) {
                agendamento.setFuncionario(
                        funcionarioService.obterPeloId(idFuncionario).orElse(null)
                );
            }

            if (agendamento.getStatus() == null ||
                    agendamento.getStatus().isBlank()) {

                agendamento.setStatus("Agendado");
            }

            agendamentoService.salvar(agendamento);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Agendamento salvo com sucesso!"
            );

            return "redirect:/agendamentos";

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );

            redirectAttributes.addFlashAttribute(
                    "agendamento",
                    agendamento
            );

            if (agendamento.getIdAgendamento() != null) {
                return "redirect:/agendamentos/editar/"
                        + agendamento.getIdAgendamento();
            }

            return "redirect:/agendamentos/novo";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model) {

        if (!model.containsAttribute("agendamento")) {

            Agendamento agendamento = agendamentoService
                    .obterPeloId(id)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Agendamento não encontrado: " + id
                            )
                    );

            model.addAttribute("agendamento", agendamento);
        }

        model.addAttribute("pets", petService.obterTodos());
        model.addAttribute("servicos", servicoService.obterTodos());
        model.addAttribute("funcionarios", funcionarioService.obterTodos());

        return "agendamentos/formulario";
    }


    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        if (!agendamentoService.existePeloId(id)) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Agendamento não encontrado."
            );

            return "redirect:/agendamentos";
        }

        try {

            agendamentoService.deletarPeloId(id);

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

    private void carregarListas(Model model) {

        model.addAttribute(
                "pets",
                petService.obterTodos()
        );

        model.addAttribute(
                "servicos",
                servicoService.obterTodos()
        );

        model.addAttribute(
                "funcionarios",
                funcionarioService.obterTodos()
        );
    }
}

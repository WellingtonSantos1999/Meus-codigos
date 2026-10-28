
package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.model.Funcionario;
import br.com.petshop.petshopweb.service.FuncionarioService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(
            FuncionarioService funcionarioService) {

        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "funcionarios",
                funcionarioService.obterTodos()
        );

        return "funcionarios/lista";
    }


    @GetMapping("/novo")
    public String novo(Model model) {

        if (!model.containsAttribute("funcionario")) {
            model.addAttribute("funcionario", new Funcionario());
        }

        return "funcionarios/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Funcionario funcionario,
            RedirectAttributes redirectAttributes) {

        try {

            funcionarioService.salvar(funcionario);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Funcionário salvo com sucesso!"
            );

            return "redirect:/funcionarios";

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );

            redirectAttributes.addFlashAttribute(
                    "funcionario",
                    funcionario
            );

            if (funcionario.getIdFuncionario() != null) {
                return "redirect:/funcionarios/editar/"
                        + funcionario.getIdFuncionario();
            }

            return "redirect:/funcionarios/novo";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model) {

        if (!model.containsAttribute("funcionario")) {

            Funcionario funcionario = funcionarioService
                    .obterPeloId(id)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Funcionário não encontrado: " + id
                            )
                    );

            model.addAttribute("funcionario", funcionario);
        }

        return "funcionarios/formulario";
    }


    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        if (!funcionarioService.existePeloId(id)) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Funcionário não encontrado."
            );

            return "redirect:/funcionarios";
        }

        try {

            funcionarioService.deletarPeloId(id);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Funcionário excluído com sucesso!"
            );

        } catch (DataIntegrityViolationException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Não é possível excluir este funcionário, " +
                            "pois existem agendamentos vinculados a ele."
            );
        }

        return "redirect:/funcionarios";
    }
}

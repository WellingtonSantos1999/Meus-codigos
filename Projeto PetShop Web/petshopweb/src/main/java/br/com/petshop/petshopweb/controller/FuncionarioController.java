
package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.model.Funcionario;
import br.com.petshop.petshopweb.repository.FuncionarioRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioController(
            FuncionarioRepository funcionarioRepository) {

        this.funcionarioRepository = funcionarioRepository;
    }

    // LISTAR FUNCIONARIOS
    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "funcionarios",
                funcionarioRepository.findAll()
        );

        return "funcionarios/lista";
    }

    // ABRIR FORMULARIO DE CADASTRO
    @GetMapping("/novo")
    public String novo(Model model) {

        model.addAttribute(
                "funcionario",
                new Funcionario()
        );

        return "funcionarios/formulario";
    }

    // CADASTRAR OU ATUALIZAR FUNCIONARIO
    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Funcionario funcionario,
            RedirectAttributes redirectAttributes) {

        funcionarioRepository.save(funcionario);

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "Funcionário salvo com sucesso!"
        );

        return "redirect:/funcionarios";
    }

    // ABRIR FORMULARIO DE EDICAO
    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {

        Funcionario funcionario = funcionarioRepository
                .findById(id)
                .orElse(null);

        if (funcionario == null) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Funcionário não encontrado."
            );

            return "redirect:/funcionarios";
        }

        model.addAttribute("funcionario", funcionario);

        return "funcionarios/formulario";
    }

    // EXCLUIR FUNCIONARIO
    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        if (!funcionarioRepository.existsById(id)) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Funcionário não encontrado."
            );

            return "redirect:/funcionarios";
        }

        try {

            funcionarioRepository.deleteById(id);
            funcionarioRepository.flush();

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

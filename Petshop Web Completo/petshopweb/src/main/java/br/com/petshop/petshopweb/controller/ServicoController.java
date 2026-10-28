
package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.model.Servico;
import br.com.petshop.petshopweb.service.ServicoService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(
            ServicoService servicoService) {

        this.servicoService = servicoService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "servicos",
                servicoService.obterTodos()
        );

        return "servicos/lista";
    }


    @GetMapping("/novo")
    public String novo(Model model) {

        if (!model.containsAttribute("servico")) {
            model.addAttribute("servico", new Servico());
        }

        return "servicos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Servico servico,
            RedirectAttributes redirectAttributes) {

        try {

            servicoService.salvar(servico);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Serviço salvo com sucesso!"
            );

            return "redirect:/servicos";

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );

            redirectAttributes.addFlashAttribute(
                    "servico",
                    servico
            );

            if (servico.getIdServico() != null) {
                return "redirect:/servicos/editar/"
                        + servico.getIdServico();
            }

            return "redirect:/servicos/novo";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model) {

        if (!model.containsAttribute("servico")) {

            Servico servico = servicoService
                    .obterPeloId(id)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Serviço não encontrado: " + id
                            )
                    );

            model.addAttribute("servico", servico);
        }

        return "servicos/formulario";
    }


    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        if (!servicoService.existePeloId(id)) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Serviço não encontrado."
            );

            return "redirect:/servicos";
        }

        try {

            servicoService.deletarPeloId(id);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Serviço excluído com sucesso!"
            );

        } catch (DataIntegrityViolationException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Não é possível excluir este serviço, " +
                            "pois existem agendamentos vinculados a ele."
            );
        }

        return "redirect:/servicos";
    }
}


package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.model.Servico;
import br.com.petshop.petshopweb.repository.ServicoRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoRepository servicoRepository;

    public ServicoController(
            ServicoRepository servicoRepository) {

        this.servicoRepository = servicoRepository;
    }

    // LISTAR TODOS OS SERVICOS
    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "servicos",
                servicoRepository.findAll()
        );

        return "servicos/lista";
    }

    // ABRIR FORMULARIO DE CADASTRO
    @GetMapping("/novo")
    public String novo(Model model) {

        model.addAttribute(
                "servico",
                new Servico()
        );

        return "servicos/formulario";
    }

    // CADASTRAR OU ATUALIZAR SERVICO
    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Servico servico,
            RedirectAttributes redirectAttributes) {

        servicoRepository.save(servico);

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "Serviço salvo com sucesso!"
        );

        return "redirect:/servicos";
    }

    // ABRIR FORMULARIO DE EDICAO
    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {

        Servico servico = servicoRepository
                .findById(id)
                .orElse(null);

        if (servico == null) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Serviço não encontrado."
            );

            return "redirect:/servicos";
        }

        model.addAttribute("servico", servico);

        return "servicos/formulario";
    }

    // EXCLUIR SERVICO
    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        if (!servicoRepository.existsById(id)) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Serviço não encontrado."
            );

            return "redirect:/servicos";
        }

        try {

            servicoRepository.deleteById(id);
            servicoRepository.flush();

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

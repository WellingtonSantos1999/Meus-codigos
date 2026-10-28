
package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.model.Agendamento;
import br.com.petshop.petshopweb.model.Pagamento;

import br.com.petshop.petshopweb.service.AgendamentoService;
import br.com.petshop.petshopweb.service.PagamentoService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final AgendamentoService agendamentoService;

    public PagamentoController(
            PagamentoService pagamentoService,
            AgendamentoService agendamentoService) {

        this.pagamentoService = pagamentoService;
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "pagamentos",
                pagamentoService.obterTodos()
        );

        return "pagamentos/lista";
    }


    @GetMapping("/novo")
    public String novo(Model model) {

        if (!model.containsAttribute("pagamento")) {

            Pagamento pagamento = new Pagamento();
            pagamento.setData(LocalDate.now());
            pagamento.setStatus("Pendente");

            model.addAttribute("pagamento", pagamento);
        }

        model.addAttribute(
                "agendamentos",
                agendamentoService.obterTodos()
        );

        return "pagamentos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Pagamento pagamento,
            @RequestParam(required = false) Long idAgendamento,
            RedirectAttributes redirectAttributes) {

        try {

            if (idAgendamento != null) {

                pagamento.setAgendamento(
                        agendamentoService
                                .obterPeloId(idAgendamento)
                                .orElse(null)
                );

            } else {
                pagamento.setAgendamento(null);
            }

            if (pagamento.getStatus() == null ||
                    pagamento.getStatus().isBlank()) {

                pagamento.setStatus("Pendente");
            }

            pagamentoService.salvar(pagamento);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Pagamento salvo com sucesso!"
            );

            return "redirect:/pagamentos";

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );

            redirectAttributes.addFlashAttribute(
                    "pagamento",
                    pagamento
            );

            if (pagamento.getIdPagamento() != null) {
                return "redirect:/pagamentos/editar/"
                        + pagamento.getIdPagamento();
            }

            return "redirect:/pagamentos/novo";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model) {

        if (!model.containsAttribute("pagamento")) {

            Pagamento pagamento = pagamentoService
                    .obterPeloId(id)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Pagamento não encontrado: " + id
                            )
                    );

            model.addAttribute("pagamento", pagamento);
        }

        model.addAttribute(
                "agendamentos",
                agendamentoService.obterTodos()
        );

        return "pagamentos/formulario";
    }


    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        if (!pagamentoService.existePeloId(id)) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Pagamento não encontrado."
            );

            return "redirect:/pagamentos";
        }

        try {

            pagamentoService.deletarPeloId(id);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Pagamento excluído com sucesso!"
            );

        } catch (DataIntegrityViolationException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Não foi possível excluir este pagamento."
            );
        }

        return "redirect:/pagamentos";
    }

    private void carregarListas(Model model) {

        model.addAttribute(
                "agendamentos",
                agendamentoService.obterTodos()
        );
    }
}

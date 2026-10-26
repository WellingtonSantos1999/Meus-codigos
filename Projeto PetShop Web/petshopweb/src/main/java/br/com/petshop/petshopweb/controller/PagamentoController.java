
package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.model.Agendamento;
import br.com.petshop.petshopweb.model.Pagamento;

import br.com.petshop.petshopweb.repository.AgendamentoRepository;
import br.com.petshop.petshopweb.repository.PagamentoRepository;

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

    private final PagamentoRepository pagamentoRepository;
    private final AgendamentoRepository agendamentoRepository;

    // CONSTRUTOR

    public PagamentoController(
            PagamentoRepository pagamentoRepository,
            AgendamentoRepository agendamentoRepository) {

        this.pagamentoRepository = pagamentoRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    // LISTAR PAGAMENTOS

    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "pagamentos",
                pagamentoRepository.findAll()
        );

        return "pagamentos/lista";
    }

    // ABRIR FORMULARIO DE CADASTRO

    @GetMapping("/novo")
    public String novo(Model model) {

        Pagamento pagamento = new Pagamento();

        pagamento.setData(LocalDate.now());
        pagamento.setStatus("Pendente");

        model.addAttribute("pagamento", pagamento);

        carregarListas(model);

        return "pagamentos/formulario";
    }

    // SALVAR PAGAMENTO

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Pagamento pagamento,
            @RequestParam Long idAgendamento,
            RedirectAttributes redirectAttributes) {

        Optional<Agendamento> agendamento =
                agendamentoRepository.findById(idAgendamento);

        if (agendamento.isEmpty()) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Agendamento não encontrado."
            );

            return "redirect:/pagamentos";
        }

        pagamento.setAgendamento(agendamento.get());

        if (pagamento.getStatus() == null
                || pagamento.getStatus().isBlank()) {

            pagamento.setStatus("Pendente");
        }

        pagamentoRepository.save(pagamento);

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "Pagamento salvo com sucesso!"
        );

        return "redirect:/pagamentos";
    }

    // ABRIR FORMULARIO DE EDICAO

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {

        Pagamento pagamento =
                pagamentoRepository.findById(id).orElse(null);

        if (pagamento == null) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Pagamento não encontrado."
            );

            return "redirect:/pagamentos";
        }

        model.addAttribute("pagamento", pagamento);

        carregarListas(model);

        return "pagamentos/formulario";
    }

    // EXCLUIR PAGAMENTO

    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        if (!pagamentoRepository.existsById(id)) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Pagamento não encontrado."
            );

            return "redirect:/pagamentos";
        }

        try {

            pagamentoRepository.deleteById(id);
            pagamentoRepository.flush();

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

    // CARREGAR AGENDAMENTOS

    private void carregarListas(Model model) {

        model.addAttribute(
                "agendamentos",
                agendamentoRepository.findAll()
        );
    }
}

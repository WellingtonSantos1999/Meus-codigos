
package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.model.Cliente;
import br.com.petshop.petshopweb.service.ClienteService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "clientes",
                clienteService.obterTodos()
        );

        return "clientes/lista";
    }


    @GetMapping("/novo")
    public String novo(Model model) {

        if (!model.containsAttribute("cliente")) {
            model.addAttribute("cliente", new Cliente());
        }

        return "clientes/formulario";
    }


    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Cliente cliente,
            RedirectAttributes redirectAttributes) {

        try {

            clienteService.salvar(cliente);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Cliente salvo com sucesso!"
            );

            return "redirect:/clientes";

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );

            redirectAttributes.addFlashAttribute(
                    "cliente",
                    cliente
            );

            if (cliente.getIdCliente() != null) {
                return "redirect:/clientes/editar/" + cliente.getIdCliente();
            }

            return "redirect:/clientes/novo";
        }
    }


    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model) {

        if (!model.containsAttribute("cliente")) {

            Cliente cliente = clienteService
                    .obterPeloId(id)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Cliente não encontrado: " + id
                            )
                    );

            model.addAttribute("cliente", cliente);
        }

        return "clientes/formulario";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        try {

            clienteService.deletarPeloId(id);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Cliente excluído com sucesso!"
            );

        } catch (DataIntegrityViolationException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Não é possível excluir este cliente, " +
                            "pois existem outros dados vinculados a ele."
            );
        }

        return "redirect:/clientes";
    }
}

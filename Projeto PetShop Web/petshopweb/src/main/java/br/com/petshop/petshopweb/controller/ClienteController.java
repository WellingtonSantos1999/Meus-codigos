
package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.model.Cliente;
import br.com.petshop.petshopweb.repository.ClienteRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.dao.DataIntegrityViolationException;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // LISTAR CLIENTES
    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "clientes",
                clienteRepository.findAll()
        );

        return "clientes/lista";
    }

    // ABRIR FORMULARIO DE CADASTRO
    @GetMapping("/novo")
    public String novo(Model model) {

        model.addAttribute("cliente", new Cliente());

        return "clientes/formulario";
    }

    // SALVAR CLIENTE (CADASTRAR OU EDITAR)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cliente cliente) {

        clienteRepository.save(cliente);

        return "redirect:/clientes";
    }

    // ABRIR FORMULARIO DE EDICAO
    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model) {

        Cliente cliente = clienteRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Cliente não encontrado: " + id
                        )
                );

        model.addAttribute("cliente", cliente);

        return "clientes/formulario";
    }

    // EXCLUIR CLIENTE
    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {

        try {

            clienteRepository.deleteById(id);
            clienteRepository.flush();

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


package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.service.ClienteService;
import br.com.petshop.petshopweb.service.PetService;
import br.com.petshop.petshopweb.service.FuncionarioService;
import br.com.petshop.petshopweb.service.ServicoService;
import br.com.petshop.petshopweb.service.AgendamentoService;
import br.com.petshop.petshopweb.service.PagamentoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ClienteService clienteService;
    private final PetService petService;
    private final FuncionarioService funcionarioService;
    private final ServicoService servicoService;
    private final AgendamentoService agendamentoService;
    private final PagamentoService pagamentoService;

    public HomeController(
            ClienteService clienteService,
            PetService petService,
            FuncionarioService funcionarioService,
            ServicoService servicoService,
            AgendamentoService agendamentoService,
            PagamentoService pagamentoService) {

        this.clienteService = clienteService;
        this.petService = petService;
        this.funcionarioService = funcionarioService;
        this.servicoService = servicoService;
        this.agendamentoService = agendamentoService;
        this.pagamentoService = pagamentoService;
    }

    @GetMapping("/")
    public String inicio(Model model) {

        model.addAttribute(
                "totalClientes",
                clienteService.contar()
        );

        model.addAttribute(
                "totalPets",
                petService.contar()
        );

        model.addAttribute(
                "totalFuncionarios",
                funcionarioService.contar()
        );

        model.addAttribute(
                "totalServicos",
                servicoService.contar()
        );

        model.addAttribute(
                "totalAgendamentos",
                agendamentoService.contar()
        );

        model.addAttribute(
                "totalPagamentos",
                pagamentoService.contar()
        );

        return "index";
    }
}

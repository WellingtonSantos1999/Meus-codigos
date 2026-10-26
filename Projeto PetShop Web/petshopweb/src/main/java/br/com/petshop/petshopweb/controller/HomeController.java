
package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.repository.ClienteRepository;
import br.com.petshop.petshopweb.repository.PetRepository;
import br.com.petshop.petshopweb.repository.FuncionarioRepository;
import br.com.petshop.petshopweb.repository.ServicoRepository;
import br.com.petshop.petshopweb.repository.AgendamentoRepository;
import br.com.petshop.petshopweb.repository.PagamentoRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ClienteRepository clienteRepository;
    private final PetRepository petRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ServicoRepository servicoRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final PagamentoRepository pagamentoRepository;

    public HomeController(
            ClienteRepository clienteRepository,
            PetRepository petRepository,
            FuncionarioRepository funcionarioRepository,
            ServicoRepository servicoRepository,
            AgendamentoRepository agendamentoRepository,
            PagamentoRepository pagamentoRepository) {

        this.clienteRepository = clienteRepository;
        this.petRepository = petRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.servicoRepository = servicoRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    @GetMapping("/")
    public String inicio(Model model) {

        model.addAttribute(
                "totalClientes",
                clienteRepository.count()
        );

        model.addAttribute(
                "totalPets",
                petRepository.count()
        );

        model.addAttribute(
                "totalFuncionarios",
                funcionarioRepository.count()
        );

        model.addAttribute(
                "totalServicos",
                servicoRepository.count()
        );

        model.addAttribute(
                "totalAgendamentos",
                agendamentoRepository.count()
        );

        model.addAttribute(
                "totalPagamentos",
                pagamentoRepository.count()
        );

        return "index";
    }
}

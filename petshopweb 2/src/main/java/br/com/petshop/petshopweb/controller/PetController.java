
package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.model.Cliente;
import br.com.petshop.petshopweb.model.Pet;

import br.com.petshop.petshopweb.service.ClienteService;
import br.com.petshop.petshopweb.service.PetService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final ClienteService clienteService;

    public PetController(
            PetService petService,
            ClienteService clienteService) {

        this.petService = petService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "pets",
                petService.obterTodos()
        );

        return "pets/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {

        if (!model.containsAttribute("pet")) {
            model.addAttribute("pet", new Pet());
        }

        model.addAttribute(
                "clientes",
                clienteService.obterTodos()
        );

        return "pets/formulario";
    }



    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Pet pet,
            @RequestParam Long idCliente,
            RedirectAttributes redirectAttributes) {

        Cliente cliente = clienteService
                .obterPeloId(idCliente)
                .orElse(null);

        if (cliente == null) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Cliente não encontrado. Selecione um dono válido."
            );

            redirectAttributes.addFlashAttribute("pet", pet);

            return "redirect:/pets/novo";
        }

        pet.setCliente(cliente);

        try {

            petService.salvar(pet);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Pet salvo com sucesso!"
            );

            return "redirect:/pets";

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );

            redirectAttributes.addFlashAttribute(
                    "pet",
                    pet
            );

            if (pet.getIdPet() != null) {
                return "redirect:/pets/editar/" + pet.getIdPet();
            }

            return "redirect:/pets/novo";
        }
    }


    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (!petService.existePeloId(id)) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Pet não encontrado."
            );

            return "redirect:/pets";
        }

        if (!model.containsAttribute("pet")) {

            Pet pet = petService
                    .obterPeloId(id)
                    .orElse(null);

            model.addAttribute("pet", pet);
        }

        model.addAttribute(
                "clientes",
                clienteService.obterTodos()
        );

        return "pets/formulario";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        if (!petService.existePeloId(id)) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Pet não encontrado."
            );

            return "redirect:/pets";
        }

        try {

            petService.deletarPeloId(id);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Pet excluído com sucesso!"
            );

        } catch (DataIntegrityViolationException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Não é possível excluir este Pet, " +
                            "pois existem agendamentos vinculados a ele."
            );
        }

        return "redirect:/pets";
    }
}

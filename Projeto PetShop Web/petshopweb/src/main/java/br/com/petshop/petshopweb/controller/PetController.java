
package br.com.petshop.petshopweb.controller;

import br.com.petshop.petshopweb.model.Cliente;
import br.com.petshop.petshopweb.model.Pet;

import br.com.petshop.petshopweb.repository.ClienteRepository;
import br.com.petshop.petshopweb.repository.PetRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetRepository petRepository;
    private final ClienteRepository clienteRepository;

    public PetController(
            PetRepository petRepository,
            ClienteRepository clienteRepository) {

        this.petRepository = petRepository;
        this.clienteRepository = clienteRepository;
    }

    // LISTAR TODOS OS PETS
    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "pets",
                petRepository.findAll()
        );

        return "pets/lista";
    }

    // ABRIR FORMULARIO DE CADASTRO
    @GetMapping("/novo")
    public String novo(Model model) {

        model.addAttribute("pet", new Pet());

        model.addAttribute(
                "clientes",
                clienteRepository.findAll()
        );

        return "pets/formulario";
    }

    // SALVAR PET (CADASTRAR OU EDITAR)
    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Pet pet,
            @RequestParam Long idCliente,
            RedirectAttributes redirectAttributes) {

        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElse(null);

        if (cliente == null) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Cliente não encontrado. Selecione um dono válido."
            );

            return "redirect:/pets";
        }

        pet.setCliente(cliente);

        petRepository.save(pet);

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "Pet salvo com sucesso!"
        );

        return "redirect:/pets";
    }

    // ABRIR FORMULARIO DE EDICAO
    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {

        Pet pet = petRepository
                .findById(id)
                .orElse(null);

        if (pet == null) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Pet não encontrado."
            );

            return "redirect:/pets";
        }

        model.addAttribute("pet", pet);

        model.addAttribute(
                "clientes",
                clienteRepository.findAll()
        );

        return "pets/formulario";
    }

    // EXCLUIR PET
    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        if (!petRepository.existsById(id)) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Pet não encontrado."
            );

            return "redirect:/pets";
        }

        try {

            petRepository.deleteById(id);
            petRepository.flush();

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

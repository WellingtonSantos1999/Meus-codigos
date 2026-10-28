
package br.com.petshop.petshopweb.service;

import br.com.petshop.petshopweb.model.Pet;
import br.com.petshop.petshopweb.repository.PetRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> obterTodos() {
        return repository.findAll();
    }

    public void salvar(Pet pet) {

        if (pet.getNome() == null ||
                pet.getNome().isBlank()) {

            throw new IllegalArgumentException(
                    "O nome do Pet é obrigatório."
            );
        }

        if (!pet.getNome().matches("[\\p{L} ]+")) {

            throw new IllegalArgumentException(
                    "O nome do Pet deve conter apenas letras."
            );
        }

        if (pet.getEspecie() == null ||
                pet.getEspecie().isBlank()) {

            throw new IllegalArgumentException(
                    "A espécie do Pet é obrigatória."
            );
        }

        if (!pet.getEspecie().matches("[\\p{L} ]+")) {

            throw new IllegalArgumentException(
                    "A espécie deve conter apenas letras."
            );
        }

        if (pet.getRaca() == null ||
                pet.getRaca().isBlank()) {

            throw new IllegalArgumentException(
                    "A raça do Pet é obrigatória."
            );
        }

        if (!pet.getRaca().matches("[\\p{L} '-]+")) {

            throw new IllegalArgumentException(
                    "A raça contém caracteres inválidos."
            );
        }

        if (pet.getIdade() == null) {

            throw new IllegalArgumentException(
                    "A idade do Pet é obrigatória."
            );
        }

        if (pet.getIdade() < 0 || pet.getIdade() > 40) {

            throw new IllegalArgumentException(
                    "A idade do Pet deve estar entre 0 e 40 anos."
            );
        }

        if (pet.getSexo() == null ||
                pet.getSexo().isBlank()) {

            throw new IllegalArgumentException(
                    "O sexo do Pet é obrigatório."
            );
        }

        if (pet.getCliente() == null ||
                pet.getCliente().getIdCliente() == null) {

            throw new IllegalArgumentException(
                    "Selecione um dono para o Pet."
            );
        }

        repository.save(pet);
    }

    public Optional<Pet> obterPeloId(Long id) {
        return repository.findById(id);
    }

    public boolean existePeloId(Long id) {
        return repository.existsById(id);
    }

    public void deletarPeloId(Long id) {
        repository.deleteById(id);
        repository.flush();
    }

    public long contar() {
        return repository.count();
    }
}

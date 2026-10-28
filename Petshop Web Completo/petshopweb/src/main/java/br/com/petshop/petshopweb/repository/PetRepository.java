
package br.com.petshop.petshopweb.repository;

import br.com.petshop.petshopweb.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository
        extends JpaRepository<Pet, Long> {

}


package br.com.petshop.petshopweb.repository;

import br.com.petshop.petshopweb.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository
        extends JpaRepository<Cliente, Long> {

}

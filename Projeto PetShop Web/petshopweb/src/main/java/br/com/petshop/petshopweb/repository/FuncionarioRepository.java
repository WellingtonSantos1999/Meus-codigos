
package br.com.petshop.petshopweb.repository;

import br.com.petshop.petshopweb.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository
        extends JpaRepository<Funcionario, Long> {

}

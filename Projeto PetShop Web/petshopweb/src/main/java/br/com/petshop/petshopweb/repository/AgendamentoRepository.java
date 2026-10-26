
package br.com.petshop.petshopweb.repository;

import br.com.petshop.petshopweb.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository
        extends JpaRepository<Agendamento, Long> {

}

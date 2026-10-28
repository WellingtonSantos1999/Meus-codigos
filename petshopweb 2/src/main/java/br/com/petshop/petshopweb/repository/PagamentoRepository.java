
package br.com.petshop.petshopweb.repository;

import br.com.petshop.petshopweb.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository
        extends JpaRepository<Pagamento, Long> {

}

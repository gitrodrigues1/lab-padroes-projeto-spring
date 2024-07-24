package one.marcos.lab_padroes_projeto_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import one.marcos.lab_padroes_projeto_spring.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}

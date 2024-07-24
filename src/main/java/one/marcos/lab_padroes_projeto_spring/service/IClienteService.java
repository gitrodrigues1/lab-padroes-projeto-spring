package one.marcos.lab_padroes_projeto_spring.service;

import one.marcos.lab_padroes_projeto_spring.model.Cliente;

public interface IClienteService {

    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id);
    void inserir(Cliente cliente);
    void atualizar(Long id, Cliente cliente);
    void deletar(Long id);
}

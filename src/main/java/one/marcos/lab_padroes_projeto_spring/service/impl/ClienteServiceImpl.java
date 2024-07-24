package one.marcos.lab_padroes_projeto_spring.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import one.marcos.lab_padroes_projeto_spring.model.Cliente;
import one.marcos.lab_padroes_projeto_spring.model.Endereco;
import one.marcos.lab_padroes_projeto_spring.repository.ClienteRepository;
import one.marcos.lab_padroes_projeto_spring.repository.EnderecoRepository;
import one.marcos.lab_padroes_projeto_spring.service.IClienteService;
import one.marcos.lab_padroes_projeto_spring.service.IViaCepService;

@Service
public class ClienteServiceImpl implements IClienteService{

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final IViaCepService viaCepService;

    public ClienteServiceImpl(
        ClienteRepository clienteRepository, 
        EnderecoRepository enderecoRepository, 
        IViaCepService viaCepService) 
    {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    
    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }
    
    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
    
    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            if (novoEndereco != null) {
                enderecoRepository.save(novoEndereco);
            }
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}

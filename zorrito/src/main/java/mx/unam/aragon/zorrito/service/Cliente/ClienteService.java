package mx.unam.aragon.zorrito.service.Cliente;

import mx.unam.aragon.zorrito.modelo.Cliente;

import java.util.List;

public interface ClienteService{
    Cliente save(Cliente cliente);
    List<Cliente> findAll();
    void deleteById(Long id);
    Cliente findById(Long id);
}

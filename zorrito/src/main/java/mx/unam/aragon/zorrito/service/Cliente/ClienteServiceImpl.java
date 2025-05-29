package mx.unam.aragon.zorrito.service.Cliente;

import mx.unam.aragon.zorrito.modelo.Cliente;
import mx.unam.aragon.zorrito.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    @Override
    public Cliente findByTelefono(String telefono) {
        return clienteRepository.findByTelefonoCliente(telefono).orElse(null);
    }

    @Override
    public Cliente findByCorreoCliente(String correoCliente) {
        return clienteRepository.findByCorreoCliente(correoCliente).orElse(null);
    }


}

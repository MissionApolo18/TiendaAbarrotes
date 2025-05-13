package mx.unam.aragon.zorrito.service.PedidoDistribuidor;

import mx.unam.aragon.zorrito.modelo.Pago;
import mx.unam.aragon.zorrito.modelo.PedidoDistribuidor;
import mx.unam.aragon.zorrito.repository.PedidoDistribuidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoDisServiceImpl implements PedidoDisService {

    @Autowired
    private PedidoDistribuidorRepository pedidoDisRepository;

    @Override
    @Transactional
    public PedidoDistribuidor save(PedidoDistribuidor pedidoDistribuidor) {
        return pedidoDisRepository.save(pedidoDistribuidor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoDistribuidor> findAll() {
        return pedidoDisRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        pedidoDisRepository.deleteById(id);
    }

    @Override
    public PedidoDistribuidor findById(Long id) {
        Optional<PedidoDistribuidor> op = pedidoDisRepository.findById(id);
        return op.orElse(null);
    }
}

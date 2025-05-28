package mx.unam.aragon.zorrito.service.DetallePedidoDis;

import mx.unam.aragon.zorrito.modelo.CorteInventario;
import mx.unam.aragon.zorrito.modelo.DetallePedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.PedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.Producto;
import mx.unam.aragon.zorrito.repository.DetallePedidoDistribuidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class DetallePedidoDisServiceImpl implements DetallePedidoDistribuidorService {

    @Autowired
    private DetallePedidoDistribuidorRepository detallePedidoRepository;

    @Override
    @Transactional
    public DetallePedidoDistribuidor save(DetallePedidoDistribuidor detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePedidoDistribuidor> findAll() {
        return detallePedidoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        detallePedidoRepository.deleteById(id);
    }

    @Override
    public DetallePedidoDistribuidor findById(Long id) {
        Optional<DetallePedidoDistribuidor> op = detallePedidoRepository.findById(id);
        return op.orElse(null);
    }
    
    @Override
    public boolean existsByPedidoAndProducto(PedidoDistribuidor pedido, Producto producto) {
        return detallePedidoRepository.existsByIdpedidoDisAndIdProductoDist(pedido, producto);
    }
}

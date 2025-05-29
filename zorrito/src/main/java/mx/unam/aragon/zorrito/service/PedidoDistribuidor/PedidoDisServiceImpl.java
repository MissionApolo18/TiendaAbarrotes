package mx.unam.aragon.zorrito.service.PedidoDistribuidor;

import mx.unam.aragon.zorrito.dto.pedido.HistorialPedidoDto;
import mx.unam.aragon.zorrito.modelo.DetallePedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.PedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.Producto;
import mx.unam.aragon.zorrito.repository.DetallePedidoDistribuidorRepository;
import mx.unam.aragon.zorrito.repository.PedidoDistribuidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoDisServiceImpl implements PedidoDisService {

    @Autowired
    private PedidoDistribuidorRepository pedidoDisRepository;
    @Autowired
    private DetallePedidoDistribuidorRepository detallePedidoDistribuidorRepository;

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

    @Override
    public List<HistorialPedidoDto> obtenerHistorialPedidos() {
        List<PedidoDistribuidor> pedidos = pedidoDisRepository.findAll();

        List<HistorialPedidoDto> historial = new ArrayList<>();

        for (PedidoDistribuidor pedido : pedidos) {
            String distribuidor = pedido.getIdDistribuidor().getNombreDistribuidor(); // ajusta si el nombre es diferente
            LocalDate fecha = pedido.getFecha();

            List<DetallePedidoDistribuidor> detalles = detallePedidoDistribuidorRepository.findByPedidoDistribuidor(pedido);

            List<String> productosYcantidades = detalles.stream()
                    .map(det -> det.getProducto().getNombreProducto() + " x" + det.getCantidad())
                    .toList();

            historial.add(new HistorialPedidoDto(distribuidor, fecha, productosYcantidades));
        }

        return historial;
    }
}

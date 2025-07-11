package mx.unam.aragon.zorrito.service.DetalleVenta;

import mx.unam.aragon.zorrito.dto.ItemVentaDto;
import mx.unam.aragon.zorrito.modelo.DetallePedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.DetalleVenta;
import mx.unam.aragon.zorrito.modelo.Venta;
import mx.unam.aragon.zorrito.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    @Transactional
    public DetalleVenta save(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> findAll() {
        return detalleVentaRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        detalleVentaRepository.deleteById(id);
    }

    @Override
    public DetalleVenta findById(Long id) {
        Optional<DetalleVenta> op = detalleVentaRepository.findById(id);
        return op.orElse(null);
    }

    @Override
    public List<DetalleVenta> findByVentas(Venta venta) {
        return detalleVentaRepository.findByVentas(venta);
    }

    public List<ItemVentaDto> obtenerItemsDeVenta(Long id) {
        List<DetalleVenta> detalles = detalleVentaRepository.findByVentas_IdVenta(id);
        List<ItemVentaDto> items = new ArrayList<>();
        for (DetalleVenta detalle : detalles) {
            ItemVentaDto item = new ItemVentaDto();
            item.setNombreProducto(detalle.getProductos().getNombreProducto());
            item.setCantidad(detalle.getCantidad());
            item.setPrecioUnitario(detalle.getPrecioUnitario());
            items.add(item);
        }
        return items;
    }

}

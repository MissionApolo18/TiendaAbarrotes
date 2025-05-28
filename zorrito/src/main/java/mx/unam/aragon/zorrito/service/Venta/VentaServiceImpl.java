package mx.unam.aragon.zorrito.service.Venta;

import mx.unam.aragon.zorrito.dto.HistorialVentaDto;
import mx.unam.aragon.zorrito.modelo.Usuarios;
import mx.unam.aragon.zorrito.modelo.Venta;
import mx.unam.aragon.zorrito.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    @Override
    @Transactional
    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public Venta findById(Long id) {
        Optional<Venta> op = ventaRepository.findById(id);
        return op.orElse(null);
    }

    public List<HistorialVentaDto> obtenerHistorialVentas() {
        List<Venta> ventas = ventaRepository.obtenerVentasConDetalles();
        List<HistorialVentaDto> historial = new ArrayList<>();

        for (Venta venta : ventas) {
            List<String> productosYcantidades = venta.getDetalleVentas().stream()
                    .map(det -> det.getProductos().getNombreProducto() + " x" + det.getCantidad())
                    .toList();

            historial.add(new HistorialVentaDto(
                    venta.getIdUsuarioVenta().getNombreUsuario(),
                    venta.getIdClienteVenta().getNombreCliente(),
                    venta.getFecha(),
                    productosYcantidades,
                    venta.getTotal()
            ));
        }

        return historial;
    }

}

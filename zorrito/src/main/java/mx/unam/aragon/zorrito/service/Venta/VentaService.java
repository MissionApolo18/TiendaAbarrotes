package mx.unam.aragon.zorrito.service.Venta;

import mx.unam.aragon.zorrito.dto.HistorialVentaDto;
import mx.unam.aragon.zorrito.dto.ItemVentaDto;
import mx.unam.aragon.zorrito.modelo.Usuarios;
import mx.unam.aragon.zorrito.modelo.Venta;

import java.util.List;

public interface VentaService {
    Venta save(Venta venta);
    List<Venta> findAll();
    void deleteById(Long id);
    Venta findById(Long id);
    public List<HistorialVentaDto> obtenerHistorialVentas();
    public List<ItemVentaDto> obtenerItemsDeVenta(Long id);
}

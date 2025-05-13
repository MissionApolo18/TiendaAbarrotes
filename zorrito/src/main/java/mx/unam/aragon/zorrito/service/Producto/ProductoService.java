package mx.unam.aragon.zorrito.service.Producto;

import mx.unam.aragon.zorrito.modelo.PedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.Producto;

import java.util.List;

public interface ProductoService {
    Producto save(Producto producto);
    List<Producto> findAll();
    void deleteById(Long id);
    Producto findById(Long id);
}

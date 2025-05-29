package mx.unam.aragon.zorrito.repository;

import mx.unam.aragon.zorrito.dto.HistorialVentaDto;
import mx.unam.aragon.zorrito.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query("SELECT DISTINCT v FROM venta v JOIN FETCH v.detalleVentas d JOIN FETCH d.productos")
    List<Venta> obtenerVentasConDetalles();

}

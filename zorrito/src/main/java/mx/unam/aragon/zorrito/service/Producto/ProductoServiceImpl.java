package mx.unam.aragon.zorrito.service.Producto;


import mx.unam.aragon.zorrito.modelo.PedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.Producto;
import mx.unam.aragon.zorrito.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto findById(Long id) {
        Optional<Producto> op = productoRepository.findById(id);
        return op.orElse(null);
    }
}

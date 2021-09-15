package com.example.demo.service;

import com.example.demo.dao.ProductoRepository;
import com.example.demo.exception.NoEncontradoException;
import com.example.demo.model.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtener(Long id) {
        return productoRepository.getById(id);
    }

    @Override
    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void elminar(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto actualizar(Producto producto) {
        Optional<Producto> productoDB=this.productoRepository.findById(producto.getId());
        if(productoDB.isPresent()){
            Producto productoActualizado = productoDB.get();
            productoActualizado.setId(producto.getId());
            productoActualizado.setCantidad(productoActualizado.getCantidad()-producto.getCantidad());
            productoRepository.save(productoActualizado);
            return productoActualizado;
        } else {
            throw new NoEncontradoException("Producto no encontrado");
        }
    }
}

package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

/*  Con el Autowired así con constructor no funcionaba bien el productoService:

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }*/

    //Ejercicio 1
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        productoService.guardar(producto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //Ejercicio 2 - Como hay solo 1 get en esta ruta, no hace falta usar el endpoint /ListaProductos
    @GetMapping
    public List<Producto> ObtenerTodos() {
        return productoService.obtenerTodos();
    }

    @GetMapping(path = "{id}")
    public Producto obtenerProductoPorId(@PathVariable("id") Long id) {
        return productoService.obtener(id);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> elminarProductoPorId(@PathVariable("id") Long id) {
        productoService.elminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Producto>actualizarProducto(@PathVariable("id") Long id, @RequestBody Producto producto) {
        producto.setId(id);
        producto.setCantidad(producto.getCantidad());
        return ResponseEntity.ok().body(this.productoService.actualizar(producto));
    }

}

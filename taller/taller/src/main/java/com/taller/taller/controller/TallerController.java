package com.taller.taller.controller;

import com.taller.taller.entity.Cliente;
import com.taller.taller.entity.Taller;
import com.taller.taller.service.TallerService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/talleres")
public class TallerController {
    private final TallerService tallerService;

    public TallerController(TallerService tallerService) {
        this.tallerService = tallerService;
    }

    @GetMapping("/todos")
    public List<Taller> listarTodos() {
        return tallerService.listarTodos();
    }

    @GetMapping("/getTaller/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return tallerService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("mensaje", "Taller no encontrado")));
    }

    @GetMapping("nuevoTaller")
    public ResponseEntity<?> agregarTaller(@RequestBody Taller taller) {
        Taller nuevoTaller = tallerService.agregarTaller(taller);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTaller);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> eliminarTaller(@PathVariable Long id) {
        if (tallerService.existePorId(id)) {
            tallerService.eliminarTaller(id);
            return ResponseEntity.ok(Collections.singletonMap("mensaje", "Taller eliminado"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("mensaje", "Taller no encontrado"));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarCompleto(
            @RequestBody Taller taller,
            @PathVariable Long id) {
        return tallerService.buscarPorId(id)
                .<ResponseEntity<?>>map(tallerExistente -> {
                    tallerExistente.setNombre(taller.getNombre());
                    tallerExistente.setNombre(taller.getCalle());
                    tallerExistente.setNombre(taller.getNombreEncargado());

                    // 1:N
                    tallerExistente.getClientes().clear();
                    if (taller.getClientes() != null) {
                        for (Cliente cliente : taller.getClientes()) {
                            cliente.setTaller(tallerExistente);
                            tallerExistente.getClientes().add(cliente);
                        }
                    }

                    //1:1
                    //clienteExistente.setCoche(cliente.getCoche());

                    //Imaginemos, si el cliente puediese estar en muchos talleres y muchos talleres pueden tener a muchos clientes (Un ejemplo horrible la verdad9
                    // N:N
                    //tallerExistente.getClientes().clear();
                    //if (taller.getClientes() != null) {
                    //    for (Cliente cliente : taller.getClientes()) {
                    //        tallerExistente.getClientes().add(cliente);
                    //        cliente.getTalleres().add(tallerExistente);
                    //    }
                    //}


                    Taller tallerActualizado = tallerService.agregarTaller(tallerExistente);
                    return ResponseEntity.ok(tallerActualizado);

                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("mensaje", "Taller no encontrado")));
    }



}

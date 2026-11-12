package com.taller.taller.service;

import com.taller.taller.entity.Taller;
import com.taller.taller.repository.TallerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TallerService {
    private final TallerRepository tallerRepository;

    public TallerService(TallerRepository tallerRepository) {
        this.tallerRepository = tallerRepository;
    }

    public List<Taller> listarTodos(){
        return tallerRepository.findAll();
    }

    public Taller agregarTaller(Taller taller){
        return tallerRepository.save(taller);
    }

    public void eliminarTaller(Long id){
        tallerRepository.deleteById(id);
    }

    public Optional<Taller> buscarPorId(Long id){
        return tallerRepository.findById(id);
    }

    public boolean existePorId(Long id){
        return tallerRepository.existsById(id);
    }

    public List<Taller> buscarPorNombre(String nombre){
        return tallerRepository.findByNombre(nombre);
    }

    public boolean existePorNombre(String nombre){
        return tallerRepository.existsByNombre(nombre);
    }
}

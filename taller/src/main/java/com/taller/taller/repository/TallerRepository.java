package com.taller.taller.repository;

import com.taller.taller.entity.Taller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TallerRepository extends JpaRepository<Taller,Long> {
    List<Taller> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}

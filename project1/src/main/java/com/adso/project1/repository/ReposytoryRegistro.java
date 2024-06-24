package com.adso.project1.repository;

import com.adso.project1.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReposytoryRegistro extends JpaRepository<Registro, Long> {
}


package com.adso.project1.service;

import com.adso.project1.model.Registro;
import com.adso.project1.repository.ReposytoryRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
@Service
public class ServiceRegistro {

    @Autowired
    private ReposytoryRegistro repositoryRegister;

    public List<Registro> getAllRegistro() {
        return repositoryRegister.findAll();
    }

    public Registro saveRegister(Registro registro) {
        return repositoryRegister.save(registro);
    }

    public Optional<Registro> getRegistroById(Long id) {
        return repositoryRegister.findById(id);
    }

    public void deleteRegistro(Long id) {
        repositoryRegister.deleteById(id);
    }

    public Registro updateRegistro(Registro registro) {
        return repositoryRegister.save(registro);
    }
}

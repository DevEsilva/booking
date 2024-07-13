package com.bideafactory.codingtest.Service;

import com.bideafactory.codingtest.Entity.Rol;
import com.bideafactory.codingtest.Enums.RolNombre;
import com.bideafactory.codingtest.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol) {
        rolRepository.save(rol);
    }


}
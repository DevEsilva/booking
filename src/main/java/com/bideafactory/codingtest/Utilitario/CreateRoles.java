package com.bideafactory.codingtest.Utilitario;

import java.util.ArrayList;
import java.util.List;

import com.bideafactory.codingtest.Entity.Rol;
import com.bideafactory.codingtest.Enums.RolNombre;
import com.bideafactory.codingtest.Service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;


    @Override
    public void run(String... args) throws Exception {

/*

        Rol rolsUPERadm = new Rol(RolNombre.ROLE_SUPERADMIN);
        rolService.save(rolsUPERadm);

        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        rolService.save(rolAdmin);

        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        rolService.save(rolUser);*/
    }
}

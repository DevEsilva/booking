package com.bideafactory.codingtest.Repository;


import com.bideafactory.codingtest.Entity.Rol;
import com.bideafactory.codingtest.Enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}

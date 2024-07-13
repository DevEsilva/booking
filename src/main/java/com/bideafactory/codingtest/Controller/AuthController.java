package com.bideafactory.codingtest.Controller;


import com.bideafactory.codingtest.Dto.JwtDto;
import com.bideafactory.codingtest.Dto.LoginUsuario;
import com.bideafactory.codingtest.Dto.NuevoUsuario;
import com.bideafactory.codingtest.Dto.Response;
import com.bideafactory.codingtest.Entity.Rol;
import com.bideafactory.codingtest.Entity.Usuario;
import com.bideafactory.codingtest.Enums.RolNombre;
import com.bideafactory.codingtest.Seguridad.jwt.JwtProvider;
import com.bideafactory.codingtest.Service.RolService;
import com.bideafactory.codingtest.Service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;

@RestController
@RequestMapping("/auth")
@Api(tags = "Login", description = "Metodos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;



    @ApiOperation("Creación de Usuario")
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Response(99,"Error al Crear Usuario",  "Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Response(99,"Error al Crear Usuario",  "ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity(new Response(99,"Error al Crear Usuario",  "ese email ya existe"), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario
                = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        if (nuevoUsuario.getRoles().contains("suadm")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_SUPERADMIN).get());
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Response(0,"Creado Satisfactoriamente",  "Usuario creado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @ApiOperation("Acceso de Usuario")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Response(99,"Error al ingresar Usuario",  "campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication
                = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<Usuario> usuario = usuarioService.getByNombreUsuario(loginUsuario.getNombreUsuario());


        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
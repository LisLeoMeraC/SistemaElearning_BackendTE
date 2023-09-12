package com.sistema.elearning;

import com.sistema.elearning.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SistemaElearningBackendApplication implements CommandLineRunner{

	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(SistemaElearningBackendApplication.class, args);


	}


//probando heroku
	@Override
	public void run(String... args) throws Exception {
		/*Usuario  usuario= new Usuario();
		usuario.setNombre("Lister");
		usuario.setApellido("Mera");
		usuario.setUsername("Lisleo");
		usuario.setContrasenia("123456");
		usuario.setEmail("listermerac@gmail.com");
		usuario.setTelefono("0987490209");
		usuario.setPerfil("Foto.png");

		Rol rol= new Rol();
		rol.setRolID(1L);
		rol.setNombre("ADMIN");

		Set<Usuario_Rol> usuarioRol= new HashSet<>();
		Usuario_Rol usuario_rol=new Usuario_Rol();
		usuario_rol.setRol(rol);
		usuario_rol.setUsuario(usuario);
		usuarioRol.add(usuario_rol);

		Usuario usuarioGuardado= usuarioService.registrarUsuario(usuario,usuarioRol);
		System.out.println(usuarioGuardado.getUsername());
*/
	}


}

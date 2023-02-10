package br.com.springboot.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.test.model.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{

	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
	List<Usuario>buscaPorNome (String nome);
	
	
}

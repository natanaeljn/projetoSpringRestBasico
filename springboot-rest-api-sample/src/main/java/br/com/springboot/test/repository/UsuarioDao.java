package br.com.springboot.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.springboot.test.model.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long>{

	
}

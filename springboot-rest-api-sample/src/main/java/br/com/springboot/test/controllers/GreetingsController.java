package br.com.springboot.test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.test.model.Usuario;
import br.com.springboot.test.repository.UsuarioDao;




/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping("/user")
public class GreetingsController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    @RequestMapping(value = "/ola/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String teste(@PathVariable String name) {
    	Usuario usuario = new Usuario();
    	usuario.setIdade(23);
    	usuario.setNome(name);
    	usuarioDao.save(usuario);
    return "Hello " + name + "!";
    }
    
    @GetMapping(value = "listatodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>>listarUsuarios(){
    	List<Usuario>usuarios = usuarioDao.findAll();
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
    @PostMapping(value = "/salvar")
    @ResponseBody
    public ResponseEntity<Usuario>salvar(@RequestBody Usuario usuario){
    Usuario usuarioSalvar =   usuarioDao.save(usuario);
    return new ResponseEntity<Usuario>(usuarioSalvar , HttpStatus.CREATED);
    	
    }
    
    @DeleteMapping(value = "/deletar")
    @ResponseBody
    public ResponseEntity<String>deletar(@RequestParam Long idUser){
    usuarioDao.deleteById(idUser);
    return new ResponseEntity<String>("usuario deletado" , HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "/buscauserid")
    @ResponseBody
    public ResponseEntity<Usuario>buscaUserId(@RequestParam(name = "idUser") Long idUser){
    Usuario usuarioId =  usuarioDao.findById(idUser).get();
    return new ResponseEntity<Usuario>(usuarioId , HttpStatus.OK);
    
    	
    }
    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?>atualizar(@RequestBody Usuario usuario){
    if(usuario.getId() == null) {
    	return new ResponseEntity<String>("Id nao foi informado" , HttpStatus.OK);
    }
    	
    Usuario usuarioSalvar =   usuarioDao.save(usuario);
    return new ResponseEntity<Usuario>(usuarioSalvar , HttpStatus.OK);
    	
    }
    @GetMapping(value = "/buscausernome")
    @ResponseBody
    public ResponseEntity<List<Usuario>>buscaUserNome(@RequestParam(name = "nome") String nome){
    List<Usuario>lista = usuarioDao.buscaPorNome(nome.trim().toUpperCase());
    return new ResponseEntity<List<Usuario>>(lista , HttpStatus.OK);
    
    	
    }
    
    
}

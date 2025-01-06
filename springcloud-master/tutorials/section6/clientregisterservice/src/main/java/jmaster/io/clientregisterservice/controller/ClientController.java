package jmaster.io.clientregisterservice.controller;

import java.util.List;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jmaster.io.clientregisterservice.entity.ClientDetail;
import jmaster.io.clientregisterservice.repository.ClientDetailRepository;

//@PreAuthorize("#oauth2.hasScope('write') && hasRole('ADMIN')")
@PermitAll
@RestController
public class ClientController {
    @Autowired
    private ClientDetailRepository clientDetailRepository;
    
    @PostMapping("/clientdetail")
    public ClientDetail addAccount(@RequestBody ClientDetail clientDetail) {
	clientDetail.setClientSecret(new BCryptPasswordEncoder().encode(clientDetail.getClientSecret()));
	clientDetailRepository.save(clientDetail);

	return clientDetail;
    }

    // get all
    @GetMapping("/clientdetails")
    public List<ClientDetail> getAll() {
	return clientDetailRepository.findAll();
    }

    @DeleteMapping("/clientdetail")
    public void delete(@RequestParam(name = "clientId") String clientId) {
	clientDetailRepository.deleteById(clientId);
    }
}

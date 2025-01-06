package accountservice.accountservice.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.PermitAll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import accountservice.accountservice.client.NotificationService;
import accountservice.accountservice.client.StatisticService;
import accountservice.accountservice.model.AccountDTO;
import accountservice.accountservice.model.MessageDTO;
import accountservice.accountservice.model.StatisticDTO;
import accountservice.accountservice.service.AccountService;

@RestController
public class AccountController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    NotificationService notificationService;

    // add new
    @PostMapping("/register")
    @PermitAll
    public AccountDTO register(@RequestBody AccountDTO accountDTO) {
	accountDTO.setRoles(new HashSet<String>(Arrays.asList("ROLE_USER")));
	accountService.add(accountDTO);

	statisticService.add(new StatisticDTO("Account " + accountDTO.getUsername() + " is created", new Date()));

	// send notificaiton
	MessageDTO messageDTO = new MessageDTO();
	messageDTO.setFrom("jmaster.io@gmail.com");
	messageDTO.setTo(accountDTO.getEmail());// username is email
	messageDTO.setToName(accountDTO.getName());
	messageDTO.setSubject("Welcome to JMaster.io");
	messageDTO.setContent("JMaster is online learning platform.");

	notificationService.sendNotification(messageDTO);

	return accountDTO;
    }
    
    @PreAuthorize("#oauth2.hasScope('write') && hasRole('ADMIN')")
    @PostMapping("/account")
    public AccountDTO addAccount(@RequestBody AccountDTO accountDTO) {
	accountService.add(accountDTO);

	statisticService.add(new StatisticDTO("Account " + accountDTO.getUsername() + " is created", new Date()));

	// send notificaiton
	MessageDTO messageDTO = new MessageDTO();
	messageDTO.setFrom("jmaster.io@gmail.com");
	messageDTO.setTo(accountDTO.getEmail());// username is email
	messageDTO.setToName(accountDTO.getName());
	messageDTO.setSubject("Welcome to JMaster.io");
	messageDTO.setContent("JMaster is online learning platform.");

	notificationService.sendNotification(messageDTO);

	return accountDTO;
    }

    // get all
    @PreAuthorize("#oauth2.hasScope('read') && hasRole('ADMIN')")
    @GetMapping("/accounts")
    public List<AccountDTO> getAll() {
	logger.info("AccountService Controller: get all accounts");

	statisticService.add(new StatisticDTO("Get all accounts", new Date()));

	return accountService.getAll();
    }

    @PreAuthorize("#oauth2.hasScope('read') && isAuthenticated()")
    @PostAuthorize("returnObject.body.username == authentication.name || hasRole('ADMIN')")
    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDTO> get(@PathVariable(name = "id") Long id) {
	return Optional.of(new ResponseEntity<AccountDTO>(accountService.getOne(id), HttpStatus.OK))
		.orElse(new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("#oauth2.hasScope('write') && hasRole('ADMIN')")
    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
	statisticService.add(new StatisticDTO("Delete account id " + id, new Date()));

	accountService.delete(id);
    }

    @PreAuthorize("#oauth2.hasScope('write') && hasRole('ADMIN')")
    @PutMapping("/account")
    public void update(@RequestBody AccountDTO accountDTO) {
	statisticService.add(new StatisticDTO("Update account: " + accountDTO.getUsername(), new Date()));

	accountService.update(accountDTO);
    }

    @PreAuthorize("#oauth2.hasScope('read') && isAuthenticated()")
    @GetMapping("/me")
    public Principal me(Principal principal, @RequestHeader("Authorization") String bearerToken) {
	return principal;
    }
}

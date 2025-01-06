package jmaster.io.clientregisterservice.repository;

import jmaster.io.clientregisterservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);

    void deleteByClientId(String clientId);
}

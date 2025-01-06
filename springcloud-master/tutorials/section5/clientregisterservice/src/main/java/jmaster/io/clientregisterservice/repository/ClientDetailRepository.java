package jmaster.io.clientregisterservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jmaster.io.clientregisterservice.entity.ClientDetail;

public interface ClientDetailRepository extends JpaRepository<ClientDetail, String>{
}

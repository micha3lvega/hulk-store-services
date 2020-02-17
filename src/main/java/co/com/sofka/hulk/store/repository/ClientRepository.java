package co.com.sofka.hulk.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.sofka.hulk.store.services.data.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}

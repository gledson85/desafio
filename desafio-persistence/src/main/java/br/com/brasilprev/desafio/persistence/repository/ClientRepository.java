package br.com.brasilprev.desafio.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brasilprev.desafio.persistence.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByNameAndCpf(String name, String cpf);

}

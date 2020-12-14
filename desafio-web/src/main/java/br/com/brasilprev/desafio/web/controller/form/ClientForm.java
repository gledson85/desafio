package br.com.brasilprev.desafio.web.controller.form;

import br.com.brasilprev.desafio.persistence.model.Client;
import br.com.brasilprev.desafio.persistence.repository.ClientRepository;

public class ClientForm {

    private String name;
    private String cpf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Client converter(ClientRepository clientRepository) {
        return clientRepository.findByNameAndCpf(name, cpf);
    }

}

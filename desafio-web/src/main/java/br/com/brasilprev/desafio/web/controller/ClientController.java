package br.com.brasilprev.desafio.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasilprev.desafio.persistence.model.Client;
import br.com.brasilprev.desafio.persistence.repository.ClientRepository;
import br.com.brasilprev.desafio.web.controller.dto.ClientDto;
import br.com.brasilprev.desafio.web.controller.form.ClientForm;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<ClientDto> add(@RequestBody @Valid ClientForm form) {

        String msg = null;

        Client client = form.converter(clientRepository);

        if (client == null) {
            client = new Client(form.getName(), form.getCpf());
            clientRepository.save(client);
        } else {
            msg = "customer already registered";
        }

        return ResponseEntity.ok(new ClientDto(client.getName(), client.getCpf(), msg));
    }

}

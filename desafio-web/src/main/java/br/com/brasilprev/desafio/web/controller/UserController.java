package br.com.brasilprev.desafio.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasilprev.desafio.persistence.model.User;
import br.com.brasilprev.desafio.persistence.repository.UserRepository;
import br.com.brasilprev.desafio.web.controller.dto.UserDto;
import br.com.brasilprev.desafio.web.controller.form.UserForm;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<UserDto> add(@RequestBody @Valid UserForm form) {

        String msg = "";

        final User user = form.converter(userRepository);
        if (user != null) {
            msg = "User already registered!";
        } else {
            userRepository.save(new User(form.getEmail(), form.getPass()));
            msg = "User save!";
        }
        return ResponseEntity.ok(new UserDto(form.getEmail(), msg));
    }

}

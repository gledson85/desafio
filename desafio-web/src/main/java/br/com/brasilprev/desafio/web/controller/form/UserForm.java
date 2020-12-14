package br.com.brasilprev.desafio.web.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.brasilprev.desafio.persistence.model.User;
import br.com.brasilprev.desafio.persistence.repository.UserRepository;

public class UserForm {

    @NotNull
    @NotEmpty
    @Length(min = 7)
    private String email;

    @NotNull
    @NotEmpty
    @Length(min = 7)
    private String pass;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public User converter(UserRepository userRepository) {
        return userRepository.findByName(email);
    }

}

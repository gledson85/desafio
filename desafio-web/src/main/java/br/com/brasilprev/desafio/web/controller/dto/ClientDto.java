package br.com.brasilprev.desafio.web.controller.dto;

public class ClientDto {

    private String name;
    private String cpf;
    private String msg;

    public ClientDto() {
        super();
    }

    public ClientDto(String name, String cpf, String msg) {
        super();
        this.name = name;
        this.cpf = cpf;
        this.msg = msg;
    }

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}

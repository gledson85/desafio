package br.com.brasilprev.desafio.web.controller.dto;

public class UserDto {

    private String user;
    private String msg;

    public UserDto() {
        super();
    }

    public UserDto(String user, String msg) {
        super();
        this.user = user;
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}

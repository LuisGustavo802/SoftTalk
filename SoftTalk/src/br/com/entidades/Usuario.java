
package br.com.entidades;

public class Usuario {
    Integer idusuario, idpessoa;
    String login, senha, flagativo;

    public Integer getIdusuario() {
        return idusuario;
    }


    public Integer getIdpessoa() {
        return idpessoa;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFlagativo() {
        return flagativo;
    }

    public void setFlagativo(String flagativo) {
        this.flagativo = flagativo;
    }

    
}

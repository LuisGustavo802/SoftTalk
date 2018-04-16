
package br.com.setor;

public class Setor {
    Integer idsetor;
    String nome, flagativo;

    public Integer getIdsetor() {
        return idsetor;
    }

    public void setIdsetor(Integer idsetor) {
        this.idsetor = idsetor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFlagativo() {
        return flagativo;
    }

    public void setFlagativo(String flagativo) {
        this.flagativo = flagativo;
    }

    @Override
    public String toString() {
        return getNome();
    }
    
    

    
}

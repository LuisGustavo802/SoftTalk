
package br.com.setor;

public class Setor {
    Integer idsetor, quant, idempresa=1;
    String nome, flagativo;

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

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

    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }

    
    @Override
    public String toString() {
        return getNome();
    }

    
}

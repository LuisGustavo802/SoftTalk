package br.com.pessoa;

import br.com.Utils.ManipularImagem;
import java.awt.image.BufferedImage;

public class Pessoa {

    private Integer idpessoa, idsetor;
    private BufferedImage imagem;
    private String nome;
    private final ManipularImagem manipulaImagem;

    public Pessoa() {
        this.manipulaImagem = new ManipularImagem();
    }

    public void setIdpessoa(Integer idpessoa) {
        this.idpessoa = idpessoa;
    }

    public Integer getIdpessoa() {
        return idpessoa;
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(String Caminho, int x, int y) {
        this.imagem = ManipularImagem.setImagemDimensao(Caminho, x, y);
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
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

    @Override
    public String toString() {
        return nome;
    }

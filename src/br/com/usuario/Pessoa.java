
package br.com.usuario;

import br.com.Utils.ManipularImagem;
import java.awt.image.BufferedImage;

public class Pessoa {
    private Integer idpessoa , idsetor;
    private BufferedImage imagem;
    private String nome;
    private ManipularImagem manipulaImagem;
   
    public Pessoa( ) {
         manipulaImagem = new ManipularImagem();
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
        this.imagem = manipulaImagem.setImagemDimensao(Caminho, x, y);
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
  
  
}

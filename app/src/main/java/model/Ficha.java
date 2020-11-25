package model;

import java.io.Serializable;

public class Ficha implements Serializable {

    private String nomeDoPersonagem;
    private Integer nivel;
    private Integer pvAtuais;
    private String idJogador;
    private String id;
    private String classe;
    private String raca;


    public Ficha() {
    }

    public Ficha(String nomeDoPersonagem, Integer nivel, Integer pvAtuais, String idJogador, String id, String classe, String raca) {
        this.nomeDoPersonagem = nomeDoPersonagem;
        this.nivel = nivel;
        this.pvAtuais = pvAtuais;
        this.idJogador = idJogador;
        this.id = id;
        this.classe = classe;
        this.raca = raca;
    }

    public String getNomeDoPersonagem() {
        return nomeDoPersonagem;
    }

    public void setNomeDoPersonagem(String nomeDoPersonagem) {
        this.nomeDoPersonagem = nomeDoPersonagem;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getPvAtuais() {
        return pvAtuais;
    }

    public void setPvAtuais(Integer pvAtuais) {
        this.pvAtuais = pvAtuais;
    }

    public String getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(String idJogador) {
        this.idJogador = idJogador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    @Override
    public String toString() {
        return "Ficha{" +
                "nomeDoPersonagem='" + nomeDoPersonagem + '\'' +
                ", nivel=" + nivel +
                ", pvAtuais=" + pvAtuais +
                ", idJogador='" + idJogador + '\'' +
                ", id='" + id + '\'' +
                ", classe='" + classe + '\'' +
                ", raca='" + raca + '\'' +
                '}';
    }
}

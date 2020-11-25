package model;

import java.io.Serializable;
import java.util.List;

public class Sessoes implements Serializable {

    private List<Sessao> sessao;

    public Sessoes(List<Sessao> sessao) {
        this.sessao = sessao;
    }

    public Sessoes() {
    }

    public List<Sessao> getSessao() {
        return sessao;
    }

    public void setSessao(List<Sessao> sessao) {
        this.sessao = sessao;
    }
}



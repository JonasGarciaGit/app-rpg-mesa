package model;

import java.io.Serializable;

public class Slot implements Serializable {

    private Ficha ficha;
    private String status;

    public Slot() {
    }

    public Slot(Ficha ficha, String status) {
        this.ficha = ficha;
        this.status = status;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "ficha=" + ficha +
                ", status='" + status + '\'' +
                '}';
    }
}

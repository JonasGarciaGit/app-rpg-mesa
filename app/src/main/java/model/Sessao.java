package model;

import java.io.Serializable;

public class Sessao  implements Serializable {

    private Slot slot1;
    private Slot slot2;
    private Slot slot3;
    private Slot slot4;
    private Slot slot5;
    private Slot slot6;

    public Sessao() {
    }

    public Sessao(Slot slot1, Slot slot2, Slot slot3, Slot slot4, Slot slot5, Slot slot6) {
        this.slot1 = slot1;
        this.slot2 = slot2;
        this.slot3 = slot3;
        this.slot4 = slot4;
        this.slot5 = slot5;
        this.slot6 = slot6;
    }

    public Slot getSlot1() {
        return slot1;
    }

    public void setSlot1(Slot slot1) {
        this.slot1 = slot1;
    }

    public Slot getSlot2() {
        return slot2;
    }

    public void setSlot2(Slot slot2) {
        this.slot2 = slot2;
    }

    public Slot getSlot3() {
        return slot3;
    }

    public void setSlot3(Slot slot3) {
        this.slot3 = slot3;
    }

    public Slot getSlot4() {
        return slot4;
    }

    public void setSlot4(Slot slot4) {
        this.slot4 = slot4;
    }

    public Slot getSlot5() {
        return slot5;
    }

    public void setSlot5(Slot slot5) {
        this.slot5 = slot5;
    }

    public Slot getSlot6() {
        return slot6;
    }

    public void setSlot6(Slot slot6) {
        this.slot6 = slot6;
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "slot1=" + slot1 +
                ", slot2=" + slot2 +
                ", slot3=" + slot3 +
                ", slot4=" + slot4 +
                ", slot5=" + slot5 +
                ", slot6=" + slot6 +
                '}';
    }
}

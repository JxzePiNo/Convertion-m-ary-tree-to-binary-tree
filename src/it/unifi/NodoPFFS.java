package it.unifi;

public class NodoPFFS <T>{
    private NodoPFFS<T> father;

    private T info;
    private NodoPFFS<T> firstSon;
    private NodoPFFS<T> brother;


    //CONSTRUCTOR
    public NodoPFFS(T info) {
        this.info = info;
    }

    public NodoPFFS(NodoPFFS<T> father, T info, NodoPFFS<T> firstSon, NodoPFFS<T> brother) {
        this.father = father;
        this.info = info;
        this.firstSon = firstSon;
        this.brother = brother;
    }

    public NodoPFFS(NodoPFFS<T> father, T info) {
        this.father = father;
        this.info = info;
    }


    //GETTERS and SETTERS
    public NodoPFFS<T> getFirstSon() {
        return firstSon;
    }
    public NodoPFFS<T> getBrother() {
        return brother;
    }
    public T getInfo() {
        return info;
    }
    public void setInfo(T info) {
        this.info = info;
    }
    public void setFirstSon(NodoPFFS<T> son) {
        this.firstSon = son;
    }
    public void setBrother(NodoPFFS<T> firstBrother) {
        this.brother = firstBrother;
    }
    public NodoPFFS<T> getFather() {
        return father;
    }
    public void setFather(NodoPFFS<T> father) {
        this.father = father;
    }

    @Override
    public String toString() {
        return info.toString();
    }
}

package com.example.alcancia12.model;

public class Meta {

    String proposito,costo;

    public Meta(){

    }

    public Meta(String proposito, String costo) {
        this.proposito = proposito;
        this.costo = costo;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }
}

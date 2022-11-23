package com.example.alcancia12.model;

public class Meta {

    String proposito,costo,fecharegistro;
    long timestamp;

    public Meta(){

    }

    public Meta(String proposito, String costo, String fecharegistro, String timestamp) {
        this.proposito = proposito;
        this.costo = costo;
        this.fecharegistro = fecharegistro;
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

    // get y set de el tiempo


    public String getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(String fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

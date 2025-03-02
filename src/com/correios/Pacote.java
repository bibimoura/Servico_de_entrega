package com.correios;

//SuperClass - Pacote
public abstract class Pacote {
    // Atributos
    private final int id;
    private String name;
    private final boolean enviado;

    // Constructor
    public Pacote(int id, String name) {
        this.id = id;
        this.name = name;
        this.enviado = false;
    }

    // ID
    public int getId() {
        return id;
    }

    // Name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Enviado
    public boolean enviado() {
        return enviado;
    }

    // Information
    public String informacoes() {
        return "ID: " + id + ", Nome: " + name + ", Enviado: " + (enviado ? "Sim" : "Não");
    }

    // Obj -> String
    @Override
    public String toString() {
        return getId() + "," + getName();
    }

    // String -> Obj
    public static Pacote fromString(String linha) {
        String[] partes = linha.split(",");
        String tipo = partes[2];

        return switch (tipo) {
            case "Nacional" -> new PacoteNacional(Integer.parseInt(partes[0]), partes[1], partes[3]);
            case "Internacional" -> new PacoteInternacional(Integer.parseInt(partes[0]), partes[1], partes[3]);
            default -> null;
        };
    }

    // Abstract Method
    public abstract void alterarDestino(String novoDestino);
}

//SubClass - PacoteNacional
class PacoteNacional extends Pacote{
    private String estado;

    //Constructor
    public PacoteNacional(int id, String name, String estado) {
        super(id, name);
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String informacoes() {
        return super.informacoes() + ", " + "Estado: " + estado;
    }

    @Override
    public void alterarDestino(String novoDestino) {
        this.estado = novoDestino;
    }

    @Override
    public String toString() {
        return super.toString() + ",Nacional," + getEstado();
    }
}

//SubClass - PacoteInternacional
class PacoteInternacional extends Pacote{
    private String pais;

    //Constructor
    public PacoteInternacional(int id, String name, String pais) {
        super(id, name);
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }

    @Override
    public String informacoes() {
        return super.informacoes() + ", " + "País: " + pais;
    }

    @Override
    public void alterarDestino(String novoDestino) {
        this.pais = novoDestino;
    }

    @Override
    public String toString() {
        return super.toString() + ",Internacional," + getPais();
    }

}
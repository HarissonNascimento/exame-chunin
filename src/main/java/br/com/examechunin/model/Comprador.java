package br.com.examechunin.model;

import java.util.Objects;

public class Comprador {
    private String nome;
    private String telefone;
    private Integer id;
    private Veiculo veiculo;
    private boolean contatado;

    public Comprador() {
    }

    public Comprador(String nome, String telefone, Integer id, boolean contatado, Veiculo veiculo) {
        this.nome = nome;
        this.telefone = telefone;
        this.id = id;
        this.veiculo = veiculo;
        this.contatado = contatado;
    }

    public Comprador(String nome, String telefone, Integer id, Veiculo veiculo) {
        this.nome = nome;
        this.telefone = telefone;
        this.id = id;
        this.veiculo = veiculo;
    }

    @Override
    public String toString() {
        return "Comprador{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comprador comprador = (Comprador) o;
        return Objects.equals(id, comprador.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isContatado() {
        return contatado;
    }

    public void setContatado(boolean contatado) {
        this.contatado = contatado;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

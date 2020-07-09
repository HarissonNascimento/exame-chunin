package br.com.examechunin.model;

import br.com.examechunin.model.enums.Combustivel;
import br.com.examechunin.model.enums.TipoVeiculo;
import br.com.examechunin.model.enums.Transmissao;
import br.com.examechunin.model.interfaces.CombustivelFactory;
import br.com.examechunin.model.interfaces.TipoVeiculoFactory;
import br.com.examechunin.model.interfaces.TransmissaoFactory;

import javax.faces.context.FacesContext;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Arrays.asList;

public class Veiculo implements Serializable {
    private Integer id;
    private Integer ano;
    private Double preco;
    private Comprador comprador;
    private TipoVeiculo tipoVeiculoEnum;
    private Combustivel combustivelEnum;
    private Transmissao transmissaoEnum;
    private String tipoVeiculo;
    private String combustivel;
    private String transmissao;
    //    private String imagem;
    private String marca;
    private String modelo;
    private String pastaImagens;
    private String descricao;
    private boolean statusVendido;
    private transient boolean editing;

    public Veiculo() {
    }

    public Veiculo(Integer id, String tipoVeiculo, String combustivel, String transmissao, String marca, String modelo, Integer ano, String descricao, Double preco, String pastaImagens, boolean statusVendido) {
        this.id = id;
        this.tipoVeiculo = tipoVeiculo;
        this.combustivel = combustivel;
        this.transmissao = transmissao;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.descricao = descricao;
        this.preco = preco;
        this.pastaImagens = pastaImagens;
        this.statusVendido = statusVendido;

    }

    public Veiculo(Integer id, String tipoVeiculo, String combustivel, String transmissao, String marca, String modelo, Integer ano, String descricao, Double preco, String pastaImagens, boolean statusVendido, Comprador comprador) {
        this.id = id;
        this.tipoVeiculo = tipoVeiculo;
        this.combustivel = combustivel;
        this.transmissao = transmissao;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.descricao = descricao;
        this.preco = preco;
        this.pastaImagens = pastaImagens;
        this.statusVendido = statusVendido;
        this.comprador = comprador;
    }

    public String thumbnail() {
        List<String> list = listarNomesImagens();
        return list.get(0);
    }

    public List<String> listarNomesImagens() {
        File file = new File(recuperarDiretorio());
        boolean dir = file.mkdir();
        if (!dir) {
            List<String> fileNameList = new ArrayList<>();
            List<File> fileList = asList(file.listFiles());
            for (File f : fileList) {
                String nameFile = f.getName();
                fileNameList.add(nameFile);
            }
            return fileNameList;
        } else {
            return new ArrayList<>();
        }
    }

    public void setarAtributos() {
        setarStringTipoVeiculo();
        setarStringCombustivel();
        setarStringCambio();
    }

    public void gerarPastaImagens() {
        this.pastaImagens = UUID.randomUUID().toString();
    }

    private void setarStringTipoVeiculo() {
        if (tipoVeiculoEnum != null) {
            this.tipoVeiculo = TipoVeiculoFactory.criarTipoVeiculo(tipoVeiculoEnum).getTipoVeiculo();
        }
    }

    private void setarStringCombustivel() {
        if (combustivelEnum != null) {
            this.combustivel = CombustivelFactory.criarCombustivel(combustivelEnum).getTipoCombustivel();
        }
    }

    private void setarStringCambio() {
        if (transmissaoEnum != null) {
            this.transmissao = TransmissaoFactory.criarCambio(transmissaoEnum).getTipoCambio();
        }
    }

    public String recuperarDiretorio() {
        if (!pastaImagens.isEmpty() || !pastaImagens.equals("")) {
            return FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources" + File.separator + "default" + File.separator + "images" + File.separator + this.pastaImagens);
        }
        throw new RuntimeException("Pasta não encontrada");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(id, veiculo.id);
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "tipoVeiculo='" + tipoVeiculo + '\'' +
                ", combustivel='" + combustivel + '\'' +
                ", cambio='" + transmissao + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public String getPastaImagens() {
        return pastaImagens;
    }

    public void setPastaImagens(String pastaImagens) {
        this.pastaImagens = pastaImagens;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public TipoVeiculo getTipoVeiculoEnum() {
        return tipoVeiculoEnum;
    }

    public void setTipoVeiculoEnum(TipoVeiculo tipoVeiculoEnum) {
        this.tipoVeiculoEnum = tipoVeiculoEnum;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public Integer getId() {
        return id;
    }

    public boolean isStatusVendido() {
        return statusVendido;
    }

    public void setStatusVendido(boolean statusVendido) {
        this.statusVendido = statusVendido;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public Combustivel getCombustivelEnum() {
        return combustivelEnum;
    }

    public void setCombustivelEnum(Combustivel combustivelEnum) {
        this.combustivelEnum = combustivelEnum;
    }

    public String getTransmissao() {
        return transmissao;
    }

    public void setTransmissao(String transmissao) {
        this.transmissao = transmissao;
    }

    public Transmissao getTransmissaoEnum() {
        return transmissaoEnum;
    }

    public void setTransmissaoEnum(Transmissao transmissaoEnum) {
        this.transmissaoEnum = transmissaoEnum;
    }

//    public String getImagem() {
//        return imagem;
//    }
//
//    public void setImagem(String imagem) {
//        this.imagem = imagem;
//    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}

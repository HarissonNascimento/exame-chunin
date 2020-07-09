package br.com.examechunin.bean;

import br.com.examechunin.model.Comprador;
import br.com.examechunin.model.Veiculo;
import br.com.examechunin.model.bd.db.CompradorDAO;
import br.com.examechunin.model.bd.db.VeiculoDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class HomeADMBean implements Serializable {
    private List<Veiculo> veiculos;
    private List<Comprador> compradores;
    private Veiculo veiculoSelecionado;
    private Comprador compradorSelecionado;

    @PostConstruct
    public void init() {
        veiculos = VeiculoDAO.selectAll();
        compradores = CompradorDAO.selectAll();
    }

    public List<Veiculo> veiculosVendidos() {
        return veiculos
                .stream()
                .filter(Veiculo::isStatusVendido)
                .sorted(Comparator
                        .comparing(Veiculo::getId)
                        .reversed())
                .collect(Collectors.toList());
    }

    public List<Veiculo> veiculosEstoque() {
        return veiculos
                .stream()
                .filter(veiculo -> !veiculo.isStatusVendido())
                .sorted(Comparator
                        .comparing(Veiculo::getId)
                        .reversed())
                .collect(Collectors.toList());
    }

    public List<Comprador> compradoresContatar() {
        return compradores
                .stream()
                .filter(comprador -> !comprador.isContatado())
                .sorted(Comparator
                        .comparing(Comprador::getId))
                .collect(Collectors.toList());
    }

    public List<Comprador> compradoresContatados() {
        return compradores
                .stream()
                .filter(Comprador::isContatado)
                .sorted(Comparator.comparing(Comprador::getId)
                        .reversed())
                .collect(Collectors.toList());
    }

    public String deletar() {
        VeiculoDAO.delete(veiculoSelecionado);
        return "homeadm?faces-redirect=true";
    }

    public String detalhes() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("veiculo", veiculoSelecionado);
        return "/veiculo.xhtml?faces-redirect=true";
    }

    public String compradorContatado() {
        if (compradorSelecionado == null) {
            return "";
        } else {
            compradorSelecionado.setContatado(true);
            CompradorDAO.update(compradorSelecionado);
            return "homeadm?faces-redirect=true";
        }
    }

    public Veiculo getVeiculoSelecionado() {
        return veiculoSelecionado;
    }

    public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
        this.veiculoSelecionado = veiculoSelecionado;
    }

    public Comprador getCompradorSelecionado() {
        return compradorSelecionado;
    }

    public void setCompradorSelecionado(Comprador compradorSelecionado) {
        this.compradorSelecionado = compradorSelecionado;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public List<Comprador> getCompradores() {
        return compradores;
    }

    public void setCompradores(List<Comprador> compradores) {
        this.compradores = compradores;
    }

}

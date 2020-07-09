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
import java.util.List;

@Named
@ViewScoped
public class VeiculoBean implements Serializable {
    private Veiculo veiculo;
    private List<String> listDiretoriosImages;
    private Comprador comprador;
    private List<Comprador> compradorList;

    @PostConstruct
    public void init() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        veiculo = (Veiculo) flash.get("veiculo");
        listDiretoriosImages = veiculo.listarNomesImagens();
        comprador = new Comprador();
        compradorList = CompradorDAO.selectAll();
    }

    public void edit() {
        compradorList.removeIf(c -> c.getVeiculo().getId() != veiculo.getId());
        veiculo.setEditing(true);
    }


    public void salvar() {
        veiculo.setarAtributos();
        if (comprador.getId() != null) {
            comprador = CompradorDAO.searchById(comprador.getId());
            veiculo.setComprador(comprador);
        }
        VeiculoDAO.update(veiculo);
        veiculo.setEditing(false);
    }

    public String comprar() {
        comprador = new Comprador();
        comprador.setVeiculo(veiculo);
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("comprador", comprador);
        return "registration?faces-redirect=true";
    }

    public List<Comprador> getCompradorList() {
        return compradorList;
    }

    public void setCompradorList(List<Comprador> compradorList) {
        this.compradorList = compradorList;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public List<String> getListDiretoriosImages() {
        return listDiretoriosImages;
    }

    public void setListDiretoriosImages(List<String> listDiretoriosImages) {
        this.listDiretoriosImages = listDiretoriosImages;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}

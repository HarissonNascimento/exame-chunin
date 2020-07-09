package br.com.examechunin.bean;

import br.com.examechunin.model.Veiculo;
import br.com.examechunin.model.bd.db.VeiculoDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ResultBean implements Serializable {
    private List<Veiculo> listVeiculos;
    private Veiculo veiculoSelecionado;
    private String busca;
    private boolean isSearched;

    @PostConstruct
    public void init() {
        Map<String, String> paramsMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        busca = paramsMap.get("busca");
        if (busca == null || busca.equals("")) {
            listVeiculos = VeiculoDAO.selectAll().stream().filter(veiculo -> !veiculo.isStatusVendido()).sorted(Comparator.comparing(Veiculo::getId).reversed()).collect(Collectors.toList());
        } else {
            listVeiculos = VeiculoDAO.searchByModel(busca).stream().filter(veiculo -> !veiculo.isStatusVendido()).sorted(Comparator.comparing(Veiculo::getId).reversed()).collect(Collectors.toList());
            if (listVeiculos.isEmpty()) {
                isSearched = true;
                listVeiculos = VeiculoDAO.selectAll().stream().filter(veiculo -> !veiculo.isStatusVendido()).sorted(Comparator.comparing(Veiculo::getId).reversed()).collect(Collectors.toList());
            }
        }
    }

    public String deletar() {
        VeiculoDAO.delete(veiculoSelecionado);
        return "resultbusca?faces-redirect=true";
    }

    public String detalhes() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("veiculo", veiculoSelecionado);
        return "veiculo?faces-redirect=true";
    }


    public boolean isSearched() {
        return isSearched;
    }

    public void setSearched(boolean searched) {
        isSearched = searched;
    }

    public List<Veiculo> getListVeiculos() {
        return listVeiculos;
    }

    public void setListVeiculos(List<Veiculo> listVeiculos) {
        this.listVeiculos = listVeiculos;
    }

    public Veiculo getVeiculoSelecionado() {
        return veiculoSelecionado;
    }

    public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
        this.veiculoSelecionado = veiculoSelecionado;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }
}

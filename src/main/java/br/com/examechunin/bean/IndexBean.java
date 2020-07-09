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
import java.util.stream.Collectors;

@Named
@ViewScoped
public class IndexBean implements Serializable {
    private List<Veiculo> veiculoList;
    private Veiculo veiculoSelecionado;

    @PostConstruct
    public void init(){
        veiculoList = VeiculoDAO.selectAll().stream().filter(veiculo -> !veiculo.isStatusVendido()).sorted(Comparator.comparing(Veiculo::getId).reversed()).collect(Collectors.toList());
    }

    public String detalhes() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("veiculo", veiculoSelecionado);
        return "/veiculo.xhtml?faces-redirect=true";
    }
    public List<Veiculo> carrosUltilitarios(){
        return veiculoList.stream().filter(v -> v.getTipoVeiculo().equals("Carros e Ultilitários")).collect(Collectors.toList());
    }

    public List<Veiculo> motosQuadriciclos(){
        return veiculoList.stream().filter(v -> v.getTipoVeiculo().equals("Motos e Quadriciclos")).collect(Collectors.toList());
    }
    public List<Veiculo> caminhoes(){
        return veiculoList.stream().filter(v -> v.getTipoVeiculo().equals("Caminhões")).collect(Collectors.toList());
    }
    public List<Veiculo> carretasCarrocerias(){
        return veiculoList.stream().filter(v -> v.getTipoVeiculo().equals("Carretas e Carrocerias")).collect(Collectors.toList());
    }
    public List<Veiculo> onibusVans(){
        return veiculoList.stream().filter(v -> v.getTipoVeiculo().equals("Ônibus e Vans")).collect(Collectors.toList());
    }
    public List<Veiculo> nauticos(){
        return veiculoList.stream().filter(v -> v.getTipoVeiculo().equals("Náuticos")).collect(Collectors.toList());
    }

    public List<Veiculo> outros(){
        return veiculoList.stream().filter(v -> v.getTipoVeiculo().equals("Outros")).collect(Collectors.toList());
    }


    public List<Veiculo> veiculosAnuncio(){
        return veiculoList.stream().limit(9).collect(Collectors.toList());
    }

    public Veiculo getVeiculoSelecionado() {
        return veiculoSelecionado;
    }

    public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
        this.veiculoSelecionado = veiculoSelecionado;
    }

    public List<Veiculo> getVeiculoList() {
        return veiculoList;
    }

    public void setVeiculoList(List<Veiculo> veiculoList) {
        this.veiculoList = veiculoList;
    }
}

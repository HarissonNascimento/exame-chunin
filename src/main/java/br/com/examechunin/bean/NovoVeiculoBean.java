package br.com.examechunin.bean;

import br.com.examechunin.model.Veiculo;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class NovoVeiculoBean implements Serializable {

    private Veiculo veiculo;

    public void init() {
        veiculo = new Veiculo();
    }

    public String next() {
        veiculo.setarAtributos();
        veiculo.gerarPastaImagens();
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("veiculo", veiculo);
        return "uploadimagens?faces-redirect=true";
    }


    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}

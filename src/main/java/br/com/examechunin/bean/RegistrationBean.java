package br.com.examechunin.bean;

import br.com.examechunin.model.Comprador;
import br.com.examechunin.model.bd.db.CompradorDAO;
import org.primefaces.event.FlowEvent;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Named
@ViewScoped
public class RegistrationBean implements Serializable {
    private Comprador comprador;

    @PostConstruct
    public void init() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        comprador = (Comprador) flash.get("comprador");
    }

    public String save() {
        CompradorDAO.save(comprador);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "index?faces-redirect=true";
    }

    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
}

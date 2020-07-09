package br.com.examechunin.bean;

import br.com.examechunin.model.Veiculo;
import br.com.examechunin.model.bd.db.VeiculoDAO;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class UploadImagensBean implements Serializable {
    private Veiculo veiculo;
    private UploadedFiles files;
    private List<String> filesDiretorio;

    public void init() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        veiculo = (Veiculo) flash.get("veiculo");
        filesDiretorio = new ArrayList<>();
    }

    public String salvar() {
        VeiculoDAO.save(veiculo);
        return "/resultbusca?faces-redirect=true";
    }

    public void upload() {
        if (files != null) {
            String diretorio = FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources" + File.separator + "default" + File.separator + "images" + File.separator + veiculo.getPastaImagens());
            File file = new File(diretorio);
            boolean dirBoolean = file.mkdirs();
            for (UploadedFile uf : files.getFiles()) {
                if (!filesDiretorio.contains(uf.getFileName())) {
                    try (InputStream is = uf.getInputStream()) {
                        Files.copy(is,
                                new File(file.getAbsolutePath(),
                                        uf.getFileName()).toPath());
                        filesDiretorio = veiculo.listarNomesImagens();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Já existe um arquivo com esse nome.", "");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
            }
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Arquivo enviado com sucesso!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Você deve selecionar de 1 a 5 imagens", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public boolean disableButton() {
        return filesDiretorio.size() < 1;
    }

    public List<String> getFilesDiretorio() {
        return filesDiretorio;
    }

    public void setFilesDiretorio(List<String> filesDiretorio) {
        this.filesDiretorio = filesDiretorio;
    }

    public UploadedFiles getFiles() {
        return files;
    }

    public void setFiles(UploadedFiles files) {
        this.files = files;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}

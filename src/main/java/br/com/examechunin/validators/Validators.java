package br.com.examechunin.validators;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class Validators implements Serializable {
    public void validateFile(FacesContext facesContext, UIComponent uiComponent, Object o)
            throws ValidatorException {
        UploadedFiles file = (UploadedFiles) o;
        try {
            validateContentType(file);
            validateFileSize(file);
        } catch (RuntimeException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(),
                    "");
            throw new ValidatorException(message);
        }
    }

    private void validateContentType(UploadedFiles file) {
        List<UploadedFile> files = file.getFiles();
        for (UploadedFile uploadedFile : files) {
            String contentType = uploadedFile.getContentType();
            if (!contentType.equals("image/png") && !contentType.equals("image/jpeg") && !contentType.equals("image/jpg")) {
                throw new RuntimeException("Apenas imagens nos formatos: jpeg, jpg e png são permitidas.");
            }
        }
    }

    private void validateFileSize(UploadedFiles file) {
        List<UploadedFile> files = file.getFiles();
        for (UploadedFile uploadedFile : files) {
            if (uploadedFile.getSize() > 1048576) {
                throw new RuntimeException("O tamanho do arquivo '" + uploadedFile.getFileName() + "' não pode exceder 2 MB");
            }
        }
    }
}

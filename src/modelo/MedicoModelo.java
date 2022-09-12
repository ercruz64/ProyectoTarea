package modelo;

public class MedicoModelo {
    private int idMedico;
    private String nombreMedico;
    private String apellidoMedico;
    private int documentoMedico;
    private String especialidadMedico;

    public MedicoModelo() {
    }

    public MedicoModelo(int idMedico) {
        this.idMedico = idMedico;
    }

    public MedicoModelo(int idMedico, String nombreMedico, String apellidoMedico, int documentoMedico) {
        this.idMedico = idMedico;
        this.nombreMedico = nombreMedico;
        this.apellidoMedico = apellidoMedico;
        this.documentoMedico = documentoMedico;
    }

    public MedicoModelo(String nombreMedico, String apellidoMedico, int documentoMedico, String especialidadMedico) {
        this.nombreMedico = nombreMedico;
        this.apellidoMedico = apellidoMedico;
        this.documentoMedico = documentoMedico;
        this.especialidadMedico = especialidadMedico;
    }

    public MedicoModelo(int idMedico, String nombreMedico, String apellidoMedico, int documentoMedico, String especialidadMedico) {
        this.idMedico = idMedico;
        this.nombreMedico = nombreMedico;
        this.apellidoMedico = apellidoMedico;
        this.documentoMedico = documentoMedico;
        this.especialidadMedico = especialidadMedico;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getApellidoMedico() {
        return apellidoMedico;
    }

    public void setApellidoMedico(String apellidoMedico) {
        this.apellidoMedico = apellidoMedico;
    }

    public int getDocumentoMedico() {
        return documentoMedico;
    }

    public void setDocumentoMedico(int documentoMedico) {
        this.documentoMedico = documentoMedico;
    }

    public String getEspecialidadMedico() {
        return especialidadMedico;
    }

    public void setEspecialidadMedico(String especialidadMedico) {
        this.especialidadMedico = especialidadMedico;
    }

    @Override
    public String toString() {
        return apellidoMedico + " " +  nombreMedico + " - " + documentoMedico;
    }
    
    
    
}

package modelo;

public class PacienteModelo {
    private int idPaciente;
    private String nombresPaciente;
    private String apellidosPaciente;
    private int documentoPaciente;
    private String fechaNacimientoPaciente;
    private String generoPaciente;

    public PacienteModelo() {
    }

    public PacienteModelo(String nombresPaciente, String apellidosPaciente, int documentoPaciente, String fechaNacimientoPaciente, String generoPaciente) {
        this.nombresPaciente = nombresPaciente;
        this.apellidosPaciente = apellidosPaciente;
        this.documentoPaciente = documentoPaciente;
        this.fechaNacimientoPaciente = fechaNacimientoPaciente;
        this.generoPaciente = generoPaciente;
    }

    public PacienteModelo(int idPaciente, String nombresPaciente, String apellidosPaciente, int documentoPaciente, String fechaNacimientoPaciente, String generoPaciente) {
        this.idPaciente = idPaciente;
        this.nombresPaciente = nombresPaciente;
        this.apellidosPaciente = apellidosPaciente;
        this.documentoPaciente = documentoPaciente;
        this.fechaNacimientoPaciente = fechaNacimientoPaciente;
        this.generoPaciente = generoPaciente;
    }

    public PacienteModelo(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    
    

    /**
     * @return the idPaciente
     */
    public int getIdPaciente() {
        return idPaciente;
    }

    /**
     * @param idPaciente the idPaciente to set
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * @return the nombresPaciente
     */
    public String getNombresPaciente() {
        return nombresPaciente;
    }

    /**
     * @param nombresPaciente the nombresPaciente to set
     */
    public void setNombresPaciente(String nombresPaciente) {
        this.nombresPaciente = nombresPaciente;
    }

    /**
     * @return the apellidosPaciente
     */
    public String getApellidosPaciente() {
        return apellidosPaciente;
    }

    /**
     * @param apellidosPaciente the apellidosPaciente to set
     */
    public void setApellidosPaciente(String apellidosPaciente) {
        this.apellidosPaciente = apellidosPaciente;
    }

    /**
     * @return the documentoPaciente
     */
    public int getDocumentoPaciente() {
        return documentoPaciente;
    }

    /**
     * @param documentoPaciente the documentoPaciente to set
     */
    public void setDocumentoPaciente(int documentoPaciente) {
        this.documentoPaciente = documentoPaciente;
    }

    /**
     * @return the fechaNacimientoPaciente
     */
    public String getFechaNacimientoPaciente() {
        return fechaNacimientoPaciente;
    }

    /**
     * @param fechaNacimientoPaciente the fechaNacimientoPaciente to set
     */
    public void setFechaNacimientoPaciente(String fechaNacimientoPaciente) {
        this.fechaNacimientoPaciente = fechaNacimientoPaciente;
    }

    /**
     * @return the generoPaciente
     */
    public String getGeneroPaciente() {
        return generoPaciente;
    }

    /**
     * @param generoPaciente the generoPaciente to set
     */
    public void setGeneroPaciente(String generoPaciente) {
        this.generoPaciente = generoPaciente;
    }

    @Override
    public String toString() {
        return apellidosPaciente + " " + nombresPaciente + " * " + documentoPaciente;
    }
   
}

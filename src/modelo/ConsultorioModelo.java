package modelo;

public class ConsultorioModelo {
    private int idConsultorio;
    private String nombreConsultorio;
    private String estadoConsultorio;

    public ConsultorioModelo() {
    }

    public ConsultorioModelo(int idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public ConsultorioModelo(String nombreConsultorio, String estadoConsultorio) {
        this.nombreConsultorio = nombreConsultorio;
        this.estadoConsultorio = estadoConsultorio;
    }

    public ConsultorioModelo(int idConsultorio, String nombreConsultorio, String estadoConsultorio) {
        this.idConsultorio = idConsultorio;
        this.nombreConsultorio = nombreConsultorio;
        this.estadoConsultorio = estadoConsultorio;
    }

    /**
     * @return the idConsultorio
     */
    public int getIdConsultorio() {
        return idConsultorio;
    }

    /**
     * @param idConsultorio the idConsultorio to set
     */
    public void setIdConsultorio(int idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    /**
     * @return the nombreConsultorio
     */
    public String getNombreConsultorio() {
        return nombreConsultorio;
    }

    /**
     * @param nombreConsultorio the nombreConsultorio to set
     */
    public void setNombreConsultorio(String nombreConsultorio) {
        this.nombreConsultorio = nombreConsultorio;
    }

    /**
     * @return the estadoConsultorio
     */
    public String getEstadoConsultorio() {
        return estadoConsultorio;
    }

    /**
     * @param estadoConsultorio the estadoConsultorio to set
     */
    public void setEstadoConsultorio(String estadoConsultorio) {
        this.estadoConsultorio = estadoConsultorio;
    }

    @Override
    public String toString() {
        return nombreConsultorio + " - " + estadoConsultorio;
    }
    
    
    
}

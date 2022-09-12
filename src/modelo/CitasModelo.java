package modelo;

public class CitasModelo {
    private int citId;
    private String citFecha;
    private String citHora;
    private int citPaciente;
    private int citMedico;
    private int citConsultorio;
    private String citEstado;
    private String citObservaciones;

    public CitasModelo() {
    }

    public CitasModelo(int citId) {
        this.citId = citId;
    }
    
    

    public CitasModelo(String citFecha, String citHora, int citPaciente, int citMedico, int citConsultorio, String citEstado, String citObservaciones) {
        this.citFecha = citFecha;
        this.citHora = citHora;
        this.citPaciente = citPaciente;
        this.citMedico = citMedico;
        this.citConsultorio = citConsultorio;
        this.citEstado = citEstado;
        this.citObservaciones = citObservaciones;
    }

    public CitasModelo(int citId, String citFecha, String citHora, int citPaciente, int citMedico, int citConsultorio, String citEstado, String citObservaciones) {
        this.citId = citId;
        this.citFecha = citFecha;
        this.citHora = citHora;
        this.citPaciente = citPaciente;
        this.citMedico = citMedico;
        this.citConsultorio = citConsultorio;
        this.citEstado = citEstado;
        this.citObservaciones = citObservaciones;
    }

    public int getCitId() {
        return citId;
    }

    public void setCitId(int citId) {
        this.citId = citId;
    }

    public String getCitFecha() {
        return citFecha;
    }

    public void setCitFecha(String citFecha) {
        this.citFecha = citFecha;
    }

    public String getCitHora() {
        return citHora;
    }

    public void setCitHora(String citHora) {
        this.citHora = citHora;
    }

    public int getCitPaciente() {
        return citPaciente;
    }

    public void setCitPaciente(int citPaciente) {
        this.citPaciente = citPaciente;
    }

    public int getCitMedico() {
        return citMedico;
    }

    public void setCitMedico(int citMedico) {
        this.citMedico = citMedico;
    }

    public int getCitConsultorio() {
        return citConsultorio;
    }

    public void setCitConsultorio(int citConsultorio) {
        this.citConsultorio = citConsultorio;
    }

    public String getCitEstado() {
        return citEstado;
    }

    public void setCitEstado(String citEstado) {
        this.citEstado = citEstado;
    }

    public String getCitObservaciones() {
        return citObservaciones;
    }

    public void setCitObservaciones(String citObservaciones) {
        this.citObservaciones = citObservaciones;
    }
    
    
}

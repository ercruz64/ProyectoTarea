package modelo;

/**
 *
 * @author Administrador
 */
public class CitasVistaModelo {
    private int citid;
    private String citfecha;
    private String cithora;
    private int citpaciente, citmedico, citconsultorio;
    private String citestado, citobservaciones, nombrespaciente, apellidospaciente;
    private int documentopaciente;
    private String fechanacimientopaciente, nombremedico, apellidomedico;
    private int documentomedico;
    private String especialidadmedico, nombreconsultorio, estadoconsultorio;

    public CitasVistaModelo() {
    }

    public CitasVistaModelo(int citid, String citfecha, String cithora, int citpaciente, int citmedico, int citconsultorio, String citestado, String citobservaciones, String nombrespaciente, String apellidospaciente, int documentopaciente, String fechanacimientopaciente, String nombremedico, String apellidomedico, int documentomedico, String especialidadmedico, String nombreconsultorio, String estadoconsultorio) {
        this.citid = citid;
        this.citfecha = citfecha;
        this.cithora = cithora;
        this.citpaciente = citpaciente;
        this.citmedico = citmedico;
        this.citconsultorio = citconsultorio;
        this.citestado = citestado;
        this.citobservaciones = citobservaciones;
        this.nombrespaciente = nombrespaciente;
        this.apellidospaciente = apellidospaciente;
        this.documentopaciente = documentopaciente;
        this.fechanacimientopaciente = fechanacimientopaciente;
        this.nombremedico = nombremedico;
        this.apellidomedico = apellidomedico;
        this.documentomedico = documentomedico;
        this.especialidadmedico = especialidadmedico;
        this.nombreconsultorio = nombreconsultorio;
        this.estadoconsultorio = estadoconsultorio;
    }

    public int getCitid() {
        return citid;
    }

    public void setCitid(int citid) {
        this.citid = citid;
    }

    public String getCitfecha() {
        return citfecha;
    }

    public void setCitfecha(String citfecha) {
        this.citfecha = citfecha;
    }

    public String getCithora() {
        return cithora;
    }

    public void setCithora(String cithora) {
        this.cithora = cithora;
    }

    public int getCitpaciente() {
        return citpaciente;
    }

    public void setCitpaciente(int citpaciente) {
        this.citpaciente = citpaciente;
    }

    public int getCitmedico() {
        return citmedico;
    }

    public void setCitmedico(int citmedico) {
        this.citmedico = citmedico;
    }

    public int getCitconsultorio() {
        return citconsultorio;
    }

    public void setCitconsultorio(int citconsultorio) {
        this.citconsultorio = citconsultorio;
    }

    public String getCitestado() {
        return citestado;
    }

    public void setCitestado(String citestado) {
        this.citestado = citestado;
    }

    public String getCitobservaciones() {
        return citobservaciones;
    }

    public void setCitobservaciones(String citobservaciones) {
        this.citobservaciones = citobservaciones;
    }

    public String getNombrespaciente() {
        return nombrespaciente;
    }

    public void setNombrespaciente(String nombrespaciente) {
        this.nombrespaciente = nombrespaciente;
    }

    public String getApellidospaciente() {
        return apellidospaciente;
    }

    public void setApellidospaciente(String apellidospaciente) {
        this.apellidospaciente = apellidospaciente;
    }

    public int getDocumentopaciente() {
        return documentopaciente;
    }

    public void setDocumentopaciente(int documentopaciente) {
        this.documentopaciente = documentopaciente;
    }

    public String getFechanacimientopaciente() {
        return fechanacimientopaciente;
    }

    public void setFechanacimientopaciente(String fechanacimientopaciente) {
        this.fechanacimientopaciente = fechanacimientopaciente;
    }

    public String getNombremedico() {
        return nombremedico;
    }

    public void setNombremedico(String nombremedico) {
        this.nombremedico = nombremedico;
    }

    public String getApellidomedico() {
        return apellidomedico;
    }

    public void setApellidomedico(String apellidomedico) {
        this.apellidomedico = apellidomedico;
    }

    public int getDocumentomedico() {
        return documentomedico;
    }

    public void setDocumentomedico(int documentomedico) {
        this.documentomedico = documentomedico;
    }

    public String getEspecialidadmedico() {
        return especialidadmedico;
    }

    public void setEspecialidadmedico(String especialidadmedico) {
        this.especialidadmedico = especialidadmedico;
    }

    public String getNombreconsultorio() {
        return nombreconsultorio;
    }

    public void setNombreconsultorio(String nombreconsultorio) {
        this.nombreconsultorio = nombreconsultorio;
    }

    public String getEstadoconsultorio() {
        return estadoconsultorio;
    }

    public void setEstadoconsultorio(String estadoconsultorio) {
        this.estadoconsultorio = estadoconsultorio;
    }
    
    
}

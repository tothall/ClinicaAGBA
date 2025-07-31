/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author ezequiel
 */
public class Consulta {
    protected int id_consulta;
    protected String codigo_consulta;
    protected String data_consulta;
    protected String id_paciente;
    protected String id_medico;
    protected String consultorio;
    protected String hora_consulta;
    
    public Consulta(int id_consulta, String codigo_consulta, String data_consulta, String id_paciente, String id_medico, String consultorio, String hora_consulta) {
        this.id_consulta = id_consulta;
        this.codigo_consulta = codigo_consulta;
        this.data_consulta = data_consulta;
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
        this.consultorio = consultorio;
        this.hora_consulta = hora_consulta;
    }
    
    public Consulta(String codigo_consulta, String data_consulta, String id_paciente, String id_medico, String consultorio, String hora_consulta) {
        this.codigo_consulta = codigo_consulta;
        this.data_consulta = data_consulta;
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
        this.consultorio = consultorio;
        this.hora_consulta = hora_consulta;
    }
    
    @Override
    public String toString() {
        return  "\nID: " + this.getId_consulta() +
                "\nData: " + this.getData_consulta() +
                "\nHora: " + this.getHora_consulta() +
                "\nID_Paciente: " + this.getId_paciente() +
                "\nID_Médico(a): " + this.getId_medico() +
                "\nConsultório: " + this.getConsultorio();
    }
    
    public boolean verificarDuplicata(String data_consulta, String hora_consulta, String consultorio) {
        return false;
    }
    

    /**
     * @return the id_consulta
     */
    public int getId_consulta() {
        return id_consulta;
    }

    /**
     * @param id_consulta the id_consulta to set
     */
    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    /**
     * @return the data_consulta
     */
    public String getData_consulta() {
        return data_consulta;
    }

    /**
     * @param data_consulta the data_consulta to set
     */
    public void setData_consulta(String data_consulta) {
        this.data_consulta = data_consulta;
    }

    /**
     * @return the id_paciente
     */
    public String getId_paciente() {
        return id_paciente;
    }

    /**
     * @param id_paciente the id_paciente to set
     */
    public void setId_paciente(String id_paciente) {
        this.id_paciente = id_paciente;
    }

    /**
     * @return the id_medico
     */
    public String getId_medico() {
        return id_medico;
    }

    /**
     * @param id_medico the id_medico to set
     */
    public void setId_medico(String id_medico) {
        this.id_medico = id_medico;
    }

    /**
     * @return the consultorio
     */
    public String getConsultorio() {
        return consultorio;
    }

    /**
     * @param consultorio the consultorio to set
     */
    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    /**
     * @return the hora_consulta
     */
    public String getHora_consulta() {
        return hora_consulta;
    }

    /**
     * @param hora_consulta the hora_consulta to set
     */
    public void setHora_consulta(String hora_consulta) {
        this.hora_consulta = hora_consulta;
    }

    /**
     * @return the codigo_consulta
     */
    public String getCodigo_consulta() {
        return codigo_consulta;
    }

    /**
     * @param codigo_consulta the codigo_consulta to set
     */
    public void setCodigo_consulta(String codigo_consulta) {
        this.codigo_consulta = codigo_consulta;
    }
    
    
}

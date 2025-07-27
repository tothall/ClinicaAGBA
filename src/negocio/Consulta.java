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
    protected String id_consulta;
    protected LocalDate data_consulta;
    protected String id_paciente;
    protected String id_medico;
    protected int consultorio;
    protected LocalTime hora_consulta;
    
    public Consulta(String id_consulta, LocalDate data_consulta, String id_paciente, String id_medico, int consultorio, LocalTime hora_consulta) {
        this.id_consulta = id_consulta;
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
    
    public boolean verificarDuplicata(LocalDate data_consulta, LocalTime hora_consulta, int consultorio) {
        return LocalDate.now() == this.data_consulta && LocalTime.now() == hora_consulta && 1 == consultorio;
    }
    

    /**
     * @return the id_consulta
     */
    public String getId_consulta() {
        return id_consulta;
    }

    /**
     * @param id_consulta the id_consulta to set
     */
    public void setId_consulta(String id_consulta) {
        this.id_consulta = id_consulta;
    }

    /**
     * @return the data_consulta
     */
    public LocalDate getData_consulta() {
        return data_consulta;
    }

    /**
     * @param data_consulta the data_consulta to set
     */
    public void setData_consulta(LocalDate data_consulta) {
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
    public int getConsultorio() {
        return consultorio;
    }

    /**
     * @param consultorio the consultorio to set
     */
    public void setConsultorio(int consultorio) {
        this.consultorio = consultorio;
    }

    /**
     * @return the hora_consulta
     */
    public LocalTime getHora_consulta() {
        return hora_consulta;
    }

    /**
     * @param hora_consulta the hora_consulta to set
     */
    public void setHora_consulta(LocalTime hora_consulta) {
        this.hora_consulta = hora_consulta;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.tsi.ipli.trabalhopratico.datamodel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Rodrigo
 */

@Entity
public class RelatorioCovid implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String regiao;
    private int codUf;
    private String data;
    private int semanaEpi;
    private int populacaoTCU;
    private int casosAcu;
    private Long casosNovos;

    public RelatorioCovid(){
    
    }
    
    public RelatorioCovid(int semanaEpi, Long casosNovos) {
        this.semanaEpi = semanaEpi;
        this.casosNovos = casosNovos;
    }

    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public int getCodUf() {
        return codUf;
    }

    public void setCodUf(int codUf) {
        this.codUf = codUf;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getSemanaEpi() {
        return semanaEpi;
    }

    public void setSemanaEpi(int semanaEpi) {
        this.semanaEpi = semanaEpi;
    }

    public int getPopulacaoTCU() {
        return populacaoTCU;
    }

    public void setPopulacaoTCU(int populacaoTCU) {
        this.populacaoTCU = populacaoTCU;
    }

    public int getCasosAcu() {
        return casosAcu;
    }

    public void setCasosAcu(int casosAcu) {
        this.casosAcu = casosAcu;
    }

    public Long getCasosNovos() {
        return casosNovos;
    }

    public void setCasosNovos(Long casosNovos) {
        this.casosNovos = casosNovos;
    }
      
}

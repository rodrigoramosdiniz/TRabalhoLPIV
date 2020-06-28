/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.tsi.ipli.trabalhopratico.datamodel;

/**
 *
 * @author Rodrigo
 */
public class SemanaCovid {
 
   private int semanaEpidemiologica;
   private Long casosNovos;

    public SemanaCovid(int semanaEpidemiologica, Long casosNovos) {
        this.semanaEpidemiologica = semanaEpidemiologica;
        this.casosNovos = casosNovos;
    }

   
   
    public int getSemanaEpidemiologica() {
        return semanaEpidemiologica;
    }

    public void setSemanaEpidemiologica(int semanaEpidemiologica) {
        this.semanaEpidemiologica = semanaEpidemiologica;
    }

    public Long getCasosNovos() {
        return casosNovos;
    }

    public void setCasosNovos(Long casosNovos) {
        this.casosNovos = casosNovos;
    }
    
}

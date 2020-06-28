/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifms.tsi.ipli.trabalhopratico.mb;

import br.ifms.tsi.ipli.trabalhopratico.datamodel.RelatorioCovid;
import br.ifms.tsi.ipli.trabalhopratico.datamodel.SemanaCovid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.annotation.PostConstruct;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;

import org.primefaces.model.chart.ChartSeries;

import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Rodrigo
 */

@ManagedBean
@RequestScoped
public class CovidMB {
    
    private BarChartModel model;
    private EntityManagerFactory emf;
    private EntityManager entityManager;
    private UploadedFile file;
    
    @PostConstruct
    public void inicializar(){
        
        inicializarChartModel();
        inicializarEntityManager();
        inicializarSeries();
        
    }
    
    public void inicializarEntityManager(){
       emf = Persistence.createEntityManagerFactory("Upload");
       entityManager = emf.createEntityManager();
    }
    
    public void inicializarChartModel(){
        model = new BarChartModel();
        model.setLegendPosition("ne");
        model.getAxis(AxisType.Y).setLabel(" Casos novos");
        model.getAxis(AxisType.X).setLabel("Semana Epidemiológica");
        model.setTitle("Casos novos de COVID-19 por Semana Epidemiológica de notificação");
        
    }
     
     public void salvarUpload(){
        try {
            InputStreamReader raeder = new InputStreamReader(file.getInputStream());
        BufferedReader br = new BufferedReader(raeder);
            String linha;
            br.readLine();
            String[] campos;
            RelatorioCovid covid;
            entityManager.getTransaction().begin();
            
            while(br.ready()){
                linha = br.readLine();
                System.out.println(linha);

                if(linha.length()<=1){
                    continue;
                }
                
                campos = linha.split(";");
                covid = new RelatorioCovid();
                
                covid.setRegiao(campos[0]);
                covid.setCodUf(Integer.parseInt(campos[3]));
                covid.setData(campos[7]);
                covid.setSemanaEpi(Integer.parseInt(campos[8]));
                covid.setPopulacaoTCU(Integer.parseInt(campos[9]));
                covid.setCasosAcu(Integer.parseInt(campos[10]));
                covid.setCasosNovos(new Long(campos[11]));
          
            entityManager.persist(covid);
            }
              
             entityManager.getTransaction().commit();
             
             inicializarChartModel();
             inicializarSeries();
        
            
        } catch (IOException ex) {
            Logger.getLogger(CovidMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    }
     
     public void inicializarSeries(){
        
         List<RelatorioCovid> relatorio = entityManager.createQuery("SELECT r FROM RelatorioCovid r").getResultList();
         HashMap<Integer, SemanaCovid> seriesMap = new HashMap();
         ChartSeries series;
         SemanaCovid semanaCovid;
         for (RelatorioCovid relatorioCovid : relatorio) {
             
             if(seriesMap.containsKey(relatorioCovid.getSemanaEpi())){
                 
                 semanaCovid = seriesMap.get(relatorioCovid.getSemanaEpi());
                 semanaCovid.setCasosNovos(semanaCovid.getCasosNovos()+ relatorioCovid.getCasosNovos());
                 
             }else{
                 semanaCovid = new SemanaCovid(relatorioCovid.getSemanaEpi(), relatorioCovid.getCasosNovos());
                 
                 
                 seriesMap.put(relatorioCovid.getSemanaEpi(), semanaCovid);
             
             }
             
         }
         
         series = new ChartSeries("Casos");
         
         for (SemanaCovid covid : seriesMap.values()){
             System.out.println(covid.getCasosNovos());
             series.set(covid.getSemanaEpidemiologica(), covid.getCasosNovos());   
         }
         
         System.out.println(series);
          
         model.addSeries(series);
     }
     
    public BarChartModel getModel() {
        return model;
    }

    public void setModel(BarChartModel model) {
        this.model = model;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}

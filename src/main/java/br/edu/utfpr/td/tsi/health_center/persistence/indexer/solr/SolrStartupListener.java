package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.model.dto.PatientDTO;

@Component
public class SolrStartupListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private SolrSchemaService solrSchemaService;
    
    @Autowired
    private SolrDataLoaderService solrDataLoaderService;
    
    private static final Logger logger = LoggerFactory.getLogger(SolrStartupListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	long startTime = System.currentTimeMillis();
    	
    	Set<String> fields = new HashSet<String>();
    	fields.add("type");
        Set<String> patientFields = Filter.getFieldsName(PatientDTO.class);
        fields.addAll(patientFields);
        try {
        	logger.info("Iniciando validação e criação dos campos Solr...");
        	solrSchemaService.validateAndCreateFields(fields, "text_general");
        	
        	logger.info("Iniciando o carregamento de dados para o Solr...");
        	solrDataLoaderService.loadDataToSolr();
        	
        	long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            logger.info("Processo concluído em {} ms.", duration);
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
}
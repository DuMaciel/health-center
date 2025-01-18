package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SolrStartupListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private SolrSchemaService solrSchemaService;
    
    @Autowired
    private SolrDataLoaderService solrDataLoaderService;
    
    private static final Logger logger = LoggerFactory.getLogger(SolrStartupListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
        	long startTime = System.currentTimeMillis();
        	logger.info("Starting to create missing fields in Solr...");
        	solrSchemaService.createMissingFields();
        	
        	logger.info("Starting to loading missing data into Solr...");
        	solrDataLoaderService.loadDataToSolr();
        	
        	long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            logger.info("Solr Initialization process completed in {} ms.", duration);
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
}
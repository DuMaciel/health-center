package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrClientConfig {

    @Value("${solr.host}")
    private String solrHost;

    @Value("${solr.core}")
    private String solrCore;

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(solrHost + "/" + solrCore).build();
    }
}
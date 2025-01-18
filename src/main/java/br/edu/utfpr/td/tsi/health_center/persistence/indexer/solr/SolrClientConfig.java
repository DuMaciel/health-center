package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("solr")
public class SolrClientConfig {

    @Value("${indexer.solr.host}")
    private String solrHost;

    @Value("${indexer.solr.core}")
    private String solrCore;

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(solrHost + "/" + solrCore).build();
    }
}
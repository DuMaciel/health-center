package br.edu.utfpr.td.tsi.health_center.persistence.indexer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;

@Component
public class SolrAdapter {
    private final SolrClient solrClient;

    public SolrAdapter(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    public void save(String type, String id, Map<String, Object> fields) {
        try {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", id);
            document.addField("type", type);
            fields.forEach(document::addField);
            solrClient.add(document);
            solrClient.commit();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar no Solr", e);
        }
    }

    public void delete(String id) {
        try {
            solrClient.deleteById(id);
            solrClient.commit();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar no Solr", e);
        }
    }

    public List<String> searchIdsByType(String type, String query) {
        try {
            SolrQuery solrQuery = new SolrQuery(query);
            solrQuery.addFilterQuery("type:" + type);
            solrQuery.setFields("id");

            QueryResponse response = solrClient.query(solrQuery);
            return response.getResults().stream()
                    .map(doc -> (String) doc.getFieldValue("id"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar no Solr", e);
        }
    }
}
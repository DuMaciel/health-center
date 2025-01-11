package br.edu.utfpr.td.tsi.health_center.persistence.indexer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;
import br.edu.utfpr.td.tsi.health_center.model.Patient;

@Component
public class PatientIndexer {
	
	private String urlString = "http://localhost:8983/sorl/health-center";
	private Http2SolrClient solr = new Http2SolrClient.Builder(urlString).build();
	
	public void add(Patient patient) {
		SolrInputDocument document = PatientMapper.toSolr(patient);
		
		try {
			solr.add(document);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			throw new RuntimeException("Erro ao tentar salvar paciente!");
		}
	}
	
	
	
	public List<String> search(String field, String term) {
		List<String> list = new ArrayList<String>();
		final SolrQuery query = new SolrQuery(String.format("%s:$s", field, term));
		query.addField("id");		
		
		QueryResponse response;
		try {
			response = solr.query(query);
			SolrDocumentList docList = response.getResults();
			
			for (SolrDocument doc : docList) {
				String patientId = doc.getFieldValue("id").toString();
				list.add(patientId);
			}
		} catch (SolrServerException | IOException e) {
			throw new RuntimeException("Erro ao tentar pesquisar os pacientes!");
		}
		return list;
	}
}

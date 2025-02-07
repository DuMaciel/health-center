package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.IndexerAdapter;

@Component
@Profile("solr")
public class SolrIndexerAdapter implements IndexerAdapter {
	static final private String idField = "id";
	
	static final private String typeField = "type";
    
    static final private String solrDefaultFieldType = "text_general";
    
    public static String getIdfield() {
		return idField;
	}

    
	public static String getTypefield() {
		return typeField;
	}

	public static String getSolrdefaultfieldtype() {
		return solrDefaultFieldType;
	}
	
    private final SolrClient solrClient;

    public SolrIndexerAdapter(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    public void save(String type, String id, Map<String, Object> fields) {
        try {
            SolrInputDocument document = new SolrInputDocument();
            document.addField(idField, makeSolrId(type, id));
            document.addField(typeField, type);
            for (Map.Entry<String, Object> field : fields.entrySet()) {
            	Object value = field.getValue();
            	document.addField(field.getKey(), value != null ? value.toString() : "");
			}
            solrClient.add(document);
            solrClient.commit();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar no Solr", e);
        }
    }
    
    public void save(String type, Map<String, Map<String, Object>> entities) {
    	try {
    		List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
        	for (Map.Entry<String, Map<String, Object>> entity : entities.entrySet()) {
        		String id = entity.getKey();
        		Map<String, Object> fields = entity.getValue();
        		SolrInputDocument document = new SolrInputDocument();
                document.addField(idField, makeSolrId(type, id));
                document.addField(typeField, type);
                for (Map.Entry<String, Object> field : fields.entrySet()) {
                	Object value = field.getValue();
                	document.addField(field.getKey(), value != null ? value.toString() : "");
    			}
                documents.add(document);
    		}
        	solrClient.add(documents);
            solrClient.commit();
    	} catch (Exception e) {
    		throw new RuntimeException("Erro ao salvar no Solr", e);
        }
    }

    public void delete(String type, String id) {
        try {
            solrClient.deleteById(makeSolrId(type, id));
            solrClient.commit();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar no Solr", e);
        }
    }
    
    public List<String> getIds(String type){
    	try {
            SolrQuery solrQuery = new SolrQuery("*:*");
            solrQuery.addFilterQuery(makeFilterByType(type));
            solrQuery.setFields(idField);
            
            QueryResponse response = solrClient.query(solrQuery);
            return response.getResults().stream()
                    .map(doc -> unMakeSolrId(type, String.valueOf(doc.getFieldValue(idField))))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar no Solr", e);
        }
    }

    public List<String> searchIds(String type, Filter filter) {
        try {
        	String field = filter.getField();
    		String term = filter.getTerm();
    		String query = String.format("%s:%s*", field, term);
            SolrQuery solrQuery = new SolrQuery(query);
            solrQuery.addFilterQuery(makeFilterByType(type));
            solrQuery.setFields(idField);

            QueryResponse response = solrClient.query(solrQuery);
            return response.getResults().stream()
                    .map(doc -> unMakeSolrId(type, String.valueOf(doc.getFieldValue(idField))))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar no Solr", e);
        }
    }
    
    @Override
    public List<String> searchIdsByFieldValues(String type, String field, List<String> values) {
        try {
            if (values == null || values.isEmpty()) {
                return new ArrayList<>();
            }

            String queryValues = values.stream()
                    .map(value -> String.format("%s", value))
                    .collect(Collectors.joining(" OR "));

            String query = String.format("%s:(%s)", field, queryValues);

            SolrQuery solrQuery = new SolrQuery(query);
            solrQuery.addFilterQuery(makeFilterByType(type));
            solrQuery.setFields(idField);

            QueryResponse response = solrClient.query(solrQuery);
            return response.getResults().stream()
                    .map(doc -> unMakeSolrId(type, String.valueOf(doc.getFieldValue(idField))))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar IDs no Solr por lista de valores", e);
        }
    }
    
    private String makeFilterByType(String type) {
    	return String.format("%s:%s", typeField, type);
    }
    
    private String makeSolrId(String type, String id) {
    	return String.format("%s_%s", type, id);
    }
    
    private String unMakeSolrId(String type, String solrId) {
    	return solrId.replaceFirst(String.format("^%s_", type), "");
    }
}
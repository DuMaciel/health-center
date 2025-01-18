package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;
import org.apache.solr.client.solrj.response.schema.SchemaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolrSchemaService {
	@Autowired
    private SolrClient solrClient;
	
    public void validateAndCreateFields(Set<String> fields, String fieldType) throws Exception {
    	Set<String> existingFields = getExistingFields();
    	
        for (String field : fields) {
            if (!existingFields.contains(field)) {
            	createField(field, fieldType);
            }
        }
    }
    
    private Set<String> getExistingFields() throws Exception {
        SchemaRequest.Fields fieldsRequest = new SchemaRequest.Fields();
        SchemaResponse.FieldsResponse fieldsResponse = fieldsRequest.process(this.solrClient);

        Set<String> fieldNames = new HashSet<>();
        for (Map<String, Object> field : fieldsResponse.getFields()) {
            fieldNames.add(String.valueOf(field.get("name")));
        }

        return fieldNames;
    }
    
    private void createField(String field, String fieldType) throws Exception {
    	Map<String, Object> fieldDefinition = Map.of(
                "name", field,
                "type", fieldType,
                "stored", true,
                "indexed", true
        );

        SchemaRequest.AddField addFieldsRequest = new SchemaRequest.AddField(fieldDefinition);
        SchemaResponse.UpdateResponse response = addFieldsRequest.process(solrClient);

        if (response.getStatus() != 0) {
            throw new RuntimeException("Erro ao criar campo no Solr");
        }
    }
}

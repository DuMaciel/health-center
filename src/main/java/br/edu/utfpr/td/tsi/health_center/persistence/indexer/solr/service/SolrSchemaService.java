package br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;
import org.apache.solr.client.solrj.response.schema.SchemaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.health_center.model.dto.ConsultationDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.DistrictDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.DoctorDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.model.dto.PatientDTO;
import br.edu.utfpr.td.tsi.health_center.persistence.indexer.solr.SolrIndexerAdapter;

@Service
@Profile("solr")
public class SolrSchemaService {
	@Autowired
    private SolrClient solrClient;
	
    public void createMissingFields() throws Exception {
    	Set<String> fields = new HashSet<String>();
    	fields.add(SolrIndexerAdapter.getTypefield());
    	
        Set<String> patientFields = Filter.getFieldsName(PatientDTO.class);
        fields.addAll(patientFields);
        Set<String> doctorFields = Filter.getFieldsName(DoctorDTO.class);
        fields.addAll(doctorFields);
        Set<String> disctrictFields = Filter.getFieldsName(DistrictDTO.class);
        fields.addAll(disctrictFields);
        Set<String> consultationFields = Filter.getFieldsName(ConsultationDTO.class);
        fields.addAll(consultationFields);
    	
    	Set<String> existingFields = getExistingFields();
        for (String field : fields) {
            if (!existingFields.contains(field)) {
            	createField(field);
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
    
    private void createField(String field) throws Exception {
    	Map<String, Object> fieldDefinition = Map.of(
                "name", field,
                "type", SolrIndexerAdapter.getSolrdefaultfieldtype(),
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

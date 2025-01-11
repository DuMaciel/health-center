package br.edu.utfpr.td.tsi.health_center.persistence.indexer;

import org.apache.solr.common.SolrInputDocument;

import br.edu.utfpr.td.tsi.health_center.model.Address;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.Patient;

public class PatientMapper {
	static public SolrInputDocument toSolr(Patient patient) {
		Address address = patient.getAddress();
		District district = address.getDistrict();
		
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", patient.getId());
		document.addField("name", patient.getName());
		document.addField("cpf", patient.getCpf());
		document.addField("postalCode", address.getPostalCode());
		document.addField("postalCode", address.getPostalCode());
		document.addField("street", address.getStreet());
		document.addField("number", address.getNumber());
		document.addField("complement", address.getComplement());
		document.addField("districtName", district.getName());
		
		return document;
	}
}

package br.edu.utfpr.td.tsi.health_center.persistence;

import java.util.List;
import br.edu.utfpr.td.tsi.health_center.model.Diagnosis;

public interface DiagnosisAdapter extends BaseAdapter<Diagnosis>, FindAllByFilter<Diagnosis> {
	public List<Diagnosis> findAllByIdsNotIn(List<String> ids);
}

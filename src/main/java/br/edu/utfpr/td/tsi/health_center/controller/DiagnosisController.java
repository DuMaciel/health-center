package br.edu.utfpr.td.tsi.health_center.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.utfpr.td.tsi.health_center.model.Diagnosis;
import br.edu.utfpr.td.tsi.health_center.model.dto.DiagnosisDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.model.dto.FilterOption;
import br.edu.utfpr.td.tsi.health_center.service.DiagnosisService;

@Controller
@RequestMapping("/diagnosis")
public class DiagnosisController {
	@Autowired
	private DiagnosisService diagnosisService;
	
	@GetMapping(value = "/view/{id}")
	public String showViewDiagnosisPage(@PathVariable String id, Model model) {
		Diagnosis diagnosis = diagnosisService.find(id);
		DiagnosisDTO diagnosisDTO = new DiagnosisDTO(diagnosis);
		model.addAttribute("diagnosisDTO", diagnosisDTO);
		return "diagnosis/view";
	}
	
	@GetMapping(value = "/list")
	public String listDiagnosis(@RequestParam(required = false, defaultValue = "details") String field,
			@RequestParam(required = false, defaultValue = "") String term, Model model) {
		Filter filter = new Filter(field, term);
		List<Diagnosis> diagnostics = diagnosisService.findAllByFilter(filter);
		List<DiagnosisDTO> diagnosticsDTO = new ArrayList<>();
		for (Diagnosis diagnosis : diagnostics) {
			diagnosticsDTO.add(new DiagnosisDTO(diagnosis));
		}
		model.addAttribute("diagnosticsDTO", diagnosticsDTO);
		model.addAttribute("filter", filter);
		model.addAttribute("filterOptions", FilterOption.getFilterOptions(DiagnosisDTO.class));
		return "diagnosis/list";
	}
}

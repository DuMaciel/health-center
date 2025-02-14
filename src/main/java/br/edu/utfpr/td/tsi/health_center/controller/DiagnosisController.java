package br.edu.utfpr.td.tsi.health_center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.utfpr.td.tsi.health_center.service.ConsultationService;
import br.edu.utfpr.td.tsi.health_center.service.DiagnosisService;

@Controller
@RequestMapping("/diagnosis")
public class DiagnosisController {

	@Autowired
	private ConsultationService consultationService;
	
	@Autowired
	private DiagnosisService diagnosisService;
}

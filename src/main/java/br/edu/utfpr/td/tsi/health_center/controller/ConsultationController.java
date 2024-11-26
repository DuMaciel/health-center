package br.edu.utfpr.td.tsi.health_center.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import br.edu.utfpr.td.tsi.health_center.controller.dto.ConsultationDTO;
import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.service.ConsultationService;
import br.edu.utfpr.td.tsi.health_center.service.DoctorService;
import br.edu.utfpr.td.tsi.health_center.service.PatientService;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {
	
	@Autowired
	private ConsultationService consultationService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping("")
	public RedirectView redirectToListening() {
		return new RedirectView("consultation/list");
	}
	
	@GetMapping(value = "/add")
	public String showAddConsultationPage(Model model) {
		List<Doctor> doctors = doctorService.findAll(null);
		List<Patient> patients = patientService.findAll(null);
		model.addAttribute("doctors", doctors);
		model.addAttribute("patients", patients);
		return "patient/add";
	}
	
	@PostMapping(value = "/add")
	public RedirectView addConsultation(ConsultationDTO consultationDTO, RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView("list");
		try {
			Consultation consultation = consultationDTO.toModel();
			consultationService.add(consultation);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("consultationDTO", consultationDTO);
			redirectView.setUrl("add");
		}
		return redirectView;
	}
	

	@GetMapping(value = "/edit/{id}")
	public String showEditConsultationPage(@PathVariable String id, Model model) {
		List<Doctor> doctors = doctorService.findAll(null);
		List<Patient> patients = patientService.findAll(null);
		model.addAttribute("doctors", doctors);
		model.addAttribute("patients", patients);
		if(!model.containsAttribute("consultationDTO")) {
			Consultation consultation = consultationService.find(id);
			ConsultationDTO consultationDTO = new ConsultationDTO(consultation);
			model.addAttribute("consultationDTO", consultationDTO);
		}
		return "consultation/edit";
	}
	
	@PostMapping(value = "/edit")
	public RedirectView editConsultation(ConsultationDTO consultationDTO, RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView("list");
		try {
			Consultation consultation = consultationDTO.toModel();
			consultationService.edit(consultation);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("ConsultationDTO", consultationDTO);
			redirectView.setUrl(String.format("edit/%s", consultationDTO.getId()));
		}
		return redirectView;
	}
	
	@GetMapping(value = "/list")
	public String listConsultation(Model model) {
		List<Consultation> consultation = consultationService.findAll();
		List<ConsultationDTO> consultationDTO = new ArrayList<ConsultationDTO>();
		for (Consultation consultaion : consultation) {
			consultationDTO.add(new ConsultationDTO(consultaion));
		}
		model.addAttribute("consultationDTO", consultationDTO);
		return "consultation/list";
	}

	@GetMapping(value = "/delete/{id}")
	public RedirectView deleteConsultation(@PathVariable String id) {
		RedirectView redirectView = new RedirectView("../list");
		consultationService.delete(id);
		return redirectView;
	}

}

package br.edu.utfpr.td.tsi.health_center.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.edu.utfpr.td.tsi.health_center.controller.dto.PatientDTO;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.service.DistrictService;
import br.edu.utfpr.td.tsi.health_center.service.PatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DistrictService districtService;
	
	@GetMapping("")
    public RedirectView redirectToListing() {
        return new RedirectView("/patient/list");
    }
	
	@GetMapping(value = "/add")
	public String showAddPatientPage(Model model) {
		List<District> districts = districtService.findAll(null);
		model.addAttribute("districts", districts);
		return "patient/add";
	}

	@PostMapping(value = "/add")
	public RedirectView addPatient(PatientDTO patientDTO, RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView("list");
		try {
			Patient patient = patientDTO.toModel();
			patientService.add(patient);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("patientDTO", patientDTO);
			redirectView.setUrl("add");
		}
		return redirectView;
	}
	
	@GetMapping(value = "/edit/{id}")
	public String showEditPatientPage(@PathVariable String id, Model model) {
		List<District> districts = districtService.findAll(null);
		model.addAttribute("districts", districts);
		if(!model.containsAttribute("patientDTO")) {
			Patient patient = patientService.find(id);
			PatientDTO patientDTO = new PatientDTO(patient);
			model.addAttribute("patientDTO", patientDTO);
		}
		return "patient/edit";
	}

	@PostMapping(value = "/edit")
	public RedirectView editPatient(PatientDTO patientDTO, RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView("list");
		try {
			Patient patient = patientDTO.toModel();
			patientService.edit(patient);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("patientDTO", patientDTO);
			redirectView.setUrl(String.format("edit/%s", patientDTO.getId()));
		}
		return redirectView;
	}

	@GetMapping(value = "/list")
	public String listPatient(@Nullable @RequestParam String name, Model model) {
		List<Patient> patients = patientService.findAll(name);
		List<PatientDTO> patientsDTO = new ArrayList<PatientDTO>();
		for (Patient patient : patients) {
			patientsDTO.add(new PatientDTO(patient));
		}
		model.addAttribute("patientsDTO", patientsDTO);
		model.addAttribute("name", name);
		return "patient/list";
	}

	@GetMapping(value = "/delete/{id}")
	public RedirectView deletePatient(@PathVariable String id) {
		RedirectView redirectView = new RedirectView("../list");
		patientService.delete(id);
		return redirectView;
	}
}

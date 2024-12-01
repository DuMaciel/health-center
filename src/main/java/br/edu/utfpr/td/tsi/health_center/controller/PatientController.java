package br.edu.utfpr.td.tsi.health_center.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import br.edu.utfpr.td.tsi.health_center.controller.dto.PatientDTO;
import br.edu.utfpr.td.tsi.health_center.controller.validation.PatientAddValidation;
import br.edu.utfpr.td.tsi.health_center.controller.validation.PatientEditValidation;
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
	public RedirectView addPatient(@Valid PatientAddValidation patientAddValidation, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes, 
			@RequestHeader(value = "Referer", required = false) String referer) {
		RedirectView redirectView = new RedirectView("list");
		PatientDTO patientDTO = new PatientDTO();
		BeanUtils.copyProperties(patientAddValidation, patientDTO);
		try {
			if (bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().getFirst();
				throw new RuntimeException(error.getDefaultMessage());
		    }
			Patient patient = patientDTO.toModel();
			patientService.add(patient);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("patientDTO", patientDTO);
			String urlToRedirect = referer != null ? referer : "add";
			redirectView.setUrl(urlToRedirect);
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
	public RedirectView editPatient(@Valid PatientEditValidation patientEditValidation, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			@RequestHeader(value = "Referer", required = false) String referer) {
		RedirectView redirectView = new RedirectView("list");
		PatientDTO patientDTO = new PatientDTO();
		BeanUtils.copyProperties(patientEditValidation, patientDTO);
		try {
			if (bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().getFirst();
				throw new RuntimeException(error.getDefaultMessage());
		    }
			Patient patient = patientDTO.toModel();
			patientService.edit(patient);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("patientDTO", patientDTO);
			String urlToRedirect = referer != null ? referer : String.format("edit/%s", patientDTO.getId());
			redirectView.setUrl(urlToRedirect);
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
	public RedirectView deletePatient(@PathVariable String id, RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView("../list");
		
		try {
			patientService.delete(id);
		} catch(Exception error) {
			redirectAttributes.addFlashAttribute("error", error.getMessage());
		}
		
		return redirectView;
	}
}

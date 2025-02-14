package br.edu.utfpr.td.tsi.health_center.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import br.edu.utfpr.td.tsi.health_center.controller.validation.ConsultationAddValidation;
import br.edu.utfpr.td.tsi.health_center.controller.validation.ConsultationEditValidation;
import br.edu.utfpr.td.tsi.health_center.controller.validation.DiagnosisAddValidation;
import br.edu.utfpr.td.tsi.health_center.model.Consultation;
import br.edu.utfpr.td.tsi.health_center.model.Diagnosis;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.Patient;
import br.edu.utfpr.td.tsi.health_center.model.dto.ConsultationDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.DiagnosisDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.model.dto.FilterOption;
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
		model.addAttribute("minDate",
				LocalDateTime.now().plusHours(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
		model.addAttribute("maxDate",
				LocalDateTime.now().plusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
		return "consultation/add";
	}

	@PostMapping(value = "/add")
	public RedirectView addConsultation(@Valid ConsultationAddValidation consultationAddValidation,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			@RequestHeader(value = "Referer", required = false) String referer) {
		RedirectView redirectView = new RedirectView("list");
		ConsultationDTO consultationDTO = new ConsultationDTO();
		BeanUtils.copyProperties(consultationAddValidation, consultationDTO);
		try {
			if (bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().getFirst();
				throw new RuntimeException(error.getDefaultMessage());
			}
			Consultation consultation = consultationDTO.toModel();
			consultationService.add(consultation);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("consultationDTO", consultationDTO);
			String urlToRedirect = referer != null ? referer : "add";
			redirectView.setUrl(urlToRedirect);
		}
		return redirectView;
	}

	@GetMapping(value = "/edit/{id}")
	public String showEditConsultationPage(@PathVariable String id, Model model) {
		List<Doctor> doctors = doctorService.findAll(null);
		List<Patient> patients = patientService.findAll(null);
		model.addAttribute("doctors", doctors);
		model.addAttribute("patients", patients);
		model.addAttribute("minDate",
				LocalDateTime.now().plusHours(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
		model.addAttribute("maxDate",
				LocalDateTime.now().plusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
		if (!model.containsAttribute("consultationDTO")) {
			Consultation consultation = consultationService.find(id);
			ConsultationDTO consultationDTO = new ConsultationDTO(consultation);
			model.addAttribute("consultationDTO", consultationDTO);
		}
		return "consultation/edit";
	}

	@PostMapping(value = "/edit")
	public RedirectView editConsultation(@Valid ConsultationEditValidation consultationEditValidation,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			@RequestHeader(value = "Referer", required = false) String referer) {
		RedirectView redirectView = new RedirectView("list");
		ConsultationDTO consultationDTO = new ConsultationDTO();
		BeanUtils.copyProperties(consultationEditValidation, consultationDTO);
		try {
			if (bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().getFirst();
				throw new RuntimeException(error.getDefaultMessage());
			}
			Consultation consultation = consultationDTO.toModel();
			consultationService.edit(consultation);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("ConsultationDTO", consultationDTO);
			String urlToRedirect = referer != null ? referer : String.format("edit/%s", consultationDTO.getId());
			redirectView.setUrl(urlToRedirect);
		}
		return redirectView;
	}

	@GetMapping(value = "/list")
	public String listConsultation(@RequestParam(required = false, defaultValue = "status") String field,
			@RequestParam(required = false, defaultValue = "") String term, Model model) {
		Filter filter = new Filter(field, term);
		List<Consultation> consultations = consultationService.findAllByFilter(filter);
		List<ConsultationDTO> consultationsDTO = new ArrayList<ConsultationDTO>();
		for (Consultation consultation : consultations) {
			consultationsDTO.add(new ConsultationDTO(consultation));
		}
		model.addAttribute("consultationsDTO", consultationsDTO);
		model.addAttribute("filter", filter);
		model.addAttribute("filterOptions", FilterOption.getFilterOptions(ConsultationDTO.class));
		return "consultation/list";
	}

	@GetMapping(value = "/delete/{id}")
	public RedirectView deleteConsultation(@PathVariable String id) {
		RedirectView redirectView = new RedirectView("../list");
		consultationService.delete(id);
		return redirectView;
	}

	@GetMapping(value = "/cancel/{id}")
	public RedirectView cancelConsultation(@PathVariable String id) {
		RedirectView redirectView = new RedirectView("../list");
		try {
			consultationService.cancelConsultation(id);
		} catch (Exception e) {

		}

		return redirectView;
	}

	@GetMapping(value = "/finish/{id}")
	public String showFinishConsultationPage(@PathVariable String id, Model model) {
		if (!model.containsAttribute("consultationDTO")) {
			Consultation consultation = consultationService.find(id);
			ConsultationDTO consultationDTO = new ConsultationDTO(consultation);
			model.addAttribute("consultationDTO", consultationDTO);
		}
		return "consultation/finish";
	}

	@PostMapping(value = "/finish")
	public RedirectView finishConsultation(@Valid DiagnosisAddValidation diagnosisAddValidation,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			@RequestHeader(value = "Referer", required = false) String referer) {
		RedirectView redirectView = new RedirectView("list");
		DiagnosisDTO diagnosisDTO = new DiagnosisDTO();
		BeanUtils.copyProperties(diagnosisAddValidation, diagnosisDTO);
		try {
			if (bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().getFirst();
				throw new RuntimeException(error.getDefaultMessage());
			}
			Diagnosis diagnosis = diagnosisDTO.toModel();
			consultationService.completeConsultation(diagnosis);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("DiagnosisDTO", diagnosisDTO);
			String urlToRedirect = referer != null ? referer : String.format("finish/%s", diagnosisDTO.getId());
			redirectView.setUrl(urlToRedirect);
		}
		return redirectView;
	}

}

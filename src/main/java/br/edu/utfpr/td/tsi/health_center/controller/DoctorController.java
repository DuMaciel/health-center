package br.edu.utfpr.td.tsi.health_center.controller;

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

import br.edu.utfpr.td.tsi.health_center.controller.validation.DoctorAddValidation;
import br.edu.utfpr.td.tsi.health_center.controller.validation.DoctorEditValidation;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;
import br.edu.utfpr.td.tsi.health_center.model.dto.DoctorDTO;
import br.edu.utfpr.td.tsi.health_center.model.dto.Filter;
import br.edu.utfpr.td.tsi.health_center.model.dto.FilterOption;
import br.edu.utfpr.td.tsi.health_center.service.DistrictService;
import br.edu.utfpr.td.tsi.health_center.service.DoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private DistrictService districtService;
	
	@GetMapping("")
    public RedirectView redirectToListing() {
        return new RedirectView("/doctor/list");
    }
	
	@GetMapping(value = "/add")
	public String showAddDoctorPage(Model model) {
		List<District> districts = districtService.findAll(null);
		model.addAttribute("districts", districts);
		return "doctor/add";
	}
	
	@PostMapping(value = "/add")
	public RedirectView addDoctor(@Valid DoctorAddValidation doctorAddValidation, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			@RequestHeader(value = "Referer", required = false) String referer) {
		RedirectView redirectView = new RedirectView("list");
		DoctorDTO doctorDTO = new DoctorDTO();
		BeanUtils.copyProperties(doctorAddValidation, doctorDTO);
		try {
			if (bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().getFirst();
				throw new RuntimeException(error.getDefaultMessage());
		    }
			Doctor doctor = doctorDTO.toModel();
			doctorService.add(doctor);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("doctorDTO", doctorDTO);
			String urlToRedirect = referer != null ? referer : "add";
			redirectView.setUrl(urlToRedirect);
		}
		return redirectView;
	}
	
	@GetMapping(value = "/edit/{id}")
	public String showEditDoctorPage(@PathVariable String id, Model model) {
		List<District> districts = districtService.findAll(null);
		model.addAttribute("districts", districts);
		if(!model.containsAttribute("doctorDTO")) {
			Doctor doctor = doctorService.find(id);
			DoctorDTO doctorDTO = new DoctorDTO(doctor);
			model.addAttribute("doctorDTO", doctorDTO);
		}
		return "doctor/edit";
	}
	
	@PostMapping(value = "/edit")
	public RedirectView editDoctor(@Valid DoctorEditValidation doctorEditValidation, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			@RequestHeader(value = "Referer", required = false) String referer) {
		RedirectView redirectView = new RedirectView("list");
		DoctorDTO doctorDTO = new DoctorDTO();
		BeanUtils.copyProperties(doctorEditValidation, doctorDTO);
		try {
			if (bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().getFirst();
				throw new RuntimeException(error.getDefaultMessage());
		    }
			Doctor doctor = doctorDTO.toModel();
			doctorService.edit(doctor);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("doctorDTO", doctorDTO);
			String urlToRedirect = referer != null ? referer : String.format("edit/%s", doctorDTO.getId());
			redirectView.setUrl(urlToRedirect);
		}
		return redirectView;
	}
	
	@GetMapping(value = "/list")
	public String listDoctor(@RequestParam(required = false, defaultValue = "name") String field,
			@RequestParam(required = false, defaultValue = "") String term, Model model) {
		Filter filter = new Filter(field, term);
		List<Doctor> doctors = doctorService.findAll("");
		List<DoctorDTO> doctorsDTO = new ArrayList<DoctorDTO>();
		for (Doctor doctor : doctors) {
			doctorsDTO.add(new DoctorDTO(doctor));
		}
		model.addAttribute("doctorsDTO", doctorsDTO);
		model.addAttribute("filter", filter);
		model.addAttribute("filterOptions", FilterOption.getFilterOptions(DoctorDTO.class));
		return "doctor/list";
	}
	
	@GetMapping(value = "/delete/{id}")
	public RedirectView deleteDoctor(@PathVariable String id, RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView("../list");
		try {
			doctorService.delete(id);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
		}
		return redirectView;
	}
}

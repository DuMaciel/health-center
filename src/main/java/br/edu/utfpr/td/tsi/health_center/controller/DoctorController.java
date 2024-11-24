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
import br.edu.utfpr.td.tsi.health_center.controller.dto.DoctorDTO;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.Doctor;
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
	public RedirectView addDoctor(DoctorDTO doctorDTO, RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView("list");
		try {
			Doctor doctor = doctorDTO.toModel();
			doctorService.add(doctor);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("doctorDTO", doctorDTO);
			redirectView.setUrl("add");
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
	public RedirectView editDoctor(DoctorDTO doctorDTO, RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView("../list");
		try {
			Doctor doctor = doctorDTO.toModel();
			doctorService.edit(doctor);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("doctorDTO", doctorDTO);
			redirectView.setUrl(String.format("edit/%s", doctorDTO.getId()));
		}
		return redirectView;
	}
	
	@GetMapping(value = "/list")
	public String listDoctor(@Nullable @RequestParam String name, Model model) {
		List<Doctor> doctors = doctorService.findAll(name);
		List<DoctorDTO> doctorsDTO = new ArrayList<DoctorDTO>();
		for (Doctor doctor : doctors) {
			doctorsDTO.add(new DoctorDTO(doctor));
		}
		model.addAttribute("doctorsDTO", doctorsDTO);
		model.addAttribute("name", name);
		return "doctor/list";
	}
	
	@GetMapping(value = "/delete/{id}")
	public RedirectView deleteDoctor(@PathVariable String id) {
		RedirectView redirectView = new RedirectView("../list");
		doctorService.delete(id);
		return redirectView;
	}
}

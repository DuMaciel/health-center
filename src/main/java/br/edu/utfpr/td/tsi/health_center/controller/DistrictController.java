package br.edu.utfpr.td.tsi.health_center.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.service.DistrictService;

@Controller
@RequestMapping("/district")
public class DistrictController {

	@Autowired
	private DistrictService districtService;
	
	@GetMapping(value = "/add")
	public String showAddDistrictPage() {
		return "add-district";
	}

	@PostMapping(value = "/add")
	public String addDistrict(District district) {
		districtService.add(district);
		return "redirect:/list-districts";
	}

	@GetMapping(value = "/list")
	public String listDistrict(Model model) {
		List<District> districts = districtService.findAll(null);
		model.addAttribute("districts", districts);
		return "list-districts";
	}

	@GetMapping(value = "/delete")
	public String deleteDistrict(@RequestParam String id) {
		districtService.delete(id);
		return "redirect:/list-districts";
	}

	@GetMapping(value = "/edit")
	public String showAddDistrictPage(@RequestParam String id, Model model) {
		District district = districtService.find(id);
		model.addAttribute("district", district);
		return "add-district";
	}

	@PostMapping(value = "/edit")
	public String editDistrict(District district) {
		districtService.add(district);
		return "redirect:/list-district";
	}
	
	
}

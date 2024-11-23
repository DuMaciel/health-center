package br.edu.utfpr.td.tsi.health_center.controller;

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

import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.model.service.DistrictService;

@Controller
@RequestMapping("/district")
public class DistrictController {
	@Autowired
	private DistrictService districtService;
	
	@GetMapping("")
    public RedirectView redirectToListing() {
        return new RedirectView("/district/list");
    }
	
	@GetMapping(value = "/add")
	public String showAddDistrictPage(Model model) {
		return "district/add";
	}

	@PostMapping(value = "/add")
	public RedirectView addDistrict(District district, RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView("../list");
		try {
			districtService.add(district);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("district", district);
			redirectView.setUrl("add");
		}
		return redirectView;
	}
	
	@GetMapping(value = "/edit/{id}")
	public String showAddDistrictPage(@PathVariable String id, Model model) {
		if(!model.containsAttribute("district")) {
			District district = districtService.find(id);
			model.addAttribute("district", district);
		}
		return "district/edit";
	}

	@PostMapping(value = "/edit")
	public RedirectView editDistrict(District district, RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView("../list");
		try {
			districtService.edit(district);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("district", district);
			redirectView.setUrl(String.format("edit/%s", district.getId()));
		}
		return redirectView;
	}

	@GetMapping(value = "/list")
	public String listDistrict(Model model) {
		List<District> districts = districtService.findAll(null);
		model.addAttribute("districts", districts);
		return "district/list";
	}

	@GetMapping(value = "/delete/{id}")
	public RedirectView deleteDistrict(@PathVariable String id) {
		RedirectView redirectView = new RedirectView("../list");
		districtService.delete(id);
		return redirectView;
	}
}

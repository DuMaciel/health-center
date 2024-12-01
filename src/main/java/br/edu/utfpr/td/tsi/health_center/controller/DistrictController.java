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

import br.edu.utfpr.td.tsi.health_center.controller.dto.DistrictDTO;
import br.edu.utfpr.td.tsi.health_center.controller.validation.DistrictAddValidation;
import br.edu.utfpr.td.tsi.health_center.controller.validation.DistrictEditValidation;
import br.edu.utfpr.td.tsi.health_center.model.District;
import br.edu.utfpr.td.tsi.health_center.service.DistrictService;

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
	public RedirectView addDistrict(@Valid DistrictAddValidation districtAddValidation, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes, 
			@RequestHeader(value = "Referer", required = false) String referer) {
		RedirectView redirectView = new RedirectView("list");
		DistrictDTO districtDTO = new DistrictDTO();
		BeanUtils.copyProperties(districtAddValidation, districtDTO);
		try {
			if (bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().getFirst();
				throw new RuntimeException(error.getDefaultMessage());
		    }
			District district = districtDTO.toModel();
			districtService.add(district);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("districtDTO", districtDTO);
			String urlToRedirect = referer != null ? referer : "add";
			redirectView.setUrl(urlToRedirect);
		}
		return redirectView;
	}
	
	@GetMapping(value = "/edit/{id}")
	public String showEditDistrictPage(@PathVariable String id, Model model) {
		if(!model.containsAttribute("district")) {
			District district = districtService.find(id);
			DistrictDTO districtDTO = new DistrictDTO(district);
			model.addAttribute("districtDTO", districtDTO);
		}
		return "district/edit";
	}

	@PostMapping(value = "/edit")
	public RedirectView editDistrict(@Valid DistrictEditValidation DistrictEditValidation, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes, 
			@RequestHeader(value = "Referer", required = false) String referer) {
		RedirectView redirectView = new RedirectView("list");
		DistrictDTO districtDTO = new DistrictDTO();
		BeanUtils.copyProperties(DistrictEditValidation, districtDTO);
		try {
			if (bindingResult.hasErrors()) {
				ObjectError error = bindingResult.getAllErrors().getFirst();
				throw new RuntimeException(error.getDefaultMessage());
		    }
			District district = districtDTO.toModel();
			districtService.edit(district);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
			redirectAttributes.addFlashAttribute("districtDTO", districtDTO);
			String urlToRedirect = referer != null ? referer : String.format("edit/%s", districtDTO.getId());
			redirectView.setUrl(urlToRedirect);
		}
		return redirectView;
	}

	@GetMapping(value = "/list")
	public String listDistrict(@Nullable @RequestParam String name, Model model) {
		List<District> districts = districtService.findAll(name);
		List<DistrictDTO> districtsDTO = new ArrayList<DistrictDTO>();
		for (District district : districts) {
			districtsDTO.add(new DistrictDTO(district));
		}
		model.addAttribute("districtsDTO", districtsDTO);
		model.addAttribute("name", name);
		return "district/list";
	}

	@GetMapping(value = "/delete/{id}")
	public RedirectView deleteDistrict(@PathVariable String id, RedirectAttributes redirectAttributes) {
		RedirectView redirectView = new RedirectView("../list");
		try {
			districtService.delete(id);
		} catch (Exception e) {
			String error = e.getMessage();
			redirectAttributes.addFlashAttribute("error", error);
		}
		return redirectView;
	}
}

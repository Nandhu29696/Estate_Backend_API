package com.backend.demo.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.demo.modal.Estate;
import com.backend.demo.service.EstateService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EstateController {

	@Autowired
	EstateService estateService;

	//API for create record
	@RequestMapping(value = "/saverec", method = RequestMethod.POST)
	public Map<String, Object> saveRec(@RequestBody Estate estate) {
		return estateService.SaveEstate(estate);
	}

	//API for get one record
	@RequestMapping(value = "/getOneRec/{id}", method = RequestMethod.GET)
	public Map<String, Object> getOneRec(@PathVariable int id) {
		return estateService.getOneEstateDet(id);
	} 

	//API for save all records
	@RequestMapping(value = "/saveAllRec", method = RequestMethod.POST)
	public Map<String, Object> saveAllRec(@RequestBody @Valid List<Estate> estateList) {
		return estateService.saveItems(estateList);
	}

}

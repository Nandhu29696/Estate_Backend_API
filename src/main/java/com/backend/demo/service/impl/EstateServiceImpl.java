package com.backend.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.demo.modal.Estate;
import com.backend.demo.repository.EstateRepository;
import com.backend.demo.service.EstateService;

@Service
public class EstateServiceImpl implements EstateService {

	@Autowired
	EstateRepository eRepository;

	@Override
	public Map<String, Object> SaveEstate(Estate estate) {
		Map<String, Object> result = new HashMap<String, Object>();

		if (estate.getName() == null || estate.getLocation() == null) {
			result.put("Status", 400);
			result.put("Error", "Please provide valid input details");
		} else if (estate.getName().length() <= 3) {
			result.put("Status", 400);
			result.put("Error", "Entered name should be morethen 3 characters");
		} else if (estate.getLocation().length() <= 3) {
			result.put("Status", 400);
			result.put("Error", "Entered location should be morethen 3 characters");
		} else if (estate.getName().length() <= 3) {
			result.put("Status", 400);
			result.put("Error", "Entered name should be morethen 3 characters");
		} else {

			Estate existname = eRepository.FindtByName(estate.getName());
			if (existname == null) {
				Estate newRecord = eRepository.save(estate);
				result.put("Status", 200);
				result.put("Message", "Record saved");
				result.put("data", newRecord);

			} else {
				result.put("Status", 409);
				result.put("message", "The name already exists. Please provide a unique name.");
			}

		}

		return result;
	}

	@Override
	public Map<String, Object> getOneEstateDet(int id) {
		Map<String, Object> result = new HashMap<String, Object>();

		Estate getOneDet = eRepository.getByID(id);
		if (getOneDet == null) {
			result.put("Status", 400);
			result.put("Error", "Item with this ID: " + id + " not found");
		} else {
			result.put("Status", 200);
			result.put("data", getOneDet);
		}
		return result;
	}

	@Override
	public Map<String, Object> saveItems(List<Estate> estateList) {

		Map<String, Object> result = new HashMap<String, Object>();

		// Check if array length is 0
		if (estateList.size() == 0) {
			result.put("Status", 400);
			result.put("message", "Please enter valid input fields");
		}

		// Check if any name is blank in the list
		else if (estateList.stream().anyMatch(item -> item.getName().isBlank())) {
			result.put("Status", 400);
			result.put("message", "Name cannot be blank");
		}

		// Check if any name length is less then 3 character
		else if (estateList.stream().anyMatch(item -> item.getName().length() < 3)) {

			result.put("Status", 400);
			result.put("message", "Name length must not exceed 3 characters");
		}

		// Check if any location is blank in the list
		else if (estateList.stream().anyMatch(item -> item.getLocation().isBlank())) {

			result.put("Status", 400);
			result.put("message", "Location cannot be blank");
		}

		// Check if any location length is less then 3 character
		else if (estateList.stream().anyMatch(item -> item.getName().length() < 3)) {

			result.put("Status", 400);
			result.put("message", "Location length must not exceed 3 characters");
		}

		else {
			eRepository.saveAll(estateList);
			result.put("Status", 200);
			result.put("message", "Items saved successfully");
		}

		return result;
	}

}

package com.backend.demo.service;

import java.util.List;
import java.util.Map;

import com.backend.demo.modal.Estate;

public interface EstateService {

	Map<String, Object> SaveEstate(Estate estate);
	
	Map<String, Object> getOneEstateDet(int id);
	
	Map<String, Object> saveItems(List<Estate> items);
}

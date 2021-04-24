package com.pictlibrary.dev.service;

import com.pictlibrary.dev.model.AppCategory;
import com.pictlibrary.dev.repository.AppCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppCategoryService {
    @Autowired
    private AppCategoryRepository appCategoryRepository;

	public AppCategory getAppCategoryById(int categoryId) {
		return appCategoryRepository.findById(categoryId).orElse(null);
	}

	public Iterable<AppCategory> getAllCategory() {
		return appCategoryRepository.findAll();
	}
}

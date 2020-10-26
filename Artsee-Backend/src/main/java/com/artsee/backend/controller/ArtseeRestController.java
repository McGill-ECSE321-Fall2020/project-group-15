package com.artsee.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.artsee.backend.service.ArtseeService;

@CrossOrigin(origins = "*")
@RestController
public class ArtseeRestController {

	@Autowired
	private ArtseeService service;

}
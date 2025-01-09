package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleSellDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleSellDTO> findById(@PathVariable Long id) {
		SaleSellDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleSellDTO>> getReport(@RequestParam(value = "minDate", required = false) String minDate,
													   @RequestParam(value = "maxDate", required = false) String maxDate,
													   @RequestParam(value = "name", defaultValue = "") String name,
													   Pageable pageable) {
		Page<SaleSellDTO> dto = service.getReport(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleSumDTO>> getSummary(@RequestParam(value = "minDate", required = false) String minDate,
													   @RequestParam(value = "maxDate", required = false) String maxDate,
													   Pageable pageable) {
		Page<SaleSumDTO> dto = service.getSumary(minDate, maxDate, pageable);
		return ResponseEntity.ok(dto);
	}

}

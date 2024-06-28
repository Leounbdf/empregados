package com.mgs.empregados.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.mgs.empregados.dtos.EmpregadoDto;
import com.mgs.empregados.models.EmpregadoModel;
import com.mgs.empregados.repositories.EmpregadoRepository;

import jakarta.validation.Valid;


@RestController
public class EmpregadosController {

	@Autowired
	EmpregadoRepository empregadoRepository;
	
	@GetMapping("/empregados")
	public ResponseEntity<List<EmpregadoModel>> listar(){
		List<EmpregadoModel> empregadoList = empregadoRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(empregadoList);
	}
	
	@PostMapping("/empregados")
	public ResponseEntity<EmpregadoModel> salvar(@RequestBody @Valid EmpregadoDto empregadoDto) {
		var empregadoModel = new EmpregadoModel();
		BeanUtils.copyProperties(empregadoDto, empregadoModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(empregadoRepository.save(empregadoModel));
	}
	
	@GetMapping("/empregados/{id}")
	public ResponseEntity<Object> detalhar(@PathVariable(value="id") Integer id) {
		Optional<EmpregadoModel> empregado = empregadoRepository.findById(id);
		if(empregado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empregado não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(empregado.get());
	}
		
	@PutMapping("/empregados/{id}")
	public ResponseEntity<Object> atualizarEmpregado(@PathVariable(value="id") Integer id, @RequestBody @Valid EmpregadoDto empregadoDto) {
		Optional<EmpregadoModel> empregado = empregadoRepository.findById(id);
		if(empregado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empregado não encontrado");
		}
		var empregadoModel = empregado.get();
			BeanUtils.copyProperties(empregadoDto, empregadoModel);
			return ResponseEntity.status(HttpStatus.OK).body(empregadoRepository.save(empregadoModel));
		}
	
	@DeleteMapping("/empregados/{id}")
	public ResponseEntity<Object> deleteEmpregado(@PathVariable(value="id") Integer id) {
		Optional<EmpregadoModel> empregado = empregadoRepository.findById(id);
		if(empregado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empregado não encontrado");
		}
		empregadoRepository.delete(empregado.get());
		return ResponseEntity.status(HttpStatus.OK).body("O empregado foi excluído");
	}
	
	
	
}


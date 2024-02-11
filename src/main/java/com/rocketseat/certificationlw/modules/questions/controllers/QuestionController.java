package com.rocketseat.certificationlw.modules.questions.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.certificationlw.modules.questions.dto.AlternativeResultDTO;
import com.rocketseat.certificationlw.modules.questions.dto.QuestionResultDTO;
import com.rocketseat.certificationlw.modules.questions.entities.AlternativeEntity;
import com.rocketseat.certificationlw.modules.questions.entities.QuestionEntity;
import com.rocketseat.certificationlw.modules.questions.repositories.QuestionRepo;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuestionRepo questionRepo;

	@GetMapping("/technology/{technology}")
	public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
		var result = this.questionRepo.findByTechnology(technology);
		var toMap = result.stream().map(question -> mapQuestionToDTO(question)).collect(Collectors.toList());
		return toMap;
	}

	static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
		var questionResultDTO = QuestionResultDTO.builder()
			.id(question.getId())
			.technology(question.getTechnology())
			.description(question.getDescription()).build();

		List<AlternativeResultDTO> alternativeResultDTOs = 	question.getAlternatives()
			.stream().map(alternative -> mapAlternativeDTO(alternative))
			.collect(Collectors.toList());

		questionResultDTO.setAlternatives(alternativeResultDTOs);
		return questionResultDTO;
	}

	static AlternativeResultDTO mapAlternativeDTO(AlternativeEntity alternativeResultDTO) {
		return AlternativeResultDTO.builder()
			.id(alternativeResultDTO.getId())
			.description(alternativeResultDTO.getDescription()).build();
	}
}

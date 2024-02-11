package com.rocketseat.certificationlw.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.certificationlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.certificationlw.modules.students.dto.VerifyIfHasCertificationDTO;
import com.rocketseat.certificationlw.modules.students.useCases.StudentCertificationAnswersUseCase;
import com.rocketseat.certificationlw.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

	@Autowired
	private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

	@PostMapping("/verifyIfHasCertification")
	public String verifyIfHasCertification(@RequestBody VerifyIfHasCertificationDTO dto) {
		var res = this.verifyIfHasCertificationUseCase.exec(dto);
		if(res) return "Usuário já fez a prova";
		return "Usuário pode fazer a prova";
	}

	@PostMapping("/certification/answer")
	public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswer) {
		try {
			var result = this.studentCertificationAnswersUseCase.exec(studentCertificationAnswer);
			return ResponseEntity.ok().body(result);
		} catch(Exception err) {
			return ResponseEntity.badRequest().body(err.getMessage());
		}
	}
}

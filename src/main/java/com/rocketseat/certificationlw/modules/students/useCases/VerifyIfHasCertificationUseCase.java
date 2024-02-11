package com.rocketseat.certificationlw.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.certificationlw.modules.students.dto.VerifyIfHasCertificationDTO;
import com.rocketseat.certificationlw.modules.students.repositories.CertificationStudentRepo;

@Service
public class VerifyIfHasCertificationUseCase {
	@Autowired
	private CertificationStudentRepo certificationStudentRepo;

	public boolean exec(VerifyIfHasCertificationDTO dto) {
		var res = this.certificationStudentRepo.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());
		if(res.isEmpty()) return false;
		return true;
	}
}

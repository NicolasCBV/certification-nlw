package com.rocketseat.certificationlw.modules.certifications.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.certificationlw.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.certificationlw.modules.students.repositories.CertificationStudentRepo;

@Service
public class Top10RankingUseCase {
	@Autowired
	public CertificationStudentRepo certificationStudentRepo;

	public List<CertificationStudentEntity> exec() {
		var result = this.certificationStudentRepo.findTop10ByOrderGradeDesc();
		return result;
	}
}

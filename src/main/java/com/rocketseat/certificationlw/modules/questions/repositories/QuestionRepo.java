package com.rocketseat.certificationlw.modules.questions.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.certificationlw.modules.questions.entities.QuestionEntity;

public interface QuestionRepo extends JpaRepository<QuestionEntity, UUID> {
	List<QuestionEntity> findByTechnology(String technology);
}

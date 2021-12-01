package com.sabitov.repository;

import com.sabitov.models.Survey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SurveyRepository extends CrudRepository<Survey, Long> {
List<Survey> findAll();
}

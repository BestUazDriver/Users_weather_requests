package com.sabitov.controllers;

import com.sabitov.models.Survey;
import com.sabitov.repository.SurveyRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/hello")
@Api(description = "Survey controller")
public class SurveyController {
    @Autowired
    private SurveyRepository surveyRepository;

    @GetMapping
    @ApiOperation("Get all surveys from database")
    public String hello(@RequestParam(name = "text", defaultValue = "Someone") String text, Model model) {
        model.addAttribute("text", text);
        Iterable<Survey> all = surveyRepository.findAll();
        System.out.println(all);
        return "hello";
    }

    @PostMapping("add_survey")
    @ApiOperation("Save new surveys")
    public String addSurvey(@RequestParam String name, @RequestParam String start_date,
                            @RequestParam String end_date, @RequestParam boolean is_active, Model model) {
        LocalDate localStartDate = new SurveyController().localDate(start_date);
        LocalDate localEndDate = new SurveyController().localDate(end_date);
        Survey survey = Survey.builder().name(name).startDate(localStartDate).endDate(localEndDate).isActive(is_active).build();
        surveyRepository.save(survey);
        List<Survey> surveyList = surveyRepository.findAll();
        model.addAttribute("surveys", surveyList);
        return "hello";
    }

    @PostMapping("delete_survey")
    @ApiOperation("Delete survey")
    public String deleteSurvey(@RequestParam String deleteId) {
        System.out.println("delete id: " + deleteId);
        deleteId = deleteId.replace("<p ", "");
        try {
            long l = Long.parseLong(deleteId);
            surveyRepository.deleteById(l);
        } catch (NumberFormatException | EmptyResultDataAccessException  e) {
            System.out.println("null");
        }
        return "hello";
    }

    @PostMapping
    @ApiIgnore
    public String hello() {
        return "hello";
    }


    @PutMapping
    @ApiOperation("Update survey")
    public String updateSurvey(@RequestParam Long id, @RequestParam String name, @RequestParam String start_date, @RequestParam String end_date, @RequestParam String is_active) {
        LocalDate localStartDate = new SurveyController().localDate(start_date);
        LocalDate localEndDate = new SurveyController().localDate(end_date);
        Survey survey = Survey.builder().
                id(id).
                name(name).
                startDate(localStartDate).
                endDate(localEndDate).
                isActive(Boolean.parseBoolean(is_active)).
                build();

        try {
            Survey oldSurvey = surveyRepository.findById(id).get();
            surveyRepository.delete(oldSurvey);
            surveyRepository.save(survey);
        }catch(EmptyResultDataAccessException e){

        }
        return "hello";
    }

    private LocalDate localDate(String stringDate) {
        String[] attributes = stringDate.split("-");
        LocalDate localDate = LocalDate.of(Integer.parseInt(attributes[0]), Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]));
        return localDate;
    }
}

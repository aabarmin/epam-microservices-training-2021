package com.epam.training.microservice.service.recipes.controller;

import com.epam.training.microservice.service.recipes.model.Recipe;
import com.epam.training.microservice.service.recipes.model.RecipeLine;
import com.epam.training.microservice.service.recipes.repository.RecipeRepository;
import com.epam.training.microservice.service.recipes.service.DrugServiceFeignClient;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {
  @Autowired
  private RecipeRepository recipeRepository;

  @Autowired
  private DrugServiceFeignClient drugServiceClient;

  @GetMapping("/recipes")
  public Collection<RecipeRestModel> findAll() {
    return StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
        .map(this::toModel)
        .collect(Collectors.toList());
  }

  private RecipeRestModel toModel(Recipe recipe) {
    return RecipeRestModel.builder()
        .doctor(recipe.getDoctor())
        .recipient(recipe.getRecipient())
        .issueDate(recipe.getIssueDate())
        .lines(toLinesModel(recipe.getLines()))
        .build();
  }

  private Set<RecipeRestLineModel> toLinesModel(Set<RecipeLine> lines) {
    return lines.stream()
        .map(this::toLineModel)
        .collect(Collectors.toSet());
  }

  private RecipeRestLineModel toLineModel(RecipeLine line) {
    return RecipeRestLineModel.builder()
        .amount(line.getAmount())
        .instruction(line.getInstruction())
        .drugModel(drugServiceClient.findById(line.getDrugId()).getContent())
        .build();
  }
}

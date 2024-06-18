package com.example.graphql.Controllers;

import com.example.graphql.Model.Coach;
import com.example.graphql.Model.Difficulty;
import com.example.graphql.Model.FitnessClass;
import graphql.GraphQLContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class FitnessController {

    @QueryMapping
    public List<FitnessClass> schedule(@Argument Coach coach
    , GraphQLContext graphQLContext){
        log.info("schedule ({})", coach);
        return List.of(FitnessClass.builder()
                        .id(UUID.randomUUID())
                        .coach(coach)
                        .startsAt(LocalDateTime.now())
                        .endsAt(LocalDateTime.now().plusHours(11))
                        .difficulty(Difficulty.BEGINNER)
                .build());
    }
}

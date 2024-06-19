package com.example.graphql.Controllers;

import com.example.graphql.Model.Coach;
import com.example.graphql.Model.Difficulty;
import com.example.graphql.Model.FitnessClass;
import graphql.GraphQLContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class FitnessController {

    @QueryMapping
    public List<FitnessClass> schedule(@Argument Coach coach
            , GraphQLContext context) {
        log.info("schedule ({})", coach);
        context.put("passThroughValueToBatchMapping", "batchMappingValue");
        return List.of(FitnessClass.builder()
                .id(UUID.randomUUID())
                .coach(coach)
                .startsAt(LocalDateTime.now())
                .endsAt(LocalDateTime.now().plusHours(11))
                .difficulty(Difficulty.BEGINNER)
                .build());
    }

    @BatchMapping
    public Callable<Map<FitnessClass, Difficulty>> difficulty(List<FitnessClass> fitnessClasses,
                                                              GraphQLContext context,
                                                              @ContextValue String passThroughValueToBatchMapping) {
        log.info("difficulty {}", passThroughValueToBatchMapping);
        return () ->
                fitnessClasses.stream().collect(Collectors.toUnmodifiableMap(
                        Function.identity(),
                        ignore -> Difficulty.BEGINNER
                ));
    }
}

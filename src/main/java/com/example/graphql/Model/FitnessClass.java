package com.example.graphql.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
public class FitnessClass {
    private UUID id;
    private Coach coach;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    private Difficulty difficulty;
}

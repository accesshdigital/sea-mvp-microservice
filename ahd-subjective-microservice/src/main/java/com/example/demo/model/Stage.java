package com.example.demo.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Stage {
private String StageType;
private String StageSummary;
private String StageReference;

}

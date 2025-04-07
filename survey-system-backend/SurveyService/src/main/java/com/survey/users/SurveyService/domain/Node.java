package com.survey.users.SurveyService.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="node", indexes = {@Index(columnList = "name", unique = true)})
@Getter
@Setter
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Node parent;
    @ManyToOne
    private Survey survey;
    @ManyToOne
    private Domain domain;
    private Long organizationId;
    private Long userId;
    private boolean priv;

    @Override
    public String toString() {
        if(this.parent != null)
            return this.id + this.name + this.parent.name;
        else
            return this.id + this.name;
    }
}

package com.joekenny.edge_config_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EdgeConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String content;
    private int revision;

    protected EdgeConfiguration() {

    }

    public EdgeConfiguration(String name, String content, int revision) {
        this.name = name;
        this.content = content;
        this.revision = revision;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public int getRevision() {
        return revision;
    }

}
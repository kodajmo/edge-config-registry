package com.joekenny.edge_config_api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/configurations")
public class EdgeConfigurationController {
    private final EdgeConfigurationRepository repository;

    public EdgeConfigurationController(EdgeConfigurationRepository repository) {
        this.repository = repository;
    }

    public record CreateConfigurationRequest(String name, String content) {}

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public EdgeConfiguration create(@RequestBody CreateConfigurationRequest request) {
        EdgeConfiguration configuration =
        new EdgeConfiguration(request.name(), request.content(), 1);
        return repository.save(configuration);
    }

    
}
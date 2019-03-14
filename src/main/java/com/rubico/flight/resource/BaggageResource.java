package com.rubico.flight.resource;

import com.rubico.flight.service.BaggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class BaggageResource {
    private BaggageService baggageService;

    @Autowired
    public BaggageResource(BaggageService baggageService) {
        this.baggageService = baggageService;
    }

    @GetMapping("/baggage/ischeckinsucceeded")
    public ResponseEntity<Boolean> isSucceeded(@NotNull @PathVariable Integer destinationId, @NotNull @PathVariable String baggageId) {
        Boolean succeeded = baggageService.isBaggageCheckInSucceeded(destinationId, baggageId);
        return ResponseEntity.ok().body(succeeded);
    }

}

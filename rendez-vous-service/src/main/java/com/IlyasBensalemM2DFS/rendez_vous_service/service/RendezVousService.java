package com.IlyasBensalemM2DFS.rendez_vous_service.service;

import com.IlyasBensalemM2DFS.rendez_vous_service.model.RendezVous;
import com.google.api.services.calendar.model.EventDateTime;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.*;

@Service
public class RendezVousService {

    private static Set<RendezVous> rendezVousDatabase = new HashSet<>();

    static{
        RendezVous rendezVous = new RendezVous();
        rendezVous.setPatientId("patientId");
        rendezVous.setPraticienId("praticien");
        rendezVous.setStatus("CONFIRMED");
        rendezVous.setGoogleEventId("someId");

    }


    private final Calendar googleCalendarService;

    private static final String CALENDAR_ID = "primary";


    public RendezVousService(Calendar googleCalendarService) {
        this.googleCalendarService = googleCalendarService;
    }

    @CircuitBreaker(name="RendezVousService", fallbackMethod="creerRendezVousFallback")
    @Retryable(
            retryFor = { IOException.class, HttpClientErrorException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public RendezVous creerRendezVous(RendezVous rendezVous) throws IOException {

        Event event = new Event()
                .setSummary("Rendez-vous medical")
                .setDescription(String.format("Appointment between patient %s and practitioner %s",
                        rendezVous.getPatientId(), rendezVous.getPraticienId()));

        EventDateTime start = new EventDateTime().setDateTime(rendezVous.getStartTime());
        EventDateTime end = new EventDateTime().setDateTime(rendezVous.getEndTime());
        event.setStart(start);
        event.setEnd(end);

        event = googleCalendarService.events().insert(CALENDAR_ID, event).execute();

        rendezVous.setGoogleEventId(event.getId());
        rendezVous.setStatus("CONFIRMED");
        rendezVousDatabase.add(rendezVous);
        return rendezVous;
    }

    public RendezVous creerRendezVousFallback(RendezVous rendezVous) throws IOException {
       rendezVous.setStatus("CIRCUIT_OPEN");
       rendezVousDatabase.add(rendezVous);
       return rendezVous;
    }


    public List<RendezVous> getAllRendezVous(){
        return new ArrayList<>(rendezVousDatabase);
    }

    public List<RendezVous> getRendezVousByPraticienAndPatient(
            String patientId,
            String praticienId
    ){
        return rendezVousDatabase.stream()
                .filter(r -> r.getPatientId().equals(r.getPatientId()) && r.getPraticienId().equals(r.getPatientId()) )
                .toList();
    }


}

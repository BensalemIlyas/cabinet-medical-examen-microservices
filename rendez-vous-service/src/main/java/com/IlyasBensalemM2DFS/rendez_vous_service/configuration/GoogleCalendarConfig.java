package com.IlyasBensalemM2DFS.rendez_vous_service.configuration;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Configuration
public class GoogleCalendarConfig {

    @Bean
    public Calendar googleCalendarService() throws GeneralSecurityException, IOException {

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(),
                new InputStreamReader(getClass().getResourceAsStream("/credentials.json"))
        );

        // Configurer le flux d'autorisation Google
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                clientSecrets,
                Collections.singleton(CalendarScopes.CALENDAR))
                .setAccessType("offline")
                .build();

        // Serveur local pour capturer le consentement de l'utilisateur
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        // Obtenir les informations d'identification
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        // Construire et retourner le service Google Calendar
        return new Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                credential)
                .setApplicationName("Medical Office Management")
                .build();
    }
//    }

//        GoogleCredentials credentials = GoogleCredentials.fromStream(
//                        new ClassPathResource("credentials.json").getInputStream())
//                .createScoped(Collections.singleton(CalendarScopes.CALENDAR));
//
//        return new Calendar.Builder(
//                GoogleNetHttpTransport.newTrustedTransport(),
//                JacksonFactory.getDefaultInstance(),
//                new HttpCredentialsAdapter(credentials))
//                .setApplicationName("Medical Office Management")
//                .build();
//    }
}
package com.pomhotel.booking.ui.api.v1.controllers;

import com.pomhotel.booking.ui.api.v1.services.RandomMusicURLService;
import com.pomhotel.booking.ui.api.v1.services.RandomMusicURLServiceImplementation;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class MusicController {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("MusicController.class");

    @GetMapping("music")
    public ResponseEntity<String> musicApi() {
        String link="";
        RandomMusicURLService music = new RandomMusicURLServiceImplementation();
        link="{ \"link\":\""+music.getRandomMusicURL()+"\" }";
        return new ResponseEntity(link, HttpStatus.OK);
    }
}

package com.pomhotel.booking.ui.api.controllers;

import com.pomhotel.booking.ui.api.services.RandomMusicURLService;
import com.pomhotel.booking.ui.api.services.RandomMusicURLServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://pom-hotel.code:3000", maxAge = 3600)
@RestController
public class MusicApiController {

    @GetMapping(value = "/api/music")
    public ResponseEntity<String> musicApi() {
        String link="";
        RandomMusicURLService music = new RandomMusicURLServiceImplementation();
        link="{ \"link\":\""+music.getRandomMusicURL()+"\" }";
        return new ResponseEntity(link, HttpStatus.OK);
    }
}

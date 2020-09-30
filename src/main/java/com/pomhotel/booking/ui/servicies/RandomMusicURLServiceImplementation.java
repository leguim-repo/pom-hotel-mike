package com.pomhotel.booking.ui.servicies;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 *  EasterEgg
 *  <a th:href="${@RandomMusicURL.getRandomMusicURL()}" target="_blank">EasterEgg</a>
 */
@Service
public class RandomMusicURLServiceImplementation implements RandomMusicURLService {
    @Override
    public String getRandomMusicURL() {
            String[] musicURLS = {
                    "https://youtu.be/GJkuTx1DQzg",
                    "https://youtu.be/Y6A_Czw8TFU",
                    "https://youtu.be/_WCur8LhH8I",
                    "https://youtu.be/m30QEZG8aQA",
                    "https://youtu.be/FIMnXw_3Rgk",
                    "https://youtu.be/vy-k0FopsmY",
                    "https://youtu.be/kwKrNtq9gHI",
                    "https://youtu.be/d1-Xc7EfT44",
                    "https://youtu.be/k1d1twnD7Tk",
            };
            int rnd = new Random().nextInt(musicURLS.length);
            return musicURLS[rnd];
    }
}

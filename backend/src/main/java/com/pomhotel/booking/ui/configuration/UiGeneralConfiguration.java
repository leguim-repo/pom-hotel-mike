package com.pomhotel.booking.ui.configuration;

import com.pomhotel.booking.ui.services.RandomMusicURLService;
import com.pomhotel.booking.ui.services.RandomMusicURLServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//--- Configuration in General -------------------------------------
@Configuration
public class UiGeneralConfiguration {

    //--- Beans ----------------------------------------------------
    @Bean(name="RandomMusicURL")
    public RandomMusicURLService callRandomMusicURLService () {
        RandomMusicURLService RandomMusicURL = new RandomMusicURLServiceImplementation();
        return RandomMusicURL;
    }


}

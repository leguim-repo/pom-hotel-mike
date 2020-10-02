package com.pomhotel.booking.ui.configuration;

import org.springframework.security.crypto.password.PasswordEncoder;


class PasswordEnconderPlaintText implements PasswordEncoder {
    // Note: Debe estar en el mismo package que UiSecurityConfiguration

    //--- Functions ----------------------------------------------------
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }

}
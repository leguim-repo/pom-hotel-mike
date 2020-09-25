package com.pomhotel.booking.ui.configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

class PasswordEnconderPlaintText implements PasswordEncoder {
    /*
    Debe estar en el mismo package que UiSecurityConfiguration
     */

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}
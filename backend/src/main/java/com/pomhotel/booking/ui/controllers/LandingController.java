package com.pomhotel.booking.ui.controllers;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Controller
public class LandingController {
    private static final org.apache.commons.logging.Log Logger = LogFactory.getLog("LandingController.class");

    private List<String> ipAvailables = new ArrayList<>();
    @Autowired
    public LandingController() {
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface nif: Collections.list(nets)) {
                if ( (nif.isUp()) && (!nif.isLoopback())) {
                    for (int i=0; i < nif.getInterfaceAddresses().size(); i++) {
                        if (nif.getInterfaceAddresses().get(i).getAddress().isSiteLocalAddress()) {
                            Logger.info("ip: "+nif.getInterfaceAddresses().get(i).getAddress().getHostAddress());
                            Logger.info("isLinkLocalAddress: "+nif.getInterfaceAddresses().get(i).getAddress().isLinkLocalAddress());
                            Logger.info("isAnyLocalAddress: "+nif.getInterfaceAddresses().get(i).getAddress().isAnyLocalAddress());
                            Logger.info("isSiteLocalAddress: "+nif.getInterfaceAddresses().get(i).getAddress().isSiteLocalAddress());
                            Logger.info("-------------------------");
                            ipAvailables.add(nif.getInterfaceAddresses().get(i).getAddress().getHostAddress());
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    //--- Home Mappings -----------------------------------------------------
    @GetMapping("/")
    public String landingController(Model model) {
        //return "redirect:/home";
        Logger.info("ipAvailables: "+ipAvailables.toString());
        model.addAttribute("ipAvailables", ipAvailables);
        return "landing";
    }
}

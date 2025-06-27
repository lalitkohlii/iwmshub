package com.iwmshub.service;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class IwmsService {
    
    private static final Logger logger = Logger.getLogger(IwmsService.class.getName());
    
    public String processData(String input) {
        logger.info("Processing data: " + input);
        // Business logic here
        return "Processed: " + input;
    }
}

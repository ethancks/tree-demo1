package com.lemon.tree.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class Tracer {
    private static final Logger LOGGER = LoggerFactory.getLogger("Logger");

    public static void info(String messagePattern, Object... args) {
        LOGGER.info(MessageFormat.format(messagePattern, args));
    }

    public static void warning(String messagePattern, Object... args) {
        LOGGER.warn(MessageFormat.format(messagePattern, args));
    }
}

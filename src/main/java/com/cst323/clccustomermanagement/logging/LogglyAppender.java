package com.cst323.clccustomermanagement.logging;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class LogglyAppender extends AppenderBase<ILoggingEvent> {

    private String logglyUrl;

    @Override
    public void start() {
        logglyUrl = System.getenv("LOGGLY_URL");
        super.start();
    }

    @Override
    protected void append(ILoggingEvent event) {

        if (logglyUrl == null || logglyUrl.isBlank()) {
            return;
        }

        try {
            URI uri = URI.create(logglyUrl);
            HttpURLConnection connection =
                    (HttpURLConnection) uri.toURL().openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setDoOutput(true);

            String message =
                    event.getTimeStamp() + " "
                    + event.getLevel() + " "
                    + event.getLoggerName() + " - "
                    + event.getFormattedMessage();

            try (OutputStream output = connection.getOutputStream()) {
                output.write(message.getBytes(StandardCharsets.UTF_8));
            }

            connection.getResponseCode();
            connection.disconnect();

        } catch (Exception e) {
            addError("Unable to send log to Loggly", e);
        }
    }
}
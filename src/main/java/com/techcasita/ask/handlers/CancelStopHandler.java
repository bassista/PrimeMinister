/*
 * CancelStopHandler, a very basic RequestHandler
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
package com.techcasita.ask.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.techcasita.ask.templates.Layout;

import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * A single RequestHandler can be configured to respond to more than one Intent.
 * Here the CancelStopHandler is set up to respond to CancelIntent, as well as to the StopIntent.
 * Say goodbye and display the mathematician full screen, without additional text and end the session
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
public class CancelStopHandler implements RequestHandler {
    private static final ResourceBundle TXT = ResourceBundle.getBundle("text");

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        final String speechText = TXT.getString("gb" + new Random().nextInt(4));
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard(TXT.getString("skill_title"), speechText)
                // display
                .addRenderTemplateDirective(Layout.T6("mathematician", ""))
                .withShouldEndSession(true)
                .build();
    }
}

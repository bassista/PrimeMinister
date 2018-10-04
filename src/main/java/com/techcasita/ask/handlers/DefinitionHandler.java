/*
 * DefinitionHandler, a very basic RequestHandler
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
 * Simply answers what a prime number is in general.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
public class DefinitionHandler implements RequestHandler {
    private static final ResourceBundle TXT = ResourceBundle.getBundle("text");

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("Definition"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        final String speechText = TXT.getString("whatis" + new Random().nextInt(5));
        return input.getResponseBuilder()
                // Speech Synthesis
                .withSpeech(speechText)
                // Display
                .withSimpleCard(TXT.getString("skill_title"), speechText)
                .addRenderTemplateDirective(Layout.T6("mathematician", TXT.getString("skill_title")))
                // say this, if user does not respond
                .withReprompt(TXT.getString("advanced" + new Random().nextInt(3)))
                .build();
    }
}

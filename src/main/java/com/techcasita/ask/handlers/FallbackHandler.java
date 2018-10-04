/*
 * FallbackHandler, a very basic RequestHandler
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
 * AMAZON.FallbackIntent in case none of the other declared intents matches the user's request.
 * But not, is only currently available in en-US locale.
 * This handler will not be triggered except in that locale,
 * so it can be safely deployed for any locale.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
public class FallbackHandler implements RequestHandler {
    private static final ResourceBundle TXT = ResourceBundle.getBundle("text");

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.FallbackIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        final String speechText = TXT.getString("dnk" + new Random().nextInt(4)) + " " + TXT.getString("ask4help");
        return input.getResponseBuilder()
                // Speech Synthesis
                .withSpeech(speechText)
                // Display
                .withSimpleCard(TXT.getString("skill_title"), TXT.getString("ask4help"))
                .addRenderTemplateDirective(Layout.T6("mathematician", TXT.getString("skill_title")))
                // say this, if user does not respond
                .withReprompt(TXT.getString("advanced" + new Random().nextInt(3)))
                .build();
    }
}

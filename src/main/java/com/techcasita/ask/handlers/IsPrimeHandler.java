/*
 * IsPrimeHandler, a very basic RequestHandler
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
package com.techcasita.ask.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.model.interfaces.display.Template;
import com.techcasita.ask.Prime;
import com.techcasita.ask.templates.Layout;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * Answers questions about a number being a prime number.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
public class IsPrimeHandler implements RequestHandler {
    private static final Logger LOG = Logger.getLogger(IsPrimeHandler.class.getName());

    private static final ResourceBundle TXT = ResourceBundle.getBundle("text");
    private static final String NUMBER_SLOT = "number";

    @Override
    public boolean canHandle(final HandlerInput input) {
        return input.matches(intentName("IsPrime"));
    }

    @Override
    public Optional<Response> handle(final HandlerInput input) {

        final Request request = input.getRequestEnvelope().getRequest();
        final IntentRequest intentRequest = (IntentRequest) request;
        final Intent intent = intentRequest.getIntent();
        final Map<String, Slot> slots = intent.getSlots();
        final Slot numberSlot = slots.get(NUMBER_SLOT);

        // final Session session = input.getRequestEnvelope().getSession(); // carefull .. serialization into JSON
        // final Context cxt = input.getRequestEnvelope().getContext(); // client hardware device info
        // final DialogState state = intentRequest.getDialogState(); //  in multi turn dialog e.g. COMPLETED

        String speechText = TXT.getString("dnk" + new Random().nextInt(4));
        Template t = Layout.T6("red0", speechText);

        if (numberSlot != null && numberSlot.getValue() != null) {
            final long k = Long.valueOf(numberSlot.getValue());
            LOG.info("requested check: " + k);
            speechText = String.format(TXT.getString("no_prime"), k);
            if (k > 1) {
                final long p = Prime.isPrime(k);
                if (k == p) {
                    speechText = String.format(TXT.getString("yes_prime"), k);
                    t = Layout.T6("green" + new Random().nextInt(3), speechText);
                } else {
                    speechText = String.format(TXT.getString("no_prime_because"), k, p);
                    t = Layout.T6("red" + new Random().nextInt(3), speechText);
                }
            }
        }
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("isPrime ?", speechText)
                .addRenderTemplateDirective(t)
                .withShouldEndSession(false)
                .withReprompt(TXT.getString("fact" + new Random().nextInt(7)))
                .build();
    }
}

/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.techcasita.ask.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.techcasita.ask.templates.Layout;

import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import static com.amazon.ask.request.Predicates.requestType;

/**
 * Handler for launch requests, i.e. skill gets requested, but not explicitly asked if a number is prime.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
public class LaunchRequestHandler implements RequestHandler {
    private static final ResourceBundle TXT = ResourceBundle.getBundle("text");

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        return input.getResponseBuilder()
                // Speech Symthesis
                .withSpeech(TXT.getString("welcome"))
                // display
                .withSimpleCard(TXT.getString("skill_title"), TXT.getString("welcome"))
                .addRenderTemplateDirective(Layout.T6("mathematician", TXT.getString("skill_title")))
                // say this, if user does not respond.
                .withReprompt(TXT.getString("whatis" + new Random().nextInt(5)))
                .build();
    }
}

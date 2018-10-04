/*
 * PrimeMinisterSkill, a very basic Alexa Skill Implementation
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
package com.techcasita.ask;

import com.amazon.ask.Skill;
import com.amazon.ask.builder.CustomSkillBuilder;
import com.amazon.ask.dispatcher.request.interceptor.RequestInterceptor;
import com.amazon.ask.servlet.SkillServlet;
import com.techcasita.ask.handlers.*;

import javax.servlet.annotation.WebServlet;
import java.util.logging.Logger;

/**
 * The skill can be reached via https://host.domain:port/name_of_warFile/skill
 * In the static getSkill method, all handlers (for intents declared in the json) get instantiated.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
@WebServlet("/skill")
public class PrimeMinisterSkill extends SkillServlet {
    public static final String URI = "https://alpha.techcasita.com:8443/pm/";
    private static final Logger LOG = Logger.getLogger(IsPrimeHandler.class.getName());

    public PrimeMinisterSkill() {
        super(getSkill());
    }

    /**
     * @return Skill, built with a CustomSkillBuilder. However, at this point there is no difference to SkillBuilder.
     */
    private static Skill getSkill() {
        final RequestInterceptor ri = input -> LOG.info(input.getRequestEnvelope().getRequest().toString());

        return new CustomSkillBuilder()
                .addRequestInterceptor(ri)
                .addRequestHandlers(
                        new DefinitionHandler(),
                        new IsPrimeHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new CancelStopHandler(),
                        new HelpIntentHandler(),
                        new FallbackHandler())
                .withSkillId("amzn1.ask.skill.009dc3fe-dc57-47c9-ba5a-38f7e5f4eca8")
                .build();
    }
}

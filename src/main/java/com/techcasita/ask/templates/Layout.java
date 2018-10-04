/*
 * Layout, a bit strage how this is handled .. we will get CSS eventually ;-)
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
package com.techcasita.ask.templates;

import com.amazon.ask.model.interfaces.display.*;
import com.techcasita.ask.PrimeMinisterSkill;

import java.util.ResourceBundle;

/**
 * Shortcut for creating templates.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 */
public class Layout {

    private final static ResourceBundle TXT = ResourceBundle.getBundle("text");

    public static Template T6(final String img_id, final String content) {

        final ImageInstance ii = ImageInstance.builder().withUrl(PrimeMinisterSkill.URI + TXT.getString(img_id)).build();
        final RichText rt = RichText.builder().withText("<font size=\"7\">" + content + "</font>").build();
        final TextContent tc = TextContent.builder().withPrimaryText(rt).build();

        return BodyTemplate6.builder()
                .withBackgroundImage(Image.builder().addSourcesItem(ii).build())
                .withTextContent(tc)
                .build();
    }


    public static Template T7(final String img_id, final String title_id, final String content_id) {

        final ImageInstance ii = ImageInstance.builder().withUrl(PrimeMinisterSkill.URI + TXT.getString(img_id)).build();

        return BodyTemplate7.builder()
                .withBackgroundImage(Image.builder().addSourcesItem(ii).build())
                .withTitle(TXT.getString(title_id))
                .build();
    }
}

package hm.cs.swe.alexa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.StandardCard;

public final class AlexaUtils {
    protected static final String SESSION_CONVERSATION_FLAG = "conversation";

    public static final String SamplesHelpText = "Du kannst fragen was Software Engineering ist oder zum Beispiel was 1968 im Software Engineering passiert ist.";
    public static final String RepromptText = "Was möchtest Du wissen? Sage \"Hilfe\" für Vorschläge.";

    public static final String Welcome = "Willkommen beim Software Engineering Skill.";

    private AlexaUtils() {
    }


    public static Card newCard(String cardTitle, String cardText) {

        StandardCard card = new StandardCard();
        card.setTitle((cardTitle == null) ? "SWE" : cardTitle);
        card.setText(cardText);

        return card;
    }

    public static PlainTextOutputSpeech newSpeech(String speechText, boolean appendRepromptText) {

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(appendRepromptText ? speechText + "\n\n" + AlexaUtils.RepromptText : speechText);

        return speech;
    }

    public static SpeechletResponse newSpeechletResponse(Card card, PlainTextOutputSpeech speech, Session session, boolean shouldEndSession) {

        // Say it...
        if (AlexaUtils.inConversationMode(session) && !shouldEndSession) {
            PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
            repromptSpeech.setText(AlexaUtils.RepromptText);

            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(repromptSpeech);

            return SpeechletResponse.newAskResponse(speech, reprompt, card);
        } else {
            return SpeechletResponse.newTellResponse(speech, card);
        }
    }


    public static void setConversationMode(Session session, boolean conversationMode) {
        if (conversationMode)
            session.setAttribute(SESSION_CONVERSATION_FLAG, "true");
        else
            session.removeAttribute(SESSION_CONVERSATION_FLAG);
    }

    public static boolean inConversationMode(Session session) {
        return session.getAttribute(SESSION_CONVERSATION_FLAG) != null;
    }

    public static int randomInt(int min, int max) {
        Random r = new Random(System.currentTimeMillis());
        return r.nextInt((max - min) + 1) + min;
    }

}

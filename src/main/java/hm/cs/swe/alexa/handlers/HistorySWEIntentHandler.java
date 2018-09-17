package hm.cs.swe.alexa.handlers;

import hm.cs.swe.models.SWEFact;
import hm.cs.swe.services.SWEService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;

import hm.cs.swe.alexa.utils.AlexaUtils;

@Component
public class HistorySWEIntentHandler implements IntentHandler {
    protected Logger logger = LoggerFactory.getLogger(HistorySWEIntentHandler.class);

    @Autowired
    private SWEService sweService;


    @Override
    public SpeechletResponse handleIntent(Intent intent, IntentRequest request, Session session) {

        StringBuffer speechText = new StringBuffer();


        // Get the Talent slot
        Slot yearSlot = intent.getSlot("SWEJahr");
        String yearStr = yearSlot == null ? null : StringUtils.trimToNull(yearSlot.getValue());

        if (yearStr != null) {

            if (logger.isInfoEnabled())
                logger.info("Got year slot value = '" + yearStr + "'.");

            try {
                // parse the year as an Integer and lookup trivia
                int year = Integer.parseInt(yearStr);
                SWEFact trivia = sweService.getYearFact(year);

                speechText.append(trivia.getText());
            } catch (NumberFormatException e) {
                speechText.append("Ich habe leider die Jahreszahl nicht verstanden.  Sage zum Beispiel \"Was ist 1968 im Software Engineering passiert?\"");
            }

        } else {
            speechText.append("Ich habe die Jahreszahl nicht verstanden. Sage zum Beispiel \"Was ist 1968 im Software Engineering passiert?\"");
        }

        Card card = AlexaUtils.newCard("SWE Trivia", speechText.toString());
        PlainTextOutputSpeech speech = AlexaUtils.newSpeech(speechText.toString(), AlexaUtils.inConversationMode(session));

        return AlexaUtils.newSpeechletResponse(card, speech, session, false);
    }

}

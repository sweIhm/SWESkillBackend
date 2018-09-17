package hm.cs.swe.alexa.handlers;

import hm.cs.swe.models.SWEFact;
import hm.cs.swe.services.SWEService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;

import hm.cs.swe.alexa.utils.AlexaUtils;

@Component
public class DefineSWEIntent implements IntentHandler {
    protected Logger logger = LoggerFactory.getLogger(DefineSWEIntent.class);

    @Autowired
    private SWEService sweService;

    @Override
    public SpeechletResponse handleIntent(Intent intent, IntentRequest request, Session session) {

        // Get a Definition of Software Engineering
        SWEFact definition = sweService.getSWEDefintion();
        Card card = AlexaUtils.newCard("Definition Software Engineering", definition.getText());
        PlainTextOutputSpeech speech = AlexaUtils.newSpeech(definition.getText(), AlexaUtils.inConversationMode(session));

        return AlexaUtils.newSpeechletResponse(card, speech, session, false);
    }

}

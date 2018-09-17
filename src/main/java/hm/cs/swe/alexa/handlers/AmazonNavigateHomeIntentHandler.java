package hm.cs.swe.alexa.handlers;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import hm.cs.swe.alexa.utils.AlexaUtils;
import org.springframework.stereotype.Component;

@Component
public class AmazonNavigateHomeIntentHandler implements IntentHandler {

    @Override
    public SpeechletResponse handleIntent(Intent intent, IntentRequest request, Session session) {

        String speechText = "OK. Goodbye";

        Card card = AlexaUtils.newCard("See ya later...", speechText);
        PlainTextOutputSpeech speech = AlexaUtils.newSpeech(speechText, false);

        return AlexaUtils.newSpeechletResponse(card, speech, session, true);
    }

}

package hm.cs.swe.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import hm.cs.swe.models.SWEFact;

import java.util.ArrayList;
import java.util.Random;

@Service
public class SWEService {
    private static class SWEDefinitions {
        static final String DEFINITION_SOMMERVILLE = "Software Engineering ist eine Ingenieursdisziplin, die sich mit allen Aspekten der Software-Produktion von der Konzeption bis zum Betrieb und Wartung von Software beschäftigt.";
        static final String DEFINITION_BALZERT = "Software Engineering ist die zielorientierte Bereitstellung von Prinzipien, Methoden, Konzepten, Notationen und Werkzeugen für die arbeitsteilige, ingenieurmäßige Entwicklung und Anwendung von umfangreichen Software-Systemen.";
        static final String DEFINITION_IEEE = "Software Engineering ist ein systematischer Ansatz zur Entwicklung, Betrieb, Wartung und Stilllegung von Software.";
        static final String DEFINITION_BROWN_EARL_MCDERMID = "Software Engineering ist die Wissenschaft und Kunst, Programme, Dokumentationen und Arbeitsabläufe mit Ökonomie, Aktualität und Eleganz zu spezifizieren, zu entwerfen, zu implementieren und zu entwickeln.";

        /**
         * No instances allowed.
         */
        private SWEDefinitions() {
        }
    }

    public SWEFact getSWEDefintion() {
        // Get a Definition of Software Engineering
        String[] SWEDefArray = {SWEDefinitions.DEFINITION_SOMMERVILLE,
                SWEDefinitions.DEFINITION_BALZERT,
                SWEDefinitions.DEFINITION_IEEE,
                SWEDefinitions.DEFINITION_BROWN_EARL_MCDERMID};

        int random = new Random().nextInt(4);
        SWEFact definition = new SWEFact(SWEDefArray[random], 0, "DEFINITION");
        return definition;
    }

    public SWEFact getYearFact(int year) {
        HashMap<Integer, SWEFact> sweFacts = new HashMap<Integer, SWEFact>();
        sweFacts.put(1968, new SWEFact("Auf einer NATO-Tagung 1968 in Garmisch-Partenkirchen wurde der Begriff des Software Engineering geprägt.", 1968, "FACT"));
        sweFacts.put(1997, new SWEFact("Die UML wurde im November 1997 als Standard akzeptiert.", 1997, "FACT"));
        sweFacts.put(2001, new SWEFact("Das agile Manifest wurde 2001 verabschiedet.", 2001, "FACT"));
        sweFacts.put(2003, new SWEFact("Die UML 2.3 wurde im Mai 2010 veröffentlicht.", 2003, "FACT"));
        sweFacts.put(2007, new SWEFact("Die DevOps-Bewegung entstand 2007.", 2007, "FACT"));

        SWEFact fact = sweFacts.get(year);
        if (fact != null) {
            return fact;
        } else {
            return new SWEFact("Ich weiß leider noch nicht was im Software Engineering im Jahr "+ String.valueOf(year) + "passiert ist.", 0, "NO_FACT");
        }
    }
}
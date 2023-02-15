package baobab.libraries.pastell.variable;

import java.util.HashMap;

/**
 * Variables de test pour
 */
public class VariableApiTestModel {

    /**
     * La version attendue de l'Api.
     */
    public static final String version = "3.1.18";

    /**
     * Le modèle
     */
    public static final String nomFlux = "test-pastell-ws-consumer";

    /**
     * Le type de connecteur.
     */
    public static final String typeConnecteur = "signature";

    /**
     * Le jeu de données pour un pour document.
     */
    public static final HashMap<String, String> donnees  = new HashMap<String, String>() {{
        put("input_01", "Neque porro quisquam");
        put("input_02", "est qui dolorem");
        put("iparapheur_type", "Actes");
        put("iparapheur_sous_type", "Délibération");
    }};
}

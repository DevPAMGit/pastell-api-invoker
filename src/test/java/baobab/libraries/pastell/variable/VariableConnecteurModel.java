package baobab.libraries.pastell.variable;

import java.util.HashMap;

/**
 * Classe de variable pour les connecteurs.
 */
public class VariableConnecteurModel {
    /**
     * Le libellé du connecteur.
     */
    public static final String libelle = "i-parapheur-test";

    /**
     * L'identifiant du connecteur.
     */
    public static final String idConnecteur = "fakeIparapheur";

    /***
     * Les données du connecteur.
     */
    public  static HashMap<String, String> donnees  = new HashMap<String, String>() {{
        put("iparapheur_type", "Actes");
        put("iparapheur_envoi_status", "ok");
        put("iparapheur_retour", "Archive");
    }};
}

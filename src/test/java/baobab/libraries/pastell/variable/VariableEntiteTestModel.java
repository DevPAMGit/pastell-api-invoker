package baobab.libraries.pastell.variable;

import baobab.libraries.pastell.service.v2.entite.TypeEntite;

/**
 * Les variables de tests pour les entités.
 */
public class VariableEntiteTestModel {
    /**
     * Le nom de l'entité de test.
     */
    public static final String nomEntite = "Entité de test";

    /**
     * Le nom de l'entité fille de test.
     */
    public static final String nomEntiteFille = "Entité de test fille";

    /**
     * Le numéro SIREN des entités de tests.
     */
    public static final String siren = "000000000";

    /**
     * Le type de l'entité mère.
     */
    public static final TypeEntite typeMere = TypeEntite.COLLECTIVITE;

    /**
     * Le type de l'entité fille.
     */
    public static final TypeEntite typeFille = TypeEntite.CENTRE_DE_GESTION;

    /**
     * Valeur du centre de gestions des entités
     */
    public static final String centreGestion = "0";


}

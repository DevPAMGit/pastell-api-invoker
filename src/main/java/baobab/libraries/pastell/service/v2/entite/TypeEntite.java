package baobab.libraries.pastell.service.v2.entite;

/**
 * Enumération limitant le type pour une entité.
 */
public enum TypeEntite {
    /**
     * Définition d'une entité de type "collectivite".
     */
    COLLECTIVITE("collectivite"),

    /**
     * Définition d'une entité de type "Centre de gestion".
     */
    CENTRE_DE_GESTION("centre_de_gestion"),

    /**
     * Définition d'une entité de type "Service".
     */
    SERVICE("service");

    /**
     * Le nom du type de l'entité.
     */
    final String nom;

    /**
     * Initialise une nouvelle instance de l'énumération {@link TypeEntite}.
     * @param nom Le nom du type d'entité.
     */
    TypeEntite(String nom) {
        this.nom = nom;
    }
}

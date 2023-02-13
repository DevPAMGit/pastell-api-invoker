package baobab.libraries.pastell.service.v2.entite;

import baobab.libraries.pastell.service.v2.noyau.PastellMultipartFormDataService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant de créer une entité.
 */
public class CreerEntiteService extends PastellMultipartFormDataService {
    /**
     * La ressource de l'entité.
     */
    protected final static String BASE_RESSOURCE = "/api/v2/entite";

    /**
     * Initialise une nouvelle instance de la classe {@link CreerEntiteService}.
     *
     * @param hote              L'hôte du service.
     * @param login             Le login de connexion.
     * @param motDePasse        Le mot de passe du login.
     * @param denomination      La dénomination de l'entité.
     * @param type              Le type de l'entité.
     * @param siren             Le numéro siren de l'entité.
     * @param centreDeGestion   Le numéro de l'entité du centre de gestion.
     */
    public CreerEntiteService(@NotNull String hote, @NotNull String login, @NotNull String motDePasse,
                              @NotNull String entiteMere, @NotNull String denomination,@NotNull TypeEntite type,
                              @NotNull String siren, String centreDeGestion) {
        // Initialisation de la classe parente.
        super(hote, BASE_RESSOURCE, login, motDePasse);

        // Initialisation des données.
        this.addDonnee("denomination", denomination);
        this.addDonnee("entite_mere", entiteMere);
        this.addDonnee("type", type.nom);
        this.addDonnee("siren", siren);
        this.addDonnee("centre_de_gestion", (centreDeGestion == null || centreDeGestion.trim().isEmpty()) ?
                                                 "0" : centreDeGestion);
    }
}

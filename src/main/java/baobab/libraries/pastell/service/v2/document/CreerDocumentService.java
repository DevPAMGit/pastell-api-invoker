package baobab.libraries.pastell.service.v2.document;

import baobab.libraries.pastell.service.v2.noyau.PastellMultipartFormDataService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service pour la création d'un document.
 */
public class CreerDocumentService extends PastellMultipartFormDataService {
    /**
     * Le format de la ressource à consulter.
     */
    private final static String FORMAT_RESSOURCE = "/api/v2/entite/%s/document";

    /**
     * Initialise une nouvelle instance de la classe {@link CreerDocumentService}.
     *
     * @param url           L'URL de l'hôte.
     * @param login         Le login de connexion.
     * @param motDePasse    Le mot de passe du login de connexion.
     * @param idEntite      L'identifiant de l'entité.
     * @param type          Le type de document à régler.
     */
    public CreerDocumentService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                @NotNull String idEntite, @NotNull String type) {
        super(url, String.format(FORMAT_RESSOURCE, idEntite), login, motDePasse);
        this.addDonnee("type", type);
    }
}

package baobab.libraries.pastell.service.v2.document;

import baobab.libraries.pastell.service.v2.noyau.PastellPostService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant d'effectuer une action sur un document.
 */
public class ActionDocumentService extends PastellPostService {
    /**
     * Le format de la ressource à consulter.
     */
    private final static String FORMAT_RESSOURCE = "/api/v2/entite/%s/document/%s/action/%s";

    /**
     * Initialise une nouvelle instance de la classe {@link PastellPostService}.
     *
     * @param url           L'URL de connexion
     * @param login         Le login de connexion.
     * @param motDePasse    Le mot de passe lié au login de connexion.
     * @param idEntite      L'identifiant de l'entité.
     * @param idDocument    L'identifiant du document.
     * @param action        L'action à exécuter.
     */
    public ActionDocumentService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                 @NotNull String idEntite, @NotNull String  idDocument, @NotNull String action) {
        super(url, String.format(FORMAT_RESSOURCE, idEntite, idDocument, action), login, motDePasse);
    }
}

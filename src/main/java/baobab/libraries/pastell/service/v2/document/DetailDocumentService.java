package baobab.libraries.pastell.service.v2.document;

import baobab.libraries.pastell.service.v2.noyau.PastellGetService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant de consulter un document sur une entité.
 */
public class DetailDocumentService extends PastellGetService {
    /**
     * Le format de la ressource à consulter.
     */
    private final static String FORMAT_RESSOURCE = "/api/v2/entite/%s/document/%s";

    /**
     * Initialise une nouvelle instance de la classe {@link PastellGetService}.
     *
     * @param url           L'URL de l'hôte.
     * @param login         Le login de connexion à l'hôte.
     * @param motDePasse    Le mot de passe du login.
     * @param idEntite      L'identifiant de l'entité.
     * @param idDocument    L'identifiant du document.
     */
    public DetailDocumentService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                 @NotNull String idEntite, @NotNull String idDocument) {
        super(url, String.format(FORMAT_RESSOURCE, idEntite, idDocument), login, motDePasse);
    }
}

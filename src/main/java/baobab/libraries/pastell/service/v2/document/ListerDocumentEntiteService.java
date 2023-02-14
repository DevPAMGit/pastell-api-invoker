package baobab.libraries.pastell.service.v2.document;

import baobab.libraries.pastell.service.v2.noyau.PastellGetService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service permettant de lister la liste des documents sur une entité.
 */
public class ListerDocumentEntiteService extends PastellGetService {
    /**
     * Le format de la ressource à consulter.
     */
    private final static String FORMAT_RESSOURCE = "/api/v2/entite/%s/document";
    /**
     * Initialise une nouvelle instance de la classe {@link PastellGetService}.
     *
     * @param url        L'URL de l'hôte.
     * @param login      Le login de connexion à l'hôte.
     * @param motDePasse Le mot de passe du login.
     * @param idEntite   L'identifiant de l'entité.
     */
    public ListerDocumentEntiteService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                       @NotNull String idEntite) {
        super(url, String.format(FORMAT_RESSOURCE, idEntite), login, motDePasse);
    }
}

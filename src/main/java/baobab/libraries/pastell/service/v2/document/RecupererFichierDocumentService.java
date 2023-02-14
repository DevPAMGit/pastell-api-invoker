package baobab.libraries.pastell.service.v2.document;

import baobab.libraries.pastell.service.v2.noyau.PastellGetService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant de récupérer un document d'un document Pastell.
 */
public class RecupererFichierDocumentService extends PastellGetService {
    /**
     * Le format du service à consulter.
     */
    private final static String FORMAT_RESSOURCE = "/api/v2/entite/%s/document/%s/file/%s";

    /**
     * Initialise une nouvelle instance de la classe {@link PastellGetService}.
     *
     * @param url           L'URL de l'hôte.
     * @param login         Le login de connexion à l'hôte.
     * @param motDePasse    Le mot de passe du login.
     * @param idEntite      L'identifiant de l'entité.
     * @param idDocument    L'identifiant du document.
     * @param idParametre   L'identifiant du fichier sur le formulaire Pastell.
     */
    public RecupererFichierDocumentService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                           @NotNull String idEntite, @NotNull String idDocument,
                                           @NotNull String idParametre) {
        super(url, String.format(FORMAT_RESSOURCE, idEntite, idDocument, idParametre), login, motDePasse);
    }
}

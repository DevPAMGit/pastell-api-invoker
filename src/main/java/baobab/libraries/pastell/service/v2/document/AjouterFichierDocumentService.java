package baobab.libraries.pastell.service.v2.document;

import baobab.libraries.pastell.service.v2.noyau.PastellMultipartFormDataService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant d'ajouter un fichier à un document.
 */
public class AjouterFichierDocumentService extends PastellMultipartFormDataService {
    /**
     * Le format de la ressource à consulter.
     */
    private final static String FORMAT_RESSOURCE = "/api/v2/entite/%s/document/%s/file/%s";

    /**
     * Initialise une nouvelle instance de la classe {@link AjouterFichierDocumentService}.
     *
     * @param url           L'URL de l'hôte.
     * @param login         Le login de connexion.
     * @param motDePasse    Le mot de passe du login de connexion.
     * @param idEntite      L'identifiant de l'entité.
     * @param idDocument    L'identifiant du document.
     * @param idParametre  L'identifiant du paramètre du fichier.
     * @param nomFichier    Le nom du fichier.
     * @param contenu       Le contenu du fichier.
     */
    public AjouterFichierDocumentService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                         @NotNull String idEntite, @NotNull String idDocument,
                                         @NotNull String idParametre, @NotNull String nomFichier,
                                         @NotNull byte[] contenu) {
        super(url, String.format(FORMAT_RESSOURCE, idEntite, idDocument, idParametre), login, motDePasse);
        this.addDonnee("file_name", nomFichier);
        this.addDonnee("file_content", contenu);
    }
}

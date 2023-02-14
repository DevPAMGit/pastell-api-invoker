package baobab.libraries.pastell.service.v2.document;

import baobab.libraries.pastell.service.v2.noyau.PastellPatchService;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Classe de service Pastell permettant de modifier un document.
 */
public class ModifierDocumentService extends PastellPatchService {
    /**
     * Le format de la ressource à consulter.
     */
    private final static String FORMAT_RESSOURCE = "/api/v2/entite/%s/document/%s";

    /**
     * Initialise une nouvelle instance de la classe {@link ModifierDocumentService}.
     *
     * @param url           L'URL de connexion
     * @param login         Le login de connexion.
     * @param motDePasse    Le mot de passe lié au login de connexion.
     * @param idEntite      L'identifiant de l'entité.
     * @param idDocument    L'identifiant du document.
     * @param donnees       Les données à modifier sur le document.
     */
    public ModifierDocumentService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                   @NotNull String idEntite, @NotNull String idDocument,
                                   HashMap<String,String> donnees) {
        super(url, String.format(FORMAT_RESSOURCE, idEntite, idDocument), login, motDePasse);
        if(donnees != null && donnees.size() > 0)
            for(String cle : donnees.keySet())
                this.addDonnee(cle, donnees.get(cle));
    }
}

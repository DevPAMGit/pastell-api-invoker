package baobab.libraries.pastell.service.v2.connecteur;

import baobab.libraries.pastell.service.v2.noyau.PastellPatchService;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Classe de service Pastell permettant de modifier un connecteur.
 */
public class ModifierConnecteurService extends PastellPatchService {
    /**
     * La ressource à consulter sur l'hôte.
     */
    protected final static String BASE_RESSOURCE = "/api/v2/entite/%s/connecteur/%s/content";

    /**
     * Initialise une nouvelle instance de la classe {@link ModifierConnecteurService}.
     *
     * @param url           L'URL de connexion
     * @param login         L'URL de connexion
     * @param login         Le login de connexion.
     * @param motDePasse    Le mot de passe lié au login de connexion.
     * @param idEntite      L'entité sur laquelle créé le on modifie le connecteur.
     * @param idConnecteur  L'identifiant du connecteur à modifier sur l'entité.
     * @param donnees       Les données à modifier sur le connecteur.
     */
    public ModifierConnecteurService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                     @NotNull String idEntite, @NotNull String idConnecteur,
                                     HashMap<String, String> donnees) {
        super(url, String.format(BASE_RESSOURCE, idEntite, idConnecteur), login, motDePasse);
        if(donnees != null)
            for (String cle: donnees.keySet())
                this.addDonnee(cle, donnees.get(cle));
    }
}

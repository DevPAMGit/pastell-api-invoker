package baobab.libraries.pastell.service.v2.connecteur;

import baobab.libraries.pastell.service.v2.noyau.PastellMultipartFormDataService;
import baobab.libraries.pastell.service.v2.noyau.PastellPatchService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service permettant de créer un connecteur sur une entité.
 */
public class CreerConnecteurService extends PastellMultipartFormDataService {
    /**
     *
     */
    private final static String FORMAT_RESSOURCE = "/api/v2/entite/%s/connecteur";

    /**
     * Initialise une nouvelle instance de la classe {@link PastellPatchService}.
     *
     * @param url           L'URL de connexion
     * @param login         Le login de connexion
     * @param motDePasse    Le mot de passe du login.
     */
    public CreerConnecteurService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                  @NotNull String idEntite, @NotNull String libelleConnecteur,
                                  @NotNull String idConnecteur) {
        super(url, String.format(FORMAT_RESSOURCE, idEntite) , login, motDePasse);
        this.addDonnee("libelle", libelleConnecteur);
        this.addDonnee("id_connecteur", idConnecteur);
    }
}

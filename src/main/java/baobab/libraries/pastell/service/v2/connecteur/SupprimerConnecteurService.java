package baobab.libraries.pastell.service.v2.connecteur;

import baobab.libraries.pastell.service.v2.noyau.PastellDeleteService;
import org.jetbrains.annotations.NotNull;

/**
 * Méthode permettant de supprimer un connecteur.
 */
public class SupprimerConnecteurService extends PastellDeleteService {
    /**
     * Format de la ressource à consulter.
     */
    public final static String FORMAT_RESSOURCE = "/api/v2/entite/%s/connecteur/%s";

    /**
     * Initialise une nouvelle instance de classe {@link PastellDeleteService}.
     *
     * @param url           L'hôte du service.
     * @param login         Le login de connexion.
     * @param motDePasse    Le mot de passe du login.
     * @param idEntite      L'identifiant de l'entité ou le connecteur a été créée.
     * @param idConnecteur  L'identifiant du connecteur à supprimer.
     */
    public SupprimerConnecteurService(@NotNull String url, @NotNull String ressource, @NotNull String login,
                                      @NotNull String motDePasse, @NotNull String idEntite,
                                      @NotNull String idConnecteur) {
        super(url, String.format(FORMAT_RESSOURCE, idEntite, idConnecteur), login, motDePasse);
    }
}

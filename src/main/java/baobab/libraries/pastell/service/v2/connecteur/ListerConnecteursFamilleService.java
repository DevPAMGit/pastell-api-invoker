package baobab.libraries.pastell.service.v2.connecteur;

import baobab.libraries.pastell.service.v2.noyau.PastellGetService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant de lister les connecteurs disponibles d'une famille.
 */
public class ListerConnecteursFamilleService extends PastellGetService {
    /**
     * La base de la ressource à consulter.
     */
    protected final static String BASE_RESSOURCE = "/api/v2/familleConnecteur/%s";

    /**
     * Initialise une nouvelle instance de la classe {@link PastellGetService}.
     *
     * @param url        L'URL de l'hôte.
     * @param login      Le login de connexion à l'hôte.
     * @param motDePasse Le mot de passe du login.
     */
    public ListerConnecteursFamilleService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                            @NotNull String familleConnecteur) {
        super(url, String.format(BASE_RESSOURCE, familleConnecteur), login, motDePasse);
    }
}

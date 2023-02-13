package baobab.libraries.pastell.service.v2.connecteur;

import baobab.libraries.pastell.service.v2.noyau.PastellGetService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant de lister les familles de connecteur disponible sur l'API.
 */
public class ListerFamillesConnecteursService extends PastellGetService {
    /**
     * La ressource à consulter.
     */
    protected final static String RESSOURCE = "/api/v2/familleConnecteur";

    /**
     * Initialise une nouvelle instance de la classe {@link PastellGetService}.
     *
     * @param url        L'URL de l'hôte.
     * @param login      Le login de connexion à l'hôte.
     * @param motDePasse Le mot de passe du login.
     */
    public ListerFamillesConnecteursService(@NotNull String url, @NotNull String login, @NotNull String motDePasse) {
        super(url, RESSOURCE, login, motDePasse);
    }
}

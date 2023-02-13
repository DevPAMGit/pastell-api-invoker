package baobab.libraries.pastell.service.v2.entite;

import baobab.libraries.pastell.service.v2.noyau.PastellGetService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant de consulter le détail d'une entité.
 */
public class ConsulterEntiteService extends PastellGetService {
    /**
     * La base de la ressource à consulter.
     */
    protected final static String BASE_RESSOURCE = "/api/v2/entite/%s";

    /**
     * Initialise une nouvelle instance de la classe {@link PastellGetService}.
     *
     * @param url        L'URL de l'hôte.
     * @param login      Le login de connexion à l'hôte.
     * @param motDePasse Le mot de passe du login.
     * @param idEntite   L'identifiant de l'entité à consulter.
     */
    public ConsulterEntiteService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                  @NotNull String idEntite) {
        super(url, String.format(BASE_RESSOURCE, idEntite), login, motDePasse);
    }
}

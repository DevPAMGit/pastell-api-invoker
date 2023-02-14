package baobab.libraries.pastell.service.v2.flux;

import baobab.libraries.pastell.service.v2.noyau.PastellGetService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant de lister les flux.
 */
public class ListerFluxService extends PastellGetService {
    /** La ressource à consulter sur l'hôte. */
    private final static String RESSOURCE = "/api/v2/flux";

    /**
     * Initialise une nouvelle instance de la classe {@link ListerFluxService}.
     *
     * @param url        L'URL de l'hôte.
     * @param login      Le login de connexion à l'hôte.
     * @param motDePasse Le mot de passe du login.
     */
    public ListerFluxService(@NotNull String url, @NotNull String login,
                             @NotNull String motDePasse) {
        super(url, RESSOURCE, login, motDePasse);
    }
}

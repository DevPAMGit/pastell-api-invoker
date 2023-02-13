package baobab.libraries.pastell.service.v2.version;

import baobab.libraries.pastell.service.v2.noyau.PastellGetService;
import org.jetbrains.annotations.NotNull;

/**
 * Service permettant de récupérer la version de l'API.
 */
public class GetVersion extends PastellGetService {
    /**
     * La resource à consulter sur l'hôte.
     */
    private static final String RESSOURCE = "/api/v2/version";

    /**
     * Initialise une nouvelle instance de la classe {@link GetVersion}.
     * @param url           L'URL de l'hôte.
     * @param login         Le login de connexion.
     * @param motDePasse    Le mot de passe du login.
     */
    public GetVersion(@NotNull String url, @NotNull String login, @NotNull String motDePasse) {
        super(url, RESSOURCE, login, motDePasse);
    }

    @Override
    public String getRessource() {
        return RESSOURCE;
    }
}

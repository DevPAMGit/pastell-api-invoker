package baobab.libraries.pastell.service.v2.flux;

import baobab.libraries.pastell.service.v2.noyau.PastellGetService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant récupérer le détail d'un service.
 */
public class DetailFluxService extends PastellGetService {
    /**
     * Le format de la ressource à consulter.
     */
    private final static String FORMAT_RESSOURCE = "/api/v2/flux/%s";


    /**
     * Initialise une nouvelle instance de la classe {@link PastellGetService}.
     *
     * @param url        L'URL de l'hôte.
     * @param login      Le login de connexion à l'hôte.
     * @param motDePasse Le mot de passe du login.
     * @param idFlux     L'identifiant du flux.
     */
    public DetailFluxService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                             @NotNull String idFlux) {
        super(url, String.format(FORMAT_RESSOURCE, idFlux), login, motDePasse);
    }
}

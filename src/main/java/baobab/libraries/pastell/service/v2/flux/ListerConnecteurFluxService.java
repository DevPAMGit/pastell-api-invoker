package baobab.libraries.pastell.service.v2.flux;

import baobab.libraries.pastell.service.v2.noyau.PastellGetService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant de lister les connecteurs d'un type lié à un flux.
 */
public class ListerConnecteurFluxService extends PastellGetService {
    /**
     * Format du service à consulter.
     */
    private final static String FORMAT_RESSOURCE = "/api/v2/entite/%s/flux/?flux=%s&type=%s";

    /**
     * Initialise une nouvelle instance de la classe {@link PastellGetService}.
     *
     * @param url               L'URL de l'hôte.
     * @param login             Le login de connexion à l'hôte.
     * @param motDePasse        Le mot de passe du login.
     * @param idFlux            L'identifiant du flux.
     * @param typeConnecteur    Le type du connecteur
     */
    public ListerConnecteurFluxService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                       @NotNull String idEntite, @NotNull String idFlux, @NotNull String typeConnecteur)
    {
        super(url, String.format(FORMAT_RESSOURCE, idEntite, idFlux, typeConnecteur), login, motDePasse);
    }
}

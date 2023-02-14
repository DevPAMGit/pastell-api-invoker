package baobab.libraries.pastell.service.v2.flux;


import baobab.libraries.pastell.service.v2.noyau.PastellPostService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant d'associer un connecteur à un flux.
 */
public class AssocierConnecteurFluxService extends PastellPostService {
    /**
     * Le format de la ressource à consulter.
     */
    private static final String FORMAT_RESSOURCE = "/api/v2/entite/%s/flux/%s/connecteur/%s?type=%s";

    /**
     * Initialise une nouvelle instance de la classe {@link AssocierConnecteurFluxService}.
     *
     * @param url               L'URL de l'hôte.
     * @param login             Le login de connexion.
     * @param motDePasse        Le mot de passe du login de connexion.
     * @param idEntite          L'identifiant de l'entité.
     * @param idFlux            L'identifiant du flux.
     * @param idConnecteur      L'identifiant du connecteur.
     * @param typeConnecteur    Le type du connecteur.
     */
    public AssocierConnecteurFluxService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                         @NotNull String idEntite, @NotNull String idFlux, @NotNull String idConnecteur,
                                         @NotNull String typeConnecteur) {
        super(url, String.format(FORMAT_RESSOURCE, idEntite, idFlux, idConnecteur, typeConnecteur), login, motDePasse);
    }
}

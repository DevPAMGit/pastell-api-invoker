package baobab.libraries.pastell.service.v2.flux;

import baobab.libraries.pastell.service.v2.noyau.PastellDeleteService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant de dissocier un connecteur d'un flux sur une entité.
 */
public class DissocierConnecteurFluxService extends PastellDeleteService {
    /**
     * Le format de la ressource à consulter.
     */
    private final static String FORMAT_RESSOURCE = "/api/v2/entite/%s/flux?id_fe=%s";

    /**
     * Initialise une nouvelle instance de classe {@link PastellDeleteService}.
     *
     * @param url                   L'hôte du service.
     * @param login                 Le login de connexion.
     * @param motDePasse            Le mot de passe du login.
     * @param idEntite              L'identifiant de l'entité.
     * @param idAssociationFlux     L'identifiant de l'association du flux.
     */
    public DissocierConnecteurFluxService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                          @NotNull String idEntite, @NotNull String idAssociationFlux) {
        super(url, String.format(FORMAT_RESSOURCE, idEntite, idAssociationFlux), login, motDePasse);
    }
}

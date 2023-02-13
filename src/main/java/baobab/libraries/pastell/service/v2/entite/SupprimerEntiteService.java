package baobab.libraries.pastell.service.v2.entite;

import baobab.libraries.pastell.service.v2.noyau.PastellDeleteService;
import org.jetbrains.annotations.NotNull;

/**
 * Service Pastell permettant de supprimer une entité.
 */
public class SupprimerEntiteService extends PastellDeleteService {
    /**
     * La ressource de l'entité.
     */
    protected final static String BASE_RESSOURCE = "/api/v2/entite/%s";

    /**
     * Initialise une nouvelle instance de classe {@link PastellDeleteService}.
     *
     * @param url        L'hôte du service.
     * @param login      Le login de connexion.
     * @param motDePasse Le mot de passe du login.
     * @param idEntite   L'identifiant de l'entité à supprimer.
     */
    public SupprimerEntiteService(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                  @NotNull String idEntite) {
        super(url, String.format(BASE_RESSOURCE, idEntite) , login, motDePasse);
    }
}

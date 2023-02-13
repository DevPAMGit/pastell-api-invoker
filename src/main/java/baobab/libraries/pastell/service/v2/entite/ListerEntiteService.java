package baobab.libraries.pastell.service.v2.entite;

import baobab.libraries.pastell.service.v2.noyau.PastellGetService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant de lister la liste des entités.
 */
public class ListerEntiteService extends PastellGetService {
    /**
     * La ressource de l'entité.
     */
    protected final static String BASE_RESSOURCE = "/api/v2/entite";

    /**
     * Initialise une nouvelle instance de la classe {@link PastellGetService}.
     *
     * @param url        L'URL de l'hôte.
     * @param login      Le login de connexion à l'hôte.
     * @param motDePasse Le mot de passe du login.
     */
    public ListerEntiteService(@NotNull String url, @NotNull String login, @NotNull String motDePasse) {
        super(url, BASE_RESSOURCE, login, motDePasse);
    }
}

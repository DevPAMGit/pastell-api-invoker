package baobab.libraries.pastell.service.v2.noyau;

import baobab.libraries.requete.authentifie.base64.RequeteHTTPDeleteAuthBasic;
import baobab.libraries.requete.noyau.RequeteHTTPException;
import baobab.libraries.service.IService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant d'effectuer des requêtes de méthode DELETE.
 */
public class PastellDeleteService extends RequeteHTTPDeleteAuthBasic implements IService {
    /**
     * La ressource sollicitée sur l'hôte.
     */
    private final String ressource;

    /**
     * Initialise une nouvelle instance de classe {@link PastellDeleteService}.
     * @param url           L'hôte du service.
     * @param ressource     La ressource à consulter sur l'hôte.
     * @param login         Le login de connexion.
     * @param motDePasse    Le mot de passe du login.
     */
    public PastellDeleteService(@NotNull String url, @NotNull String ressource, @NotNull String login,
                                @NotNull String motDePasse) {
        super(String.format("%s%s", url, ressource) , login, motDePasse);
        this.ressource = ressource;
    }

    @Override
    public byte[] getCorps() throws RequeteHTTPException {
        return null;
    }

    @Override
    public String getRessource() {
        return this.ressource;
    }
}

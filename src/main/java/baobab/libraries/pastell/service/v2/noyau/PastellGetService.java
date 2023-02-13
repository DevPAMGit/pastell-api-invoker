package baobab.libraries.pastell.service.v2.noyau;

import baobab.libraries.requete.authentifie.base64.RequeteHTTPGetAuthBasic;
import baobab.libraries.requete.noyau.RequeteHTTPException;
import baobab.libraries.service.IService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Classe de base pour un appel service Pastell.
 */
public abstract class PastellGetService extends RequeteHTTPGetAuthBasic implements IService {
    /**
     * La ressource sollicitée sur l'hôte.
     */
    private final String ressource;

    /**
     * Initialise une nouvelle instance de la classe {@link PastellGetService}.
     * @param url           L'URL de l'hôte.
     * @param ressource     La ressource sollicitée sur l'hôte.
     * @param login         Le login de connexion à l'hôte.
     * @param motDePasse    Le mot de passe du login.
     */
    public PastellGetService(@NotNull String url, @NotNull String ressource,
                             @NotNull String login, @NotNull String motDePasse) {
        super(String.format("%s%s", url, ressource), login, motDePasse);
        this.ressource = ressource;
    }

    @Override
    public String appeler() throws IOException, RequeteHTTPException, InterruptedException {
        String resultat = super.appeler();
        int statut = this.getStatut();

        if(statut == 400)
            throw new RequeteHTTPException("Votre demande n'a pas été comprise par Pastell.");
        else if(statut == 401)
            throw new RequeteHTTPException("Veuillez vous authentifier pour utiliser Pastell.");
        else if(statut == 404)
            throw new RequeteHTTPException(String.format("La resource '%s' n'a pas été trouvé sur le serveur.",
                                           this.getRessource()));
        else if(statut == 405)
            throw new RequeteHTTPException(String.format("La méthode '%s' n'est pas disponible pour la ressources '%s'",
                                          this.getMethode(), this.getRessource()));
        else if(statut == 409)
            throw new RequeteHTTPException("La modification ou création crée un conflit.");
        else if(statut == 500)
            throw new RequeteHTTPException("Une erreur est survenue lors de l'exécution de votre action sur l'API.");

        return resultat;
    }

    @Override
    public String getRessource() {
        return this.ressource;
    }
}

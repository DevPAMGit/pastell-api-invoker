package baobab.libraries.pastell.service.v2.noyau;

import baobab.libraries.requete.authentifie.base64.RequeteHTTPPostAuthBasic;
import baobab.libraries.requete.noyau.RequeteHTTPException;
import baobab.libraries.service.IService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe de service Pastell permettant d'effectuer une requête HTTP de méthode PATCH.
 */
public class PastellPostService extends RequeteHTTPPostAuthBasic implements IService {
    /**
     * La ressource sollicitée sur l'hôte.
     */
    private final String ressource;

    /**
     * Initialise une nouvelle instance de la classe {@link PastellPostService}.
     * @param url           L'URL de connexion
     * @param ressource     La ressource à consulter sur l'hôte distant.
     * @param login         Le login de connexion.
     * @param motDePasse    Le mot de passe lié au login de connexion.
     */
    public PastellPostService(@NotNull String url, @NotNull String ressource, @NotNull String login,
                              @NotNull String motDePasse) {
        super(String.format("%s%s", url, ressource), login, motDePasse);
        this.ressource = ressource;
    }

    @Override
    public byte[] getCorps() throws RequeteHTTPException {
        StringBuilder resultat = new StringBuilder();
        boolean estPremier = true;

        for (String cle: this.donnees.keySet()) {
            if(estPremier) estPremier = !estPremier;
            else resultat.append("&");
            resultat.append(String.format("%s=%s", cle, (String)this.donnees.get(cle)));
        }

        return resultat.toString().getBytes();
    }


    @Override
    public String getRessource() {
        return this.ressource;
    }
}

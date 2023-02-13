package baobab.libraries.pastell;

import baobab.libraries.pastell.service.v2.connecteur.CreerConnecteurService;
import baobab.libraries.pastell.service.v2.connecteur.ListerConnecteursFamilleService;
import baobab.libraries.pastell.service.v2.connecteur.ListerFamillesConnecteursService;
import baobab.libraries.pastell.service.v2.connecteur.ModifierConnecteurService;
import baobab.libraries.pastell.service.v2.entite.*;
import baobab.libraries.pastell.service.v2.version.GetVersion;
import baobab.libraries.requete.noyau.RequeteHTTPException;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Classe permettant d'interagir avec l'API Pastell.
 */
public class PastellApiV2 {
    /**
     * L'hôte de l'API.
     */
    private final String hote;

    /**
     * Le login de connexion à l'API.
     */
    private final String login;

    /**
     * Le mot de passe du login.
     */
    private final String motDePasse;

    /**
     * Initialise une nouvelle instance de la classe {@link PastellApiV2}.
     * @param hote          L'hôte de l'API.
     * @param login         Le login de connexion à l'API.
     * @param motDePasse    Le mot de passe du login.
     */
    public PastellApiV2(@NotNull String hote, @NotNull String login, @NotNull String motDePasse) {
        this.hote = hote;
        this.login = login;
        this.motDePasse = motDePasse;
    }

    /**
     * Récupère la version de l'API.
     * @return Un JSON contenant la version de l'API.
     */
    public JSONObject getVersion() throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(new GetVersion(this.hote, this.login, this.motDePasse).appeler());
    }

    /**
     * Crée une entité au sein de l'api.
     * @param idEntiteMere              L'identifiant de l'entité mère.
     * @param denomination              La dénomination de l'entité (son nom).
     * @param type                      Le type de l'entité.
     * @param siren                     Le numéro siren de l'entité.
     * @param centreGestion             Le numéro de l'entité de centre de gestion.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws InterruptedException     Si l'opération est interrompue.
     * @throws RequeteHTTPException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject creerEntite(String idEntiteMere, @NotNull String denomination, @NotNull TypeEntite type,
                                  @NotNull String siren, String centreGestion)
            throws IOException, RequeteHTTPException, InterruptedException {

        if(idEntiteMere == null || idEntiteMere.trim().isEmpty())
            return new JSONObject(new CreerEntiteService(this.hote, this.login, this.motDePasse, "0",
                                  denomination, type, siren, centreGestion).appeler());

        return new JSONObject(new CreerEntiteService(this.hote, this.login, this.motDePasse, idEntiteMere, denomination,
                              type, siren, centreGestion).appeler());
    }

    /**
     * Supprime une entité de l'API Pastell.
     * @param idEntite                  L'identifiant de l'entité à supprimer.
     * @return                          Le résultat de l'opération en instance {@link JSONObject}.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject supprimerEntite(String idEntite) throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(new SupprimerEntiteService(this.hote, this.login, this.motDePasse, idEntite).appeler());
    }


    /**
     * Permet de récupérer la liste complète des entités de l'API Pastell.
     * @return                          Un tableau d'objets JSON.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws InterruptedException     Si l'opération est interrompue.
     * @throws RequeteHTTPException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONArray getEntites() throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONArray(new ListerEntiteService(this.hote, this.login, this.motDePasse).appeler());
    }

    /**
     * Permet de consulter en détail une entité.
     * @param idEntite                  L'identifiant de l'entité à consulter.
     * @return                          Le résultat de l'opération en instance {@link JSONObject}.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject getEntite(@NotNull String idEntite)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(new ConsulterEntiteService(this.hote, this.login, this.motDePasse, idEntite).appeler());
    }

    /**
     * Permet de consulter la liste des familles de connecteurs disponibles sur l'API.
     * @return                          La liste des familles de connecteurs disponibles sur l'API.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONArray getFamillesConnecteur() throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONArray(new ListerFamillesConnecteursService(this.hote, this.login, this.motDePasse).appeler());
    }

    /**
     * Permet de consulter la liste des connecteurs disponible pour une famille dans l'API.
     * @param familleConnecteur         Le nom de la famille de connecteurs.
     * @return                          La liste des connecteurs disponible pour la famille donnée.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONArray getConnecteursFamille(String familleConnecteur)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONArray(new ListerConnecteursFamilleService(this.hote, this.login, this.motDePasse,
                familleConnecteur).appeler());
    }

    /**
     * Permet de créer un connecteur sur une entité.
     * @param idEntite                  L'identifiant de l'entité.
     * @param libelle                   Le libellé du connecteur sur l'entité.
     * @param idConnecteur              L'identifiant du type de connecteur à créer.
     * @return                          Le .
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject creerConnecteur(@NotNull String idEntite, @NotNull String libelle, @NotNull String idConnecteur)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(new CreerConnecteurService(this.hote, this.login, this.motDePasse, idEntite,
                libelle, idConnecteur).appeler());
    }

    /**
     *
     * @param idEntite      L'entité sur laquelle créé le on modifie le connecteur.
     * @param idConnecteur  L'identifiant du connecteur à modifier sur l'entité.
     * @param donnees       Les données à modifier sur le connecteur.
     * @return
     * @throws IOException
     * @throws RequeteHTTPException
     * @throws InterruptedException
     */
    public JSONObject modifierConnecteur(@NotNull String idEntite, @NotNull String idConnecteur,
                                         HashMap<String, String> donnees) throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(new ModifierConnecteurService(this.hote, this.login, this.motDePasse, idEntite,
                idConnecteur, donnees).appeler());
    }
}

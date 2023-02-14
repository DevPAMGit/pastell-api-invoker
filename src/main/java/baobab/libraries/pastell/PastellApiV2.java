package baobab.libraries.pastell;

import baobab.libraries.pastell.service.v2.connecteur.*;
import baobab.libraries.pastell.service.v2.document.*;
import baobab.libraries.pastell.service.v2.entite.*;
import baobab.libraries.pastell.service.v2.flux.*;
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
     * @param idEntite      L'entité sur laquelle on modifie le connecteur.
     * @param idConnecteur  L'identifiant du connecteur à modifier sur l'entité.
     * @param donnees       Les données à modifier sur le connecteur.
     * @return              Une instance de classe {@link JSONObject}.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject modifierConnecteur(@NotNull String idEntite, @NotNull String idConnecteur,
                                         HashMap<String, String> donnees) throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(new ModifierConnecteurService(this.hote, this.login, this.motDePasse, idEntite,
                idConnecteur, donnees).appeler());
    }

    /**
     * Méthode permettant supprimer un connecteur d'une entité.
     * @param idEntite                  L'identifiant de l'entité sur laquelle on souhaite supprimer le connecteur.
     * @param idConnecteur              L'identifiant du connecteur à supprimer.
     * @return                          Une instance de classe {@link JSONObject}.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject supprimerConnecteur(@NotNull String idEntite, @NotNull String idConnecteur)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(
                new SupprimerConnecteurService(this.hote, this.login, this.motDePasse, idEntite, idConnecteur).appeler()
        );
    }

    // ************************************** SERVICES DE GESTION DES FLUX ****************************************** //

    /**
     * Méthode permettant d'associer un connecteur à un flux sur une entité.
     * @param idEntite                  L'identifiant de l'entité.
     * @param idFlux                    L'identifiant du flux.
     * @param idConnecteur              L'identifiant du connecteur.
     * @param typeConnecteur            Le type de connecteur.
     * @return                          Une instance de {@link JSONObject} contenant les informations de l'association.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject associerConnecteurFlux(@NotNull String idEntite, @NotNull String idFlux,
                                             @NotNull String idConnecteur, @NotNull String typeConnecteur)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(new AssocierConnecteurFluxService(this.hote, this.login, this.motDePasse, idEntite,
                                                                idFlux, idConnecteur, typeConnecteur).appeler());
    }

    /**
     * Méthode permettant de récupérer les informations concernant un flux.
     * @param idFlux                    L'identifiant du flux.
     * @return                          Une instance de {@link JSONObject} contenant les informations du flux.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject detailFlux(@NotNull String idFlux)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(new DetailFluxService(this.hote, this.login, this.motDePasse, idFlux).appeler());
    }

    /**
     * Méthode permettant de dissocier un connecteur d'un flux sur une entité.
     * @param idEntite                  L'identifiant de l'entité sur
     * @param idAssociationFlux         L'identifiant de association.
     * @return                          Une instance de {@link JSONObject} contenant les informations de suppression de
     *                                      l'association.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject dissocierConnecteurFlux(@NotNull String idEntite, @NotNull String idAssociationFlux)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(new DissocierConnecteurFluxService(this.hote, this.login, this.motDePasse, idEntite,
                                                                 idAssociationFlux).appeler());
    }

    /**
     * Méthode permettant de lister tous les flux de la plateforme.
     * @return                          Une instance de type {@link JSONObject} contenant tous les flux disponibles.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject listerFlux() throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(new ListerFluxService(this.hote, this.login, this.motDePasse).appeler());
    }

    /**
     * Méthode permettant de récupérer la liste des connecteurs associés à un flux sur une entité.
     * @param idEntite                  L'identifiant de l'entité.
     * @param idFlux                    L'identifiant du flux.
     * @param typeConnecteur            Le type du connecteur.
     * @return                          Une instance de type {@link JSONArray} contenant tous connecteurs liés à un 
     *                                      flux sur une entité.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONArray listerConnecteurAssociesFlux(@NotNull String idEntite, @NotNull String idFlux,
                                                  @NotNull String typeConnecteur)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONArray(new ListerConnecteurFluxService(this.hote, this.login, this.motDePasse, idEntite, idFlux,
                                                             typeConnecteur).appeler());
    }

    // ************************************ FIN - SERVICES DE GESTION DES FLUX ************************************* //

    // ************************************** SERVICES DE GESTION DES DOCUMENT ************************************* //

    /**
     * Méthode permettant d'effectuer une action sur un document.
     * @param idEntite                  Identifiant de l'entité.
     * @param idDocument                L'identifiant du document.
     * @param action                    L'action à executer sur le document.
     * @return                          Une instance de type {@link JSONObject} indiquant le résultat de l'action sur le
     *                                      document.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject actionDocument(@NotNull String idEntite, @NotNull String  idDocument, @NotNull String action)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(new ActionDocumentService(this.hote, this.login, this.motDePasse, idEntite,
                idDocument, action).appeler());
    }

    /**
     * Méthode permettant d'ajouter un fichier à un document.
     * @param idEntite                  L'identifiant de l'entité.
     * @param idDocument                L'identifiant du document.
     * @param idParametre               L'identifiant du paramètre.
     * @param nomFichier                Le nom du fichier.
     * @param contenu                   Le contenu du fichier.
     * @return                          Une instance de type {@link JSONObject} représentant contenant les informations
     *                                      du document.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject ajouterFichierDocument(@NotNull String idEntite, @NotNull String idDocument,
                                             @NotNull String idParametre, @NotNull String nomFichier,
                                             @NotNull byte[] contenu)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(new AjouterFichierDocumentService(this.hote, this.login, this.motDePasse,
                                                                idEntite, idDocument, idParametre, nomFichier,
                                                                contenu).appeler());
    }

    /**
     * Méthode permettant de créer un document sur une entité.
     * @param idEntite                  L'identifiant de l'entité.
     * @param type                      Le type de document à créer.
     * @return                          Une instance de type {@link JSONObject} représentant le détail du document.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject creerDocument(@NotNull String idEntite, @NotNull String type)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(
                new CreerDocumentService(this.hote, this.login, this.motDePasse, idEntite, type).appeler()
        );
    }

    /**
     * Méthode permettant de récupérer le détail d'un document sur une entité.
     * @param idEntite                  L'identifiant de l'entité.
     * @param idDocument                L'identifiant du document.
     * @return                          Une instance de type {@link JSONObject} représentant le détail du document.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject detailDocument(@NotNull String idEntite, @NotNull String idDocument)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(
                new DetailDocumentService(this.hote, this.login, this.motDePasse, idEntite, idDocument).appeler());
    }

    /**
     * Méthode permettant de lister les documents présents sur une entité.
     * @param idEntite                  L'identifiant de l'entité.
     * @return                          Une instance de type {@link JSONArray} représentant la liste des documents
     *                                      présents sur l'instance.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONArray listerDocuments(@NotNull String idEntite) throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONArray(
                new ListerDocumentEntiteService(this.hote, this.login, this.motDePasse, idEntite).appeler());
    }

    /**
     * Méthode permettant de modifier les propriétés d'un document.
     * @param idEntite                  L'identifiant de l'entité.
     * @param idDocument                L'identifiant du document.
     * @param donnees                   Les données à modifier sur le document.
     * @return                          Une instance de type {@link JSONObject} représentant le détail du document.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject modifierDocument(@NotNull String idEntite, @NotNull String idDocument,
                                       HashMap<String,String> donnees)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(
                new ModifierDocumentService(this.hote, this.login, this.motDePasse, idEntite, idDocument,
                                            donnees).appeler());
    }

    /**
     * Méthode permettant de récupérer un fichier lié à un document Pastell.
     * @param idEntite                  L'identifiant de l'entité.
     * @param idDocument                L'identifiant du document.
     * @param idParametre               L'identifiant du paramètre du fichier sur le document.
     * @return                          Une instance de type {@link JSONObject} représentant le détail du document.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException     Si l'opération est interrompue.
     * @throws InterruptedException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public JSONObject recupererFichierDocument(@NotNull String idEntite, @NotNull String idDocument,
                                               @NotNull String idParametre)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(new RecupererFichierDocumentService(this.hote, this.login, this.motDePasse,
                                                                 idEntite, idDocument, idParametre).appeler());
    }

    // ************************************ FIN - SERVICES DE GESTION DES DOCUMENT ********************************* //
}

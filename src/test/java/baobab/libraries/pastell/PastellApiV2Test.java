package baobab.libraries.pastell;

import baobab.libraries.pastell.variable.VariableApiTestModel;
import baobab.libraries.pastell.variable.VariableConnecteurModel;
import baobab.libraries.pastell.variable.VariableEntiteTestModel;
import baobab.libraries.pastell.variable.VariableIdentificationTestModel;
import baobab.libraries.requete.noyau.RequeteHTTPException;
import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

/**
 * Classe de tests unitaires pour l'instance {@link PastellApiV2}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PastellApiV2Test {

    /**
     * L'instance à tester.
     */
    private static PastellApiV2 api;

    /**
     * Le modèle de données pour les résultats des tests de l'api.
     */
    private static PastellApiTestModel modele;

    /**
     * Instancie de l'API à tester.
     */
    @BeforeAll
    public static void initialiser(){
        api = new PastellApiV2(VariableIdentificationTestModel.hote, VariableIdentificationTestModel.login,
                VariableIdentificationTestModel.mdp);
        modele = new PastellApiTestModel();
    }

    /**
     * Méthode permettant de tester la version de l'API Pastell.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws InterruptedException     Si l'opération est interrompue.
     * @throws RequeteHTTPException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    @Test
    @Order(1)
    @DisplayName("Consultation de la version de l'API.")
    public void testerVersion() throws IOException, RequeteHTTPException, InterruptedException {
        Assertions.assertEquals(VariableApiTestModel.version, api.getVersion().getString("version"));
    }

    /**
     * Méthode permettant de tester la création d'une entité.
     * @throws IOException              Si une exception d'entrée/sortie se produit.
     * @throws InterruptedException     Si l'opération est interrompue.
     * @throws RequeteHTTPException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    @Test
    @Order(2)
    @DisplayName("Création d'une entité.")
    public void testerCreerEntite() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject resultat = api.creerEntite(null, VariableEntiteTestModel.nomEntite,
                              VariableEntiteTestModel.typeMere, VariableEntiteTestModel.siren, null);
        Assertions.assertTrue(resultat.has("id_e"));
        modele.idEntite = resultat.getString("id_e");
    }

    @Test
    @Order(3)
    @DisplayName("Création d'un entité fille.")
    public void testerCreerEntiteFille() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject resultat = api.creerEntite(modele.idEntite, VariableEntiteTestModel.nomEntiteFille,
                              VariableEntiteTestModel.typeFille, VariableEntiteTestModel.siren, "0");

        Assertions.assertTrue(resultat.has("id_e"));
        modele.idEntiteFille = resultat.getString("id_e");
    }

    @Test
    @Order(4)
    @DisplayName("Consultation d'une entité.")
    public void testerConsultationEntite() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject entite = api.getEntite(modele.idEntite);
        Assertions.assertEquals(modele.idEntite, entite.getString("id_e"));
        Assertions.assertEquals(VariableEntiteTestModel.nomEntite, entite.getString("denomination"));
        Assertions.assertEquals(VariableEntiteTestModel.siren, entite.getString("siren"));
        Assertions.assertEquals(VariableEntiteTestModel.centreGestion, entite.getString("centre_de_gestion"));
    }

    @Test
    @Order(5)
    @DisplayName("Lister des entités disponible.")
    public void testerListageEntites() throws IOException, RequeteHTTPException, InterruptedException {
        boolean mereTrouvee = false;
        boolean filleTrouvee = false;

        JSONArray resultat = api.getEntites();
        int max = resultat.length(), index = 0;
        while((!mereTrouvee || !filleTrouvee) && index < max) {
            JSONObject objet = resultat.getJSONObject(index);
            String idEntite = objet.getString("id_e");
            if(idEntite.equals(modele.idEntite)) mereTrouvee = true;
            if(idEntite.equals(modele.idEntiteFille) ){
                String idEntiteMere = objet.getString("entite_mere");
                if(idEntiteMere.equals(modele.idEntite)) filleTrouvee = true;
            }

            index++;
        }
        Assertions.assertTrue(mereTrouvee);
        Assertions.assertTrue(filleTrouvee);
    }

    @Test
    @Order(6)
    @DisplayName("Suppression d'une entité.")
    public void testerSuppressionEntite() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject resultat = api.supprimerEntite(modele.idEntiteFille);
        Assertions.assertTrue(resultat.has("result"));
        Assertions.assertEquals(resultat.getString("result"), "ok");
    }

    @Order(7)
    @Disabled
    @ParameterizedTest
    @ValueSource(strings = { "Bordereau SEDA", "classification-cdg", "empty", "GED", "Glaneur", "mailsec",
                             "pdf-relance", "Purge", "SAE", "signature", "TdT", "transformation" })
    @DisplayName("Récupération des familles de connecteurs")
    public void testerListerFamillesConnecteur(String famille)
            throws IOException, RequeteHTTPException, InterruptedException {
        JSONArray familles = api.getFamillesConnecteur();
        int index = 0, max = familles.length();
        while (!familles.getString(index).equals(famille) && index < max) index++;
        Assertions.assertTrue(index < max);
    }

    @Order(8)
    @Disabled
    @ParameterizedTest
    @ValueSource(strings = { "fakeIparapheur", "fast-parapheur", "iParapheur", "libersign" })
    @DisplayName("Récupération des connecteurs de la famille signature")
    public void testerListerConnecteursFamille(String famille)
            throws IOException, RequeteHTTPException, InterruptedException {
        JSONArray familles = api.getConnecteursFamille("signature");
        int index = 0, max = familles.length();
        while (!familles.getString(index).equals(famille) && index < max) index++;
        Assertions.assertTrue(index < max);
    }

    @Order(9)
    @Test
    @DisplayName("Création d'un connecteur sur une entité")
    public void testerConnecteur() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject connecteur = api.creerConnecteur(modele.idEntite, VariableConnecteurModel.libelle,
                                VariableConnecteurModel.idConnecteur);
        Assertions.assertTrue(connecteur.has("id_ce"));
        modele.idConnecteur = connecteur.getString("id_ce");
    }

    @Order(10)
    @Test
    @DisplayName("Modification du connecteur.")
    public void testModifierConnecteur() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject connecteur = api.modifierConnecteur(modele.idEntite, modele.idConnecteur,
                                VariableConnecteurModel.donnees);
        Assertions.assertTrue(connecteur.has("result"));
        Assertions.assertEquals("ok", connecteur.getString("result"));
        JSONObject donnees = connecteur.getJSONObject("data");
        for(String cle : VariableConnecteurModel.donnees.keySet())
            Assertions.assertEquals(VariableConnecteurModel.donnees.get(cle), donnees.getString(cle));
    }

    @Order(11)
    @Test
    @DisplayName("Liaison du connecteur à un flux")
    public void testLierConnecteurFlux() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject flux = api.associerConnecteurFlux(modele.idEntite, VariableApiTestModel.nomFlux, modele.idConnecteur,
                                                     VariableApiTestModel.typeConnecteur);
        Assertions.assertTrue(flux.has("id_fe"));
        modele.idAssociation = flux.getString("id_fe");
    }

    @Order(12)
    @Test
    @DisplayName("Création d'un document.")
    public void testerCreationDocument() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject document = api.creerDocument(modele.idEntite, VariableApiTestModel.nomFlux);
        Assertions.assertTrue(document.has("id_d"));
        modele.idDocument = document.getString("id_d");
    }

    @Order(13)
    @Test
    @DisplayName("Modification d'un document.")
    public void testerModificationDocument() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject document = api.modifierDocument(modele.idEntite, modele.idDocument, VariableApiTestModel.donnees);
        Assertions.assertTrue(document.has("result"));
        Assertions.assertEquals("ok", document.getString("result"));
    }

    @Order(15)
    @Test
    @DisplayName("Récupération du détail du document")
    public void testerDetailDocument() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject document = api.detailDocument(modele.idEntite, modele.idDocument);

        Assertions.assertTrue(document.has("data"));
        JSONObject data = document.getJSONObject("data");

        for(String cle : VariableApiTestModel.donnees.keySet()) {
            Assertions.assertTrue(data.has(cle));
            Assertions.assertEquals(VariableApiTestModel.donnees.get(cle), data.getString(cle));
        }
    }

    @Order(16)
    @Test
    @DisplayName("Orientation d'un document")
    public void testerOrientationDocument() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject document = api.actionDocument(modele.idEntite, modele.idDocument, "orientation");
        Assertions.assertTrue(document.has("result"));
        Assertions.assertTrue(document.getBoolean("result"));
    }

    @Order(18)
    @Test
    @DisplayName("Suppression du document")
    public void testerSuppressionDocument() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject document = api.actionDocument(modele.idEntite, modele.idDocument, "supression");
        Assertions.assertTrue(document.has("result"));
        Assertions.assertTrue(document.getBoolean("result"));
    }

    @Order(19)
    @Test
    @DisplayName("Dissociation d'un connecteur d'un flux")
    public void testerDissocierConnecteurFlux() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject document = api.dissocierConnecteurFlux(modele.idEntite, modele.idAssociation);
        Assertions.assertTrue(document.has("result"));
        Assertions.assertEquals("ok", document.getString("result"));
    }

    @Order(20)
    @Test
    @DisplayName("Suppression du connecteur")
    public void testerSupprimerConnecteur() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject document = api.supprimerConnecteur(modele.idEntite, modele.idConnecteur);
        Assertions.assertTrue(document.has("result"));
        Assertions.assertEquals("ok", document.getString("result"));
    }

    @Order(21)
    @Test
    @DisplayName("Suppression du connecteur")
    public void testerSupprimerEntite() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject document = api.supprimerEntite(modele.idEntite);
        Assertions.assertTrue(document.has("result"));
        Assertions.assertEquals("ok", document.getString("result"));
    }
}

package controllers;

import application.Core;
import application.DetailsDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();
    ObservableList<String> positionsObsList = FXCollections.observableList(Core.positions);
    private int set = 1; // varie entre 1 et 2 (les 2 sets proposés par op gg)
    private DetailsDto champDetails;
    @FXML private Label textHelper;
    @FXML private ImageView loaderGif;
    @FXML private ListView<String> champsList;
    @FXML private ImageView backIcon;
    @FXML private Label backLabel;
    @FXML private ImageView champTile;
    @FXML private Label champName;
    @FXML private Label linkToggleSet;
    @FXML private ChoiceBox<String> positionSelector;
    @FXML private HBox hBox;
    @FXML private VBox vBox0;
    @FXML private VBox vBox1;
    @FXML private VBox vBox2;
    @FXML private HBox submainhbox1;
    @FXML private HBox submainhbox2;
    @FXML private HBox subhbox1;
    @FXML private HBox subhbox2;
    @FXML private ImageView vbox1mainperk;
    @FXML private ImageView vbox1perk1;
    @FXML private ImageView vbox1perk2;
    @FXML private ImageView vbox1perk3;
    @FXML private ImageView vbox1perk4;
    @FXML private ImageView vbox1perk5;
    @FXML private ImageView vbox1fragment1;
    @FXML private ImageView vbox1fragment2;
    @FXML private ImageView vbox1fragment3;
    @FXML private ImageView vbox2mainperk;
    @FXML private ImageView vbox2perk1;
    @FXML private ImageView vbox2perk2;
    @FXML private ImageView vbox2perk3;
    @FXML private ImageView vbox2perk4;
    @FXML private ImageView vbox2perk5;
    @FXML private ImageView vbox2fragment1;
    @FXML private ImageView vbox2fragment2;
    @FXML private ImageView vbox2fragment3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loaderGif.setVisible(false);
        positionSelector.setItems(positionsObsList);
        selectionChampsScene();
        this.fetchChamps();
        this.linkToggleSet.setText("voir set 2");
    }

    @FXML
    private void onChampSelected(MouseEvent event) {
        detailChampScene();
        try {
            String selectedChamp = champsList.getSelectionModel().getSelectedItem();
            DetailsDto details = Core.getInfo(selectedChamp);
            this.champDetails = details;
            bindPerksAndFragments(details);
            Image img = new Image("resources/riotIcons/champs/" + selectedChamp + ".jpg");
            champTile.setImage(img);
            champName.setText(selectedChamp);
            if(!"".equals(details.getPosition())) {
                System.out.println(details.getPosition());
                positionSelector.setValue(details.getPosition());
            }
        } catch (Exception ex) {
            showErrorBox(ex.getMessage());
        }
    }

    @FXML
    private void goBackToListChamps(MouseEvent event) {
        selectionChampsScene();
    }

    @FXML
    private void toggleSet(MouseEvent event) {
        if(this.set == 1) {
            this.set = 2;
            this.linkToggleSet.setText("voir set 1");
            this.bindPerksAndFragmentsSet2(this.champDetails);
        } else {
            this.set = 1;
            this.linkToggleSet.setText("voir set 2");
            this.bindPerksAndFragments(this.champDetails);
        }
    }

    private void fetchChamps() {
        list.removeAll(list);
        List<String> champs = Core.getChamps();
        list.addAll(champs);
        champsList.getItems().addAll(list);
    }

    private void selectionChampsScene() {
        hBox.setVisible(false);
        backLabel.setVisible(false);
        backIcon.setVisible(false);
        champsList.setVisible(true);
        textHelper.setVisible(true);
    }

    private void detailChampScene() {
        hBox.setVisible(true);
        backIcon.setVisible(true);
        backLabel.setVisible(true);
        champsList.setVisible(false);
        textHelper.setVisible(false);
    }

    private void showErrorBox(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        // Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        // stage.getIcons().add(new Image("ressources/pirateicon.png"));
        alert.show();
    }

    private void bindPerksAndFragments(DetailsDto details) {
        System.out.println(details.getPerks().toString());
        System.out.println(details.getFragments().toString());
        Image img = new Image("resources/riotIcons/perks/" + details.getPerks().get(0) + ".png");
        Image img1 = new Image("resources/riotIcons/perks/" + details.getPerks().get(1) + ".png");
        Image img2 = new Image("resources/riotIcons/perks/" + details.getPerks().get(2) + ".png");
        Image img3 = new Image("resources/riotIcons/perks/" + details.getPerks().get(3) + ".png");
        Image img4 = new Image("resources/riotIcons/perks/" + details.getPerks().get(4) + ".png");
        Image img5 = new Image("resources/riotIcons/perks/" + details.getPerks().get(5) + ".png");
        Image img6 = new Image("resources/riotIcons/perks/" + details.getPerks().get(6) + ".png");
        Image img7 = new Image("resources/riotIcons/perks/" + details.getPerks().get(7) + ".png");
        Image img8 = new Image("resources/riotIcons/perks/" + details.getPerks().get(8) + ".png");
        Image img9 = new Image("resources/riotIcons/perks/" + details.getPerks().get(9) + ".png");
        Image img10 = new Image("resources/riotIcons/perks/" + details.getPerks().get(10) + ".png");
        Image img11 = new Image("resources/riotIcons/perks/" + details.getPerks().get(11) + ".png");
        vbox1mainperk.setImage(img);
        vbox1perk1.setImage(img1);
        vbox1perk2.setImage(img2);
        vbox1perk3.setImage(img3);
        vbox1perk4.setImage(img4);
        vbox1perk5.setImage(img5);
        vbox2mainperk.setImage(img6);
        vbox2perk1.setImage(img7);
        vbox2perk2.setImage(img8);
        vbox2perk3.setImage(img9);
        vbox2perk4.setImage(img10);
        vbox2perk5.setImage(img11);
        Image frg1 = new Image("resources/riotIcons/perks/" + details.getFragments().get(0) + ".png");
        Image frg2 = new Image("resources/riotIcons/perks/" + details.getFragments().get(1) + ".png");
        Image frg3 = new Image("resources/riotIcons/perks/" + details.getFragments().get(2) + ".png");
        Image frg4 = new Image("resources/riotIcons/perks/" + details.getFragments().get(3) + ".png");
        Image frg5 = new Image("resources/riotIcons/perks/" + details.getFragments().get(4) + ".png");
        Image frg6 = new Image("resources/riotIcons/perks/" + details.getFragments().get(5) + ".png");
        vbox1fragment1.setImage(frg1);
        vbox1fragment2.setImage(frg2);
        vbox1fragment3.setImage(frg3);
        vbox2fragment1.setImage(frg4);
        vbox2fragment2.setImage(frg5);
        vbox2fragment3.setImage(frg6);
    }

    private void bindPerksAndFragmentsSet2(DetailsDto details) {
        System.out.println(details.getPerks().toString());
        System.out.println(details.getFragments().toString());
        Image img = new Image("resources/riotIcons/perks/" + details.getPerks().get(12) + ".png");
        Image img1 = new Image("resources/riotIcons/perks/" + details.getPerks().get(13) + ".png");
        Image img2 = new Image("resources/riotIcons/perks/" + details.getPerks().get(14) + ".png");
        Image img3 = new Image("resources/riotIcons/perks/" + details.getPerks().get(15) + ".png");
        Image img4 = new Image("resources/riotIcons/perks/" + details.getPerks().get(16) + ".png");
        Image img5 = new Image("resources/riotIcons/perks/" + details.getPerks().get(17) + ".png");
        Image img6 = new Image("resources/riotIcons/perks/" + details.getPerks().get(18) + ".png");
        Image img7 = new Image("resources/riotIcons/perks/" + details.getPerks().get(19) + ".png");
        Image img8 = new Image("resources/riotIcons/perks/" + details.getPerks().get(20) + ".png");
        Image img9 = new Image("resources/riotIcons/perks/" + details.getPerks().get(21) + ".png");
        Image img10 = new Image("resources/riotIcons/perks/" + details.getPerks().get(22) + ".png");
        Image img11 = new Image("resources/riotIcons/perks/" + details.getPerks().get(23) + ".png");
        vbox1mainperk.setImage(img);
        vbox1perk1.setImage(img1);
        vbox1perk2.setImage(img2);
        vbox1perk3.setImage(img3);
        vbox1perk4.setImage(img4);
        vbox1perk5.setImage(img5);
        vbox2mainperk.setImage(img6);
        vbox2perk1.setImage(img7);
        vbox2perk2.setImage(img8);
        vbox2perk3.setImage(img9);
        vbox2perk4.setImage(img10);
        vbox2perk5.setImage(img11);
        Image frg1 = new Image("resources/riotIcons/perks/" + details.getFragments().get(6) + ".png");
        Image frg2 = new Image("resources/riotIcons/perks/" + details.getFragments().get(7) + ".png");
        Image frg3 = new Image("resources/riotIcons/perks/" + details.getFragments().get(8) + ".png");
        Image frg4 = new Image("resources/riotIcons/perks/" + details.getFragments().get(9) + ".png");
        Image frg5 = new Image("resources/riotIcons/perks/" + details.getFragments().get(10) + ".png");
        Image frg6 = new Image("resources/riotIcons/perks/" + details.getFragments().get(11) + ".png");
        vbox1fragment1.setImage(frg1);
        vbox1fragment2.setImage(frg2);
        vbox1fragment3.setImage(frg3);
        vbox2fragment1.setImage(frg4);
        vbox2fragment2.setImage(frg5);
        vbox2fragment3.setImage(frg6);
    }

}

package com.richmond.darkhorse.ProjectSB.gui.component;
import java.util.ArrayList;
import java.util.List;

import com.richmond.darkhorse.ProjectSB.Admin;
import com.richmond.darkhorse.ProjectSB.gui.scene.AdminHome;
import com.richmond.darkhorse.ProjectSB.gui.scene.CenterWorkspaceScene;
import com.richmond.darkhorse.ProjectSB.gui.scene.ClassroomWorkspaceScene;
import com.richmond.darkhorse.ProjectSB.gui.scene.DirectorWorkspaceScene;
import com.richmond.darkhorse.ProjectSB.gui.scene.TeacherWorkspaceScene;
import com.richmond.darkhorse.ProjectSB.middleman.ChangeScene;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public interface AdminLayout extends SceneFormatter,CoreFunctions {

	/**
	 * Builds an HBox, which serves as the top pane in the default BorderPane layout for an administrator account
	 * @param stage - the current program stage
	 * @param admin - the administrator account user 
	 * @return an HBox pane 
	 */
	public default HBox buildTopPane(Stage stage,Admin admin) {
		HBox topPane = new HBox();
		ImageView logoViewer = createImageWithFitHeight("images/logo.png",250);
		ImageButton homeButton = new ImageButton(createImageWithFitHeight("images/home.png",125));
		homeButton.setOnAction(e -> Platform.runLater(new ChangeScene(stage,new AdminHome(stage,null,admin))));
		ImageButton logoutButton = new ImageButton(createImageWithFitHeight("images/logout.png",125));
		logoutButton.setOnAction(e -> saveInit(stage));
		VBox homeButtonBox = buildButtonWithLabel(homeButton,createLabel("home","text"));
		VBox logoutButtonBox = buildButtonWithLabel(logoutButton,createLabel("logout","text"));
		topPane.setAlignment(Pos.CENTER);
		topPane.getChildren().addAll(homeButtonBox,logoViewer,logoutButtonBox);
		topPane.getStyleClass().add("topbar");
		return topPane;
	}
	
	/**
	 * Builds a button with a label beneath it
	 * @param button - an ImageButton
	 * @param label - the desired label for the button
	 * @return a centered VBox with the button and the label 
	 */
	public default VBox buildButtonWithLabel(ImageButton button,Label label) {
		VBox labeledButton = new VBox();
		labeledButton.setAlignment(Pos.CENTER);
		labeledButton.getChildren().addAll(button,label);
		return labeledButton;
	}
	
	/**
	 * Builds an HBox, which serves as the bottom pane in the default BorderPane layout for an administrator account
	 * @return an HBox pane
	 */
	public default HBox buildBottomPane() {
		HBox bottomPane = new HBox();
		Label signature = new Label("Created by Marisa Richmond");
		signature.getStyleClass().add("text");
		bottomPane.getChildren().add(signature);
		bottomPane.getStyleClass().add("bottombar");
		return bottomPane;
	}
	
	/**
	 * Builds a VBox, which serves as the left pane in the default BorderPane layout for an administrator account
	 * @param stage - the current program stage
	 * @param admin - the administrator account user 
	 * @return a VBox pane
	 */
	public default VBox buildLeftPane(Stage stage,Admin admin) {
		VBox leftPane = new VBox();
		ImageButton centerButton = new ImageButton(createImageWithFitHeight("images/center.png",100));
		centerButton.setOnAction(e -> Platform.runLater(new ChangeScene(stage,new CenterWorkspaceScene(stage,null,admin))));
		Label addCenter = createLabel("Centers","label");
		ImageButton directorButton = new ImageButton(createImageWithFitHeight("images/director.png",100));
		directorButton.setOnAction(e -> Platform.runLater(new ChangeScene(stage,new DirectorWorkspaceScene(stage,null,admin))));
		Label addDirector = createLabel("Directors","label");
		ImageButton teacherButton = new ImageButton(createImageWithFitHeight("images/teacher.png",100));
		teacherButton.setOnAction(e -> Platform.runLater(new ChangeScene(stage,new TeacherWorkspaceScene(stage,null,admin))));
		Label addTeacher = createLabel("Teachers","label");
		ImageButton classroomButton = new ImageButton(createImageWithFitHeight("images/classroom.png",100));
		classroomButton.setOnAction(e -> Platform.runLater(new ChangeScene(stage,new ClassroomWorkspaceScene(stage,null,admin))));
		Label addClassroom = createLabel("Classrooms","label");
		leftPane.getChildren().addAll(centerButton,addCenter,directorButton,addDirector,teacherButton,addTeacher,classroomButton,addClassroom);
		leftPane.getStyleClass().add("sidebar");
		return leftPane;
	}
	
	/**
	 * Creates a List of labels
	 * @param labelNames - the text for the labels
	 * @param cssTag - the CSS tag for each label
	 * @return a list of labels 
	 */
	public default List<Label> populateLabels(List<String> labelNames,String cssTag){
		List<Label> labels = new ArrayList<>();
		for(String labelName : labelNames) {
			Label newLabel = createLabel(labelName,cssTag);
			labels.add(newLabel);
		}
		return labels;
	}
	
	/**
	 * Creates a list of text fields
	 * @param textFieldPrompts - the prompt text to go into the text field
	 * @param cssTag - the CSS tag for each text field
	 * @param maxWidth - the desired width of the text field
	 * @return a list of text fields
	 */
	public default List<TextField> populateTextFields(List<String> textFieldPrompts,String cssTag,int maxWidth){
		List<TextField> textFields = new ArrayList<>();
		for(String textFieldPrompt : textFieldPrompts) {
			TextField newTextField = createTextField(textFieldPrompt,cssTag,maxWidth);
			textFields.add(newTextField);
		}
		return textFields;
	}
	
	/**
	 * Populates the GridPane with the given Labels and TextFields, matching the first Label to the first TextField and so on beginning with the starting row index 
	 * @param gridpane - GridPane layout
	 * @param labels - a list of Labels
	 * @param textFields - a list of TextFields
	 * @param startIndex - the desired starting row
	 */
	public default void populateGridPane(GridPane gridpane,List<Label> labels,List<TextField> textFields,int startIndex) {
		int labelIndex = startIndex;
		for(Label label : labels) {
			placeNode(gridpane,label,0,labelIndex,"right",null);
			labelIndex++;
		}
		int textFieldIndex = startIndex;
		for(TextField textField : textFields) {
			placeNodeSpan(gridpane,textField,1,textFieldIndex,2,1,"left",null);
			textFieldIndex++;
		}
	}
	
}

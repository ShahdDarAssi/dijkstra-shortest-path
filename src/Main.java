
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

	private String selectedFilePath = "";
	private Label selectedFileLabel;
	private ComboBox<Integer> startComboBox;
	private ComboBox<Integer> endComboBox;
	private TextArea outputArea;
	private Graph currentGraph;

	@Override
	public void start(Stage primaryStage) {

		String css = this.getClass().getResource("styles.css").toExternalForm();

		Label titleLabel = new Label("Shortest Path");
		titleLabel.getStyleClass().add("title-label");

		Label subtitleLabel = new Label("Find optimal path using dijkstra algorithm");
		subtitleLabel.getStyleClass().add("subtitle-label");

		VBox titleBox = new VBox(5, titleLabel, subtitleLabel);
		titleBox.getStyleClass().add("title-box");

		Label fileSectionLabel = new Label("Select graph file");
		fileSectionLabel.getStyleClass().add("section-label");

		Button browseBtn = new Button("Browse for File");
		browseBtn.getStyleClass().add("browse-button");

		selectedFileLabel = new Label("No file selected");
		selectedFileLabel.getStyleClass().add("file-label");

		browseBtn.setOnAction(e -> {

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select Graph File");
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
					new FileChooser.ExtensionFilter("All Files", "*.*"));

			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
				selectedFilePath = selectedFile.getAbsolutePath();
				String fileName = selectedFile.getName();
				selectedFileLabel.setText(fileName);
				selectedFileLabel.getStyleClass().add("file-label-selected");
				selectedFileLabel.getStyleClass().remove("file-label");
				loadGraph();
			}
		});

		VBox fileSection = new VBox(10, fileSectionLabel, browseBtn, selectedFileLabel);
		fileSection.getStyleClass().add("file-section");

		GridPane inputPane = new GridPane();
		inputPane.getStyleClass().add("input-pane");

		Label startLabel = new Label("Start Vertex : ");
		startLabel.getStyleClass().add("input-label");
		startComboBox = new ComboBox<>();
		startComboBox.setPromptText("Select start vertex");
		startComboBox.setPrefWidth(200);
		startComboBox.setDisable(true);
		startComboBox.getStyleClass().add("combo-box");

		Label endLabel = new Label("End Vertex : ");
		endLabel.getStyleClass().add("input-label");
		endComboBox = new ComboBox<>();
		endComboBox.setPromptText("Select end vertex");
		endComboBox.setPrefWidth(200);
		endComboBox.setDisable(true);
		endComboBox.getStyleClass().add("combo-box");

		VBox verticesBox = new VBox(10, new HBox(10, startLabel, startComboBox), new HBox(10, endLabel, endComboBox));
		inputPane.add(verticesBox, 0, 0, 2, 1);

		outputArea = new TextArea();
		outputArea.setEditable(false);
		outputArea.setWrapText(true);
		outputArea.getStyleClass().add("output-area");
		outputArea.setPrefHeight(300);

		VBox outputBox = new VBox(10, new Label("Results : "), outputArea);
		outputBox.getStyleClass().add("output-box");

		Button distanceBtn = new Button("Shortest by Distance");
		Button timeBtn = new Button("Shortest by Time");
		Button bothBtn = new Button("Shortest by Both");

		distanceBtn.getStyleClass().add("action-button");
		timeBtn.getStyleClass().add("action-button");
		bothBtn.getStyleClass().add("action-button");

		HBox buttonsBox = new HBox(20, distanceBtn, timeBtn, bothBtn);
		buttonsBox.getStyleClass().add("buttons-box");

		distanceBtn.setOnAction(e -> runDijkstra(currentGraph, startComboBox, endComboBox, outputArea, "distance"));
		timeBtn.setOnAction(e -> runDijkstra(currentGraph, startComboBox, endComboBox, outputArea, "time"));
		bothBtn.setOnAction(e -> runDijkstra(currentGraph, startComboBox, endComboBox, outputArea, "both"));

		VBox root = new VBox(15, titleBox, fileSection, inputPane, buttonsBox, outputBox);
		root.getStyleClass().add("root");
		root.setAlignment(Pos.TOP_CENTER);

		Scene scene = new Scene(root, 850, 700);
		scene.getStylesheets().add(css);
		primaryStage.setScene(scene);
		primaryStage.setMinWidth(700);
		primaryStage.setMinHeight(550);
		primaryStage.setTitle("Shortest path finder");
		primaryStage.show();

	}

	private void runDijkstra(Graph graph, ComboBox<Integer> startCombo, ComboBox<Integer> endCombo, TextArea outputArea,
			String options) {

		if (graph == null) {
			outputArea.setText("Please select a graph file first");
			outputArea.getStyleClass().add("error-output");
			return;
		}

		Integer startId = startCombo.getValue();
		Integer endId = endCombo.getValue();

		if (startId == null || endId == null) {
			outputArea.setText("Please select start and end vertices");
			outputArea.getStyleClass().add("error-output");
			return;
		}

		if (startId.equals(endId)) {
			outputArea.setText("Start and end vertices cannot be the same");
			outputArea.getStyleClass().add("error-output");
			return;
		}

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("start : ").append(startId).append("\n").append("end : ").append(endId).append("\n\n");

		if (options.equals("distance") || options.equals("both")) {
		    TableEntry[] tableDist = Dijkstra.dijkstra(graph, startId, false);
		    stringBuilder.append("Shortest Path by Distance (Km) :\n");
		    Dijkstra.printPath(endId, tableDist, graph, stringBuilder, false); 
		    stringBuilder.append("\n");
		}

		if (options.equals("time") || options.equals("both")) {
		    TableEntry[] tableTime = Dijkstra.dijkstra(graph, startId, true);
		    stringBuilder.append("Shortest Path by Time (minutes) :\n");
		    Dijkstra.printPath(endId, tableTime, graph, stringBuilder, true); 
		    stringBuilder.append("\n");
		}


		outputArea.setText(stringBuilder.toString());
		outputArea.getStyleClass().remove("error-output");
		outputArea.getStyleClass().add("output-area");
	}

	private void loadGraph() {

	    currentGraph = ReadFile.readGraphFromFile(selectedFilePath);

	    if (currentGraph == null) {
	        return;
	    }

	    int numVertices = currentGraph.getNumberOfVertices();

	    ObservableList<Integer> vertexList = FXCollections.observableArrayList();
	    for (int i = 0; i < numVertices; i++) {
	        vertexList.add(i);
	    }

	    startComboBox.setItems(vertexList);
	    endComboBox.setItems(vertexList);

	    startComboBox.setDisable(false);
	    endComboBox.setDisable(false);

	    int[] firstLineData = ReadFile.readFirstLine(selectedFilePath);
	    int start = firstLineData[0];
	    int end = firstLineData[1];
	    int option = firstLineData[2];

	    startComboBox.setValue(start);
	    endComboBox.setValue(end);

	    String optionStr;
	    if (option == 1) {
	        optionStr = "distance";
	    } else if (option == 2) {
	        optionStr = "time";
	    } else if (option == 3) {
	        optionStr = "both";
	    } else {
	        optionStr = "both";
	    }

	   runDijkstra(currentGraph, startComboBox, endComboBox, outputArea, optionStr);
	}


	public static void main(String[] args) {
		launch(args);
	}
}
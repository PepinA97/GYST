import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GetYourShitTogether extends Application {

	private String mediaTypeDisplayed;
	private String tableTypeDisplayed;
	private AnimeBacklog animeBacklog = new AnimeBacklog();
	private TVShowBacklog tvBacklog = new TVShowBacklog();
	private VideoGameBacklog vgBacklog = new VideoGameBacklog();
	private MovieBacklog movieBacklog = new MovieBacklog();
	private VBox vbox;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Get Thine Shite Together, Cur");

		// Create the registration form grid pane
		this.vbox = new VBox();

		vbox.setSpacing(10);

		GridPane gridPane = new GridPane();
		vbox.getChildren().add(gridPane);
		// Add UI controls to the registration form grid pane
		addUIControls(vbox, "TVShows", "Backlogs");
		// Create a scene with registration form grid pane as the root node
		Scene scene = new Scene(vbox, 1440, 760);
		// Set the scene in primary stage
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	private void addUIControls(VBox vbox, String mediaType, String tableType) {

		this.InitializeButtonPane(vbox);
		mediaTypeDisplayed = mediaType;
		tableTypeDisplayed = tableType;
		TableView<TVShowBacklogItem> tvTable = loadTVTable();
		HBox dummyBox = new HBox();
		vbox.getChildren().add(tvTable);
		vbox.getChildren().add(dummyBox);

		this.InitializeBacklogButtons(vbox);

	}

	private void InitializeButtonPane(VBox vbox) {

		GridPane gridPane = (GridPane) vbox.getChildren().get(0);

		gridPane.setAlignment(Pos.CENTER);

		// columnOneConstraints will be applied to all the nodes placed in column one.
		ColumnConstraints column1Constraints = new ColumnConstraints();
		ColumnConstraints column2Constraints = new ColumnConstraints();
		column2Constraints.setPercentWidth(11);
		// columnTwoConstraints will be applied to all the nodes placed in column two.
		ColumnConstraints column3Constraints = new ColumnConstraints();
		ColumnConstraints column4Constrains = new ColumnConstraints();
		ColumnConstraints column5Constraints = new ColumnConstraints();
		column5Constraints.setPercentWidth(11);

		gridPane.getColumnConstraints().addAll(column1Constraints, column2Constraints, column3Constraints,
				column4Constrains, column5Constraints);

		HBox buttons = new HBox();

		// initialize about button
		Button aboutButton = new Button("About");
		aboutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("about button pressed");
			}
		});

		// initialize recommend button
		Button recommendButton = new Button("Recommendations");
		recommendButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				tableTypeDisplayed = "Recommendations";
				switch (mediaTypeDisplayed) {
				case "TVShows":
					vbox.getChildren().set(1, loadTVRecommendations());
					break;
				case "Movies":
					vbox.getChildren().set(1, loadMovieRecommendations());
					break;
				case "Anime":
					vbox.getChildren().set(1, loadAnimeRecommendations());
					break;
				case "VideoGames":
					vbox.getChildren().set(1, loadVideoGameRecommendations());
					break;
				}

				initializeRecommendationButtons();
			}
		});

		// initialize backlog button
		Button backlogButton = new Button("Backlogs");
		backlogButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				tableTypeDisplayed = "Backlogs";
				switch (mediaTypeDisplayed) {
				case "TVShows":
					vbox.getChildren().set(1, loadTVTable());
					break;
				case "Movies":
					vbox.getChildren().set(1, loadMovieTable());
					break;
				case "Anime":
					vbox.getChildren().set(1, loadAnimeTable());
					break;
				case "VideoGames":
					vbox.getChildren().set(1, loadVideoGameTable());
					break;

				}

				InitializeBacklogButtons(vbox);
			}
		});

		buttons.getChildren().addAll(aboutButton, recommendButton, backlogButton);
		gridPane.add(buttons, 0, 0);

		Label bufferLabel1 = new Label();
		gridPane.add(bufferLabel1, 1, 0);

		// initialize search label & text field
		Label searchLabel = new Label("Search: ");
		gridPane.add(searchLabel, 2, 0);

		TextField searchField = new TextField();
		searchField.setPrefHeight(40);
		searchField.setPrefWidth(550);
		searchField.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String searchText = searchField.getText();
				searchMedia(searchText);

			}
		});

		gridPane.add(searchField, 3, 0);

		HBox categories = new HBox();

		// initialize TV Shows button
		Button tvButton = new Button("TV Shows");
		tvButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				mediaTypeDisplayed = "TVShows";
				switch (tableTypeDisplayed) {
				case "Backlogs":
					vbox.getChildren().set(1, loadTVTable());
					break;
				case "Recommendations":
					vbox.getChildren().set(1, loadTVRecommendations());
					break;
				case "Search":
					searchMedia(searchField.getText());
					break;
				}
			}
		});

		// initialize Movies button
		Button movieButton = new Button("Movies");
		movieButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				mediaTypeDisplayed = "Movies";
				switch (tableTypeDisplayed) {
				case "Backlogs":
					vbox.getChildren().set(1, loadMovieTable());
					break;
				case "Recommendations":
					vbox.getChildren().set(1, loadMovieRecommendations());
					break;
				case "Search":
					searchMedia(searchField.getText());
					break;
				}
			}
		});

		// initialize Anime button
		Button animeButton = new Button("Anime");
		animeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				mediaTypeDisplayed = "Anime";
				switch (tableTypeDisplayed) {
				case "Backlogs":
					vbox.getChildren().set(1, loadAnimeTable());
					break;
				case "Recommendations":
					vbox.getChildren().set(1, loadAnimeRecommendations());
					break;
				case "Search":
					searchMedia(searchField.getText());
					break;
				}
			}
		});

		// initialize VideoGames button
		Button vidyaButton = new Button("Videogames");
		vidyaButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				mediaTypeDisplayed = "VideoGames";
				switch (tableTypeDisplayed) {
				case "Backlogs":
					vbox.getChildren().set(1, loadVideoGameTable());
					break;
				case "Recommendations":
					vbox.getChildren().set(1, loadVideoGameRecommendations());
					break;
				case "Search":
					searchMedia(searchField.getText());
					break;
				}
			}
		});

		categories.getChildren().addAll(tvButton, movieButton, animeButton, vidyaButton);

		gridPane.add(categories, 5, 0);

	}

	@SuppressWarnings("unchecked")
	private TableView<TVShowBacklogItem> loadTVTable() {

		TableView<TVShowBacklogItem> table = new TableView<TVShowBacklogItem>();
		ObservableList<TVShowBacklogItem> data = tvBacklog.fetchAll();
		table.setItems(data);

		// initialize table with Title, Genre, Status, Rating, and Priority headers
		TableColumn<TVShowBacklogItem, String> titleCol = new TableColumn<TVShowBacklogItem, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<TVShowBacklogItem, String>("title"));
		TableColumn<TVShowBacklogItem, String> authorCol = new TableColumn<TVShowBacklogItem, String>("Genre");
		authorCol.setCellValueFactory(new PropertyValueFactory<TVShowBacklogItem, String>("genre"));
		TableColumn<TVShowBacklogItem, String> statusCol = new TableColumn<TVShowBacklogItem, String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<TVShowBacklogItem, String>("status"));
		TableColumn<TVShowBacklogItem, String> ratingCol = new TableColumn<TVShowBacklogItem, String>("Rating");
		ratingCol.setCellValueFactory(new PropertyValueFactory<TVShowBacklogItem, String>("rating"));
		TableColumn<TVShowBacklogItem, Integer> priorityCol = new TableColumn<TVShowBacklogItem, Integer>("Priority");
		priorityCol.setCellValueFactory(new PropertyValueFactory<TVShowBacklogItem, Integer>("priority"));

		table.getColumns().setAll(titleCol, authorCol, statusCol, ratingCol, priorityCol);
		table.setPrefWidth(1430);
		table.setPrefHeight(635);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;
	}

	@SuppressWarnings("unchecked")
	private TableView<MovieBacklogItem> loadMovieTable() {

		TableView<MovieBacklogItem> table = new TableView<MovieBacklogItem>();
		ObservableList<MovieBacklogItem> data = movieBacklog.fetchAll();
		table.setItems(data);

		// initialize table with Title, Genre, Status, Rating, and Priority headers
		TableColumn<MovieBacklogItem, String> titleCol = new TableColumn<MovieBacklogItem, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<MovieBacklogItem, String>("title"));
		TableColumn<MovieBacklogItem, String> authorCol = new TableColumn<MovieBacklogItem, String>("Genre");
		authorCol.setCellValueFactory(new PropertyValueFactory<MovieBacklogItem, String>("genre"));
		TableColumn<MovieBacklogItem, String> statusCol = new TableColumn<MovieBacklogItem, String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<MovieBacklogItem, String>("status"));
		TableColumn<MovieBacklogItem, String> ratingCol = new TableColumn<MovieBacklogItem, String>("Rating");
		ratingCol.setCellValueFactory(new PropertyValueFactory<MovieBacklogItem, String>("rating"));
		TableColumn<MovieBacklogItem, Integer> priorityCol = new TableColumn<MovieBacklogItem, Integer>("Priority");
		priorityCol.setCellValueFactory(new PropertyValueFactory<MovieBacklogItem, Integer>("priority"));

		table.getColumns().setAll(titleCol, authorCol, statusCol, ratingCol, priorityCol);
		table.setPrefWidth(1430);
		table.setPrefHeight(635);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;
	}

	@SuppressWarnings("unchecked")
	private TableView<AnimeBacklogItem> loadAnimeTable() {

		TableView<AnimeBacklogItem> table = new TableView<AnimeBacklogItem>();
		ObservableList<AnimeBacklogItem> data = animeBacklog.fetchAll();
		table.setItems(data);

		// initialize table with Title, Genre, Status, Rating, and Priority headers
		TableColumn<AnimeBacklogItem, String> titleCol = new TableColumn<AnimeBacklogItem, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<AnimeBacklogItem, String>("title"));
		TableColumn<AnimeBacklogItem, String> authorCol = new TableColumn<AnimeBacklogItem, String>("Genre");
		authorCol.setCellValueFactory(new PropertyValueFactory<AnimeBacklogItem, String>("genre"));
		TableColumn<AnimeBacklogItem, String> statusCol = new TableColumn<AnimeBacklogItem, String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<AnimeBacklogItem, String>("status"));
		TableColumn<AnimeBacklogItem, String> ratingCol = new TableColumn<AnimeBacklogItem, String>("Rating");
		ratingCol.setCellValueFactory(new PropertyValueFactory<AnimeBacklogItem, String>("rating"));
		TableColumn<AnimeBacklogItem, Integer> priorityCol = new TableColumn<AnimeBacklogItem, Integer>("Priority");
		priorityCol.setCellValueFactory(new PropertyValueFactory<AnimeBacklogItem, Integer>("priority"));

		table.getColumns().setAll(titleCol, authorCol, statusCol, ratingCol, priorityCol);
		table.setPrefWidth(1430);
		table.setPrefHeight(635);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;
	}

	@SuppressWarnings("unchecked")
	private TableView<VideoGameBacklogItem> loadVideoGameTable() {

		TableView<VideoGameBacklogItem> table = new TableView<VideoGameBacklogItem>();
		ObservableList<VideoGameBacklogItem> data = vgBacklog.fetchAll();
		table.setItems(data);

		// initialize table with Title, Genre, Status, Rating, and Priority headers
		TableColumn<VideoGameBacklogItem, String> titleCol = new TableColumn<VideoGameBacklogItem, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<VideoGameBacklogItem, String>("title"));
		TableColumn<VideoGameBacklogItem, String> authorCol = new TableColumn<VideoGameBacklogItem, String>("Genre");
		authorCol.setCellValueFactory(new PropertyValueFactory<VideoGameBacklogItem, String>("genre"));
		TableColumn<VideoGameBacklogItem, String> statusCol = new TableColumn<VideoGameBacklogItem, String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory<VideoGameBacklogItem, String>("status"));
		TableColumn<VideoGameBacklogItem, String> ratingCol = new TableColumn<VideoGameBacklogItem, String>("Rating");
		ratingCol.setCellValueFactory(new PropertyValueFactory<VideoGameBacklogItem, String>("rating"));
		TableColumn<VideoGameBacklogItem, Integer> priorityCol = new TableColumn<VideoGameBacklogItem, Integer>(
				"Priority");
		priorityCol.setCellValueFactory(new PropertyValueFactory<VideoGameBacklogItem, Integer>("priority"));

		table.getColumns().setAll(titleCol, authorCol, statusCol, ratingCol, priorityCol);
		table.setPrefWidth(1430);
		table.setPrefHeight(635);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;
	}

	@SuppressWarnings("unchecked")
	private TableView<TVShow> loadTVRecommendations() {
		TableView<TVShow> table = new TableView<TVShow>();

		TVShowRecommendations tvrecs = new TVShowRecommendations();
		ObservableList<TVShow> data = tvrecs.getRecommendations();
		table.setItems(data);

		// initialize table with Title, Genre, Rating, Release Date, and Directors
		// headers
		TableColumn<TVShow, String> titleCol = new TableColumn<TVShow, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<TVShow, String>("title"));
		TableColumn<TVShow, String> genreCol = new TableColumn<TVShow, String>("Genre");
		genreCol.setCellValueFactory(new PropertyValueFactory<TVShow, String>("genre"));
		TableColumn<TVShow, String> ratingcol = new TableColumn<TVShow, String>("Rating");
		ratingcol.setCellValueFactory(new PropertyValueFactory<TVShow, String>("rating"));
		TableColumn<TVShow, Integer> releaseCol = new TableColumn<TVShow, Integer>("Release Date");
		releaseCol.setCellValueFactory(new PropertyValueFactory<TVShow, Integer>("releaseDate"));
		TableColumn<TVShow, String> directorCol = new TableColumn<TVShow, String>("Directors");
		directorCol.setCellValueFactory(new PropertyValueFactory<TVShow, String>("directors"));

		table.getColumns().setAll(titleCol, genreCol, ratingcol, releaseCol, directorCol);
		table.setPrefWidth(1430);
		table.setPrefHeight(635);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;
	}

	@SuppressWarnings("unchecked")
	private TableView<Movie> loadMovieRecommendations() {

		TableView<Movie> table = new TableView<Movie>();

		MovieRecommendations movrecs = new MovieRecommendations();
		ObservableList<Movie> data = movrecs.getRecommendations();
		table.setItems(data);

		// initialize table with Title, Rating, Release Date, and Directors headers
		TableColumn<Movie, String> titleCol = new TableColumn<Movie, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
		TableColumn<Movie, String> genreCol = new TableColumn<Movie, String>("Genre");
		genreCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
		TableColumn<Movie, String> ratingcol = new TableColumn<Movie, String>("Rating");
		ratingcol.setCellValueFactory(new PropertyValueFactory<Movie, String>("rating"));
		TableColumn<Movie, Integer> releaseCol = new TableColumn<Movie, Integer>("Release Date");
		releaseCol.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("releaseDate"));
		TableColumn<Movie, String> directorCol = new TableColumn<Movie, String>("Directors");
		directorCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("directors"));

		table.getColumns().setAll(titleCol, genreCol, ratingcol, releaseCol, directorCol);
		table.setPrefWidth(1430);
		table.setPrefHeight(635);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;

	}

	@SuppressWarnings("unchecked")
	private TableView<Anime> loadAnimeRecommendations() {
		TableView<Anime> table = new TableView<Anime>();

		AnimeRecommendations tvrecs = new AnimeRecommendations();
		ObservableList<Anime> data = tvrecs.getRecommendations();
		table.setItems(data);

		// initialize table with Title, Genre, Rating headers
		TableColumn<Anime, String> titleCol = new TableColumn<Anime, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<Anime, String>("title"));
		TableColumn<Anime, String> genreCol = new TableColumn<Anime, String>("Genre");
		genreCol.setCellValueFactory(new PropertyValueFactory<Anime, String>("genre"));
		TableColumn<Anime, String> ratingcol = new TableColumn<Anime, String>("Rating");
		ratingcol.setCellValueFactory(new PropertyValueFactory<Anime, String>("rating"));

		table.getColumns().setAll(titleCol, genreCol, ratingcol);
		table.setPrefWidth(1430);
		table.setPrefHeight(635);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;
	}

	@SuppressWarnings("unchecked")
	private TableView<VideoGame> loadVideoGameRecommendations() {
		TableView<VideoGame> table = new TableView<VideoGame>();

		VideoGameRecommendations tvrecs = new VideoGameRecommendations();
		ObservableList<VideoGame> data = tvrecs.getRecommendations();
		table.setItems(data);

		// initialize table with Title, Genre, Rating, Release Date, and Platforms
		TableColumn<VideoGame, String> titleCol = new TableColumn<VideoGame, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("title"));
		TableColumn<VideoGame, String> genreCol = new TableColumn<VideoGame, String>("Genre");
		genreCol.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("genre"));
		TableColumn<VideoGame, String> ratingcol = new TableColumn<VideoGame, String>("Rating");
		ratingcol.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("rating"));
		TableColumn<VideoGame, Integer> releaseCol = new TableColumn<VideoGame, Integer>("Release Date");
		releaseCol.setCellValueFactory(new PropertyValueFactory<VideoGame, Integer>("releaseDate"));
		TableColumn<VideoGame, String> platformCol = new TableColumn<VideoGame, String>("Platforms");
		platformCol.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("platforms"));

		table.getColumns().setAll(titleCol, genreCol, ratingcol, releaseCol, platformCol);
		table.setPrefWidth(1430);
		table.setPrefHeight(635);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;
	}

	@SuppressWarnings("unchecked")
	private TableView<TVShow> loadTVSearch(String searchText) {
		TableView<TVShow> table = new TableView<TVShow>();

		TVShowSearch tvsearch = new TVShowSearch();
		ObservableList<TVShow> data = tvsearch.search(searchText);
		table.setItems(data);

		// initialize table with Title, Genre, Rating, Release Date, and Directors
		TableColumn<TVShow, String> titleCol = new TableColumn<TVShow, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<TVShow, String>("title"));
		TableColumn<TVShow, String> genreCol = new TableColumn<TVShow, String>("Genre");
		genreCol.setCellValueFactory(new PropertyValueFactory<TVShow, String>("genre"));
		TableColumn<TVShow, String> ratingcol = new TableColumn<TVShow, String>("Rating");
		ratingcol.setCellValueFactory(new PropertyValueFactory<TVShow, String>("rating"));
		TableColumn<TVShow, Integer> releaseCol = new TableColumn<TVShow, Integer>("Release Date");
		releaseCol.setCellValueFactory(new PropertyValueFactory<TVShow, Integer>("releaseDate"));
		TableColumn<TVShow, String> directorCol = new TableColumn<TVShow, String>("Directors");
		directorCol.setCellValueFactory(new PropertyValueFactory<TVShow, String>("directors"));

		table.getColumns().setAll(titleCol, genreCol, ratingcol, releaseCol, directorCol);
		table.setPrefWidth(1430);
		table.setPrefHeight(635);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;

	}

	@SuppressWarnings("unchecked")
	private TableView<Movie> loadMovieSearch(String searchText) {
		TableView<Movie> table = new TableView<Movie>();

		MovieSearch movsearch = new MovieSearch();
		ObservableList<Movie> data = movsearch.search(searchText);
		table.setItems(data);

		// initialize table with Title, Genre, Rating, Release Date, and Directors
		TableColumn<Movie, String> titleCol = new TableColumn<Movie, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
		TableColumn<Movie, String> genreCol = new TableColumn<Movie, String>("Genre");
		genreCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
		TableColumn<Movie, String> ratingcol = new TableColumn<Movie, String>("Rating");
		ratingcol.setCellValueFactory(new PropertyValueFactory<Movie, String>("rating"));
		TableColumn<Movie, Integer> releaseCol = new TableColumn<Movie, Integer>("Release Date");
		releaseCol.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("releaseDate"));
		TableColumn<Movie, String> directorCol = new TableColumn<Movie, String>("Directors");
		directorCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("directors"));

		table.getColumns().setAll(titleCol, genreCol, ratingcol, releaseCol, directorCol);
		table.setPrefWidth(1430);
		table.setPrefHeight(635);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;
	}

	@SuppressWarnings("unchecked")
	private TableView<Anime> loadAnimeSearch(String searchText) {
		TableView<Anime> table = new TableView<Anime>();

		AnimeSearch animesearch = new AnimeSearch();
		ObservableList<Anime> data = animesearch.search(searchText);
		table.setItems(data);

		// initialize table with Title, Genre, and Rating
		TableColumn<Anime, String> titleCol = new TableColumn<Anime, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<Anime, String>("title"));
		TableColumn<Anime, String> genreCol = new TableColumn<Anime, String>("Genre");
		genreCol.setCellValueFactory(new PropertyValueFactory<Anime, String>("genre"));
		TableColumn<Anime, String> ratingcol = new TableColumn<Anime, String>("Rating");
		ratingcol.setCellValueFactory(new PropertyValueFactory<Anime, String>("rating"));

		table.getColumns().setAll(titleCol, genreCol, ratingcol);
		table.setPrefWidth(1430);
		table.setPrefHeight(635);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;
	}

	@SuppressWarnings("unchecked")
	private TableView<VideoGame> loadVideoGameSearch(String searchText) {
		TableView<VideoGame> table = new TableView<VideoGame>();

		VideoGameSearch vidsearch = new VideoGameSearch();
		ObservableList<VideoGame> data = vidsearch.search(searchText);
		table.setItems(data);

		// initialize table with Title, Genre, Rating, Release Date, and Platforms
		TableColumn<VideoGame, String> titleCol = new TableColumn<VideoGame, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("title"));
		TableColumn<VideoGame, String> genreCol = new TableColumn<VideoGame, String>("Genre");
		genreCol.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("genre"));
		TableColumn<VideoGame, String> ratingcol = new TableColumn<VideoGame, String>("Rating");
		ratingcol.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("rating"));
		TableColumn<VideoGame, Integer> releaseCol = new TableColumn<VideoGame, Integer>("Release Date");
		releaseCol.setCellValueFactory(new PropertyValueFactory<VideoGame, Integer>("releaseDate"));
		TableColumn<VideoGame, String> platformCol = new TableColumn<VideoGame, String>("Platforms");
		platformCol.setCellValueFactory(new PropertyValueFactory<VideoGame, String>("platforms"));

		table.getColumns().setAll(titleCol, genreCol, ratingcol, releaseCol, platformCol);
		table.setPrefWidth(1430);
		table.setPrefHeight(635);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		return table;
	}

	@SuppressWarnings("unchecked")
	private void initializeRecommendationButtons() {

		HBox bottomRow = new HBox();

		Button addButton = new Button("Add Recommended Media");
		addButton.setPrefWidth(300);
		addButton.setPrefHeight(50);
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				Stage addRecommendedStage = new Stage();
				// show recommended media depending on which media type is currently selected
				switch (mediaTypeDisplayed) {
				case "TVShows":
					TableView<TVShow> tvTable = (TableView<TVShow>) vbox.getChildren().get(1);

					if (tvTable.getSelectionModel().getSelectedItem() != null) {
						TVShow selectedShow = tvTable.getSelectionModel().getSelectedItem();
						createAddRecommendedStage(addRecommendedStage, selectedShow);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION, "Select a recommendation to add.");
						alert.showAndWait();
					}
					break;
				case "Movies":

					TableView<Movie> movieTable = (TableView<Movie>) vbox.getChildren().get(1);

					if (movieTable.getSelectionModel().getSelectedItem() != null) {
						Movie selectedMovie = movieTable.getSelectionModel().getSelectedItem();
						createAddRecommendedStage(addRecommendedStage, selectedMovie);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION, "Select a recommendation to add.");
						alert.showAndWait();
					}

					break;
				case "Anime":

					TableView<Anime> animeTable = (TableView<Anime>) vbox.getChildren().get(1);

					if (animeTable.getSelectionModel().getSelectedItem() != null) {
						Anime selectedAnime = animeTable.getSelectionModel().getSelectedItem();
						createAddRecommendedStage(addRecommendedStage, selectedAnime);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION, "Select a recommendation to add.");
						alert.showAndWait();
					}

					break;
				case "VideoGames":

					TableView<VideoGame> vgTable = (TableView<VideoGame>) vbox.getChildren().get(1);

					if (vgTable.getSelectionModel().getSelectedItem() != null) {
						VideoGame selectedGame = vgTable.getSelectionModel().getSelectedItem();
						createAddRecommendedStage(addRecommendedStage, selectedGame);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION, "Select a recommendation to add.");
						alert.showAndWait();
					}

					break;
				default:
					System.out.println("Danger there be dragons in the Gap");

				}

			}
		});

		bottomRow.setAlignment(Pos.CENTER);
		bottomRow.getChildren().add(addButton);
		vbox.getChildren().set(2, bottomRow);

	}

	@SuppressWarnings("unchecked")
	private void initializeSearchButtons() {

		HBox bottomRow = new HBox();

		Button addButton = new Button("Add Searched Media");
		addButton.setPrefWidth(300);
		addButton.setPrefHeight(50);
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				Stage addSearchedStage = new Stage();
				switch (mediaTypeDisplayed) {
				case "TVShows":
					TableView<TVShow> tvTable = (TableView<TVShow>) vbox.getChildren().get(1);

					if (tvTable.getSelectionModel().getSelectedItem() != null) {
						TVShow selectedShow = tvTable.getSelectionModel().getSelectedItem();
						createAddSearchedStage(addSearchedStage, selectedShow);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION, "Select a media to add.");
						alert.showAndWait();
					}
					break;
				case "Movies":

					TableView<Movie> movieTable = (TableView<Movie>) vbox.getChildren().get(1);

					if (movieTable.getSelectionModel().getSelectedItem() != null) {
						Movie selectedMovie = movieTable.getSelectionModel().getSelectedItem();
						createAddSearchedStage(addSearchedStage, selectedMovie);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION, "Select a media to add.");
						alert.showAndWait();
					}

					break;
				case "Anime":

					TableView<Anime> animeTable = (TableView<Anime>) vbox.getChildren().get(1);

					if (animeTable.getSelectionModel().getSelectedItem() != null) {
						Anime selectedAnime = animeTable.getSelectionModel().getSelectedItem();
						createAddSearchedStage(addSearchedStage, selectedAnime);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION, "Select a media to add.");
						alert.showAndWait();
					}

					break;
				case "VideoGames":

					TableView<VideoGame> vgTable = (TableView<VideoGame>) vbox.getChildren().get(1);

					if (vgTable.getSelectionModel().getSelectedItem() != null) {
						VideoGame selectedGame = vgTable.getSelectionModel().getSelectedItem();
						createAddSearchedStage(addSearchedStage, selectedGame);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION, "Select a media to add.");
						alert.showAndWait();
					}

					break;
				default:
					System.out.println("Danger there be dragons in the Gap");

				}

			}
		});

		bottomRow.setAlignment(Pos.CENTER);
		bottomRow.getChildren().add(addButton);
		vbox.getChildren().set(2, bottomRow);

	}

	private void searchMedia(String searchText) {
		tableTypeDisplayed = "Search";
		switch (mediaTypeDisplayed) {
		case "TVShows":
			vbox.getChildren().set(1, loadTVSearch(searchText));
			break;
		case "Movies":
			vbox.getChildren().set(1, loadMovieSearch(searchText));
			break;
		case "Anime":
			vbox.getChildren().set(1, loadAnimeSearch(searchText));
			break;
		case "VideoGames":
			vbox.getChildren().set(1, loadVideoGameSearch(searchText));
			break;

		}

		initializeSearchButtons();

	}

	@SuppressWarnings("unchecked")
	private void InitializeBacklogButtons(VBox vbox) {
		HBox bottomRow = new HBox();

		// initialize delete button
		Button deleteButton = new Button("Delete");

		deleteButton.setPrefWidth(100);
		deleteButton.setPrefHeight(50);
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					switch (mediaTypeDisplayed) {

					case "TVShows":
						TableView<TVShowBacklogItem> tvTable = (TableView<TVShowBacklogItem>) vbox.getChildren().get(1);
						TVShowBacklogItem selectedShowForDeletion = tvTable.getSelectionModel().getSelectedItem();
						tvBacklog.Delete(selectedShowForDeletion);
						vbox.getChildren().set(1, loadTVTable());
						break;
					case "Movies":
						TableView<MovieBacklogItem> movieTable = (TableView<MovieBacklogItem>) vbox.getChildren()
								.get(1);
						MovieBacklogItem selectedMovieForDeletion = movieTable.getSelectionModel().getSelectedItem();
						movieBacklog.Delete(selectedMovieForDeletion);
						vbox.getChildren().set(1, loadMovieTable());
						break;
					case "Anime":
						TableView<AnimeBacklogItem> animeTable = (TableView<AnimeBacklogItem>) vbox.getChildren()
								.get(1);
						AnimeBacklogItem selectedAnimeForDeletion = animeTable.getSelectionModel().getSelectedItem();
						animeBacklog.Delete(selectedAnimeForDeletion);
						vbox.getChildren().set(1, loadAnimeTable());
						break;
					case "VideoGames":
						TableView<VideoGameBacklogItem> vgTable = (TableView<VideoGameBacklogItem>) vbox.getChildren()
								.get(1);
						VideoGameBacklogItem selectedVGForDeletion = vgTable.getSelectionModel().getSelectedItem();
						vgBacklog.Delete(selectedVGForDeletion);
						vbox.getChildren().set(1, loadVideoGameTable());
						break;
					default:
						System.out.println("Danger there be dragons in the Gap");
					}
				} catch (Exception exception) {

				}
			}
		});

		// initialize add button
		Button addButton = new Button("Add");

		addButton.setPrefWidth(100);
		addButton.setPrefHeight(50);
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Stage addStage = new Stage();
				createAddForm(addStage);
			}
		});

		// initialize edit button
		Button editButton = new Button("Edit");

		editButton.setPrefWidth(100);
		editButton.setPrefHeight(50);
		editButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Stage editStage = new Stage();
				switch (mediaTypeDisplayed) {
				case "TVShows":
					TableView<TVShowBacklogItem> tvTable = (TableView<TVShowBacklogItem>) vbox.getChildren().get(1);

					if (tvTable.getSelectionModel().getSelectedItem() != null) {
						TVShowBacklogItem selectedShow = tvTable.getSelectionModel().getSelectedItem();
						createEditForm(editStage, selectedShow);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION, "Select a row to edit.");
						alert.showAndWait();
					}
					break;
				case "Movies":

					TableView<MovieBacklogItem> movieTable = (TableView<MovieBacklogItem>) vbox.getChildren().get(1);

					if (movieTable.getSelectionModel().getSelectedItem() != null) {
						MovieBacklogItem selectedMovie = movieTable.getSelectionModel().getSelectedItem();
						createEditForm(editStage, selectedMovie);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION, "Select a row to edit.");
						alert.showAndWait();
					}

					break;
				case "Anime":

					TableView<AnimeBacklogItem> animeTable = (TableView<AnimeBacklogItem>) vbox.getChildren().get(1);

					if (animeTable.getSelectionModel().getSelectedItem() != null) {
						AnimeBacklogItem selectedAnime = animeTable.getSelectionModel().getSelectedItem();
						createEditForm(editStage, selectedAnime);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION, "Select a row to edit.");
						alert.showAndWait();
					}

					break;
				case "VideoGames":

					TableView<VideoGameBacklogItem> vgTable = (TableView<VideoGameBacklogItem>) vbox.getChildren()
							.get(1);

					if (vgTable.getSelectionModel().getSelectedItem() != null) {
						VideoGameBacklogItem selectedGame = vgTable.getSelectionModel().getSelectedItem();
						createEditForm(editStage, selectedGame);
					} else {
						Alert alert = new Alert(AlertType.INFORMATION, "Select a row to edit.");
						alert.showAndWait();
					}

					break;
				default:
					System.out.println("Danger there be dragons in the Gap");

				}
			}
		});

		// set alignment & spacing
		bottomRow.setAlignment(Pos.CENTER);
		bottomRow.setSpacing(20);

		bottomRow.getChildren().addAll(addButton, deleteButton, editButton);

		vbox.getChildren().set(2, bottomRow);

	}

	private void createAddForm(Stage addStage) {
		addStage.setTitle("Add Media");
		HBox addButtons = new HBox();
		addButtons.setPadding(new Insets(20, 20, 20, 20));
		GridPane addPane = new GridPane();
		addPane.setAlignment(Pos.CENTER);
		addPane.setHgap(15);
		addPane.setVgap(15);
		addPane.setPadding(new Insets(0, 40, 40, 40));

		ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		columnOneConstraints.setHalignment(HPos.RIGHT);
		ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
		columnTwoConstrains.setHgrow(Priority.ALWAYS);
		addPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

		BorderPane addBox = new BorderPane();

		Scene addScene = new Scene(addBox, 700, 500);

		HBox labelBox = new HBox();
		Label pageTitle = new Label("Add New Media");
		pageTitle.setFont(new Font("Arial", 30));
		addBox.setTop(labelBox);
		labelBox.getChildren().add(pageTitle);
		labelBox.setPadding(new Insets(20, 20, 20, 0));
		labelBox.setAlignment(Pos.CENTER);

		// add labels for Title, Release Year, Genre, Rating, and Priority
		Label titleLabel = new Label("Title: ");
		TextField titleInput = new TextField();

		Label releaseLabel = new Label("Release Year: ");
		TextField releaseInput = new TextField();

		Label genreLabel = new Label("Genre: ");
		TextField genreInput = new TextField();

		Label statusLabel = new Label("Status: ");
		ObservableList<String> statuses;
		if (mediaTypeDisplayed.contentEquals("VideoGames")) {
			statuses = FXCollections.observableArrayList("To Play", "Playing", "On Hold", "Dropped", "Completed"

			);

		} else {
			statuses = FXCollections.observableArrayList("To Watch", "Watching", "On Hold", "Dropped", "Completed");
		}
		ComboBox<String> statusBox = new ComboBox<String>(statuses);

		Label ratingLabel = new Label("Rating(1-10): ");
		TextField ratingInput = new TextField();

		Label priorityLabel = new Label("Priority(Numeric): ");
		TextField priorityInput = new TextField();
		switch (this.mediaTypeDisplayed) {

		case "Anime":

			addPane.add(titleLabel, 0, 0);
			addPane.add(titleInput, 1, 0);
			addPane.add(genreLabel, 0, 1);
			addPane.add(genreInput, 1, 1);
			addPane.add(statusLabel, 0, 2);
			addPane.add(statusBox, 1, 2);
			addPane.add(ratingLabel, 0, 3);
			addPane.add(ratingInput, 1, 3);
			addPane.add(priorityLabel, 0, 4);
			addPane.add(priorityInput, 1, 4);

			break;
		default:

			addPane.add(titleLabel, 0, 0);
			addPane.add(titleInput, 1, 0);
			addPane.add(releaseLabel, 0, 1);
			addPane.add(releaseInput, 1, 1);
			addPane.add(genreLabel, 0, 2);
			addPane.add(genreInput, 1, 2);
			addPane.add(statusLabel, 0, 3);
			addPane.add(statusBox, 1, 3);
			addPane.add(ratingLabel, 0, 4);
			addPane.add(ratingInput, 1, 4);
			addPane.add(priorityLabel, 0, 5);
			addPane.add(priorityInput, 1, 5);

		}

		addBox.setCenter(addPane);

		// initialize Cancel button
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				addStage.close();
			}
		});
		cancelButton.setPrefWidth(100);
		cancelButton.setPrefHeight(50);
		Button addtoBacklogButton = new Button("Add");
		addtoBacklogButton.setPrefWidth(100);
		addtoBacklogButton.setPrefHeight(50);
		addtoBacklogButton.setOnAction(e -> {
			String title = titleInput.getText();
			String release = releaseInput.getText();
			String genre = genreInput.getText();
			String status = statusBox.getValue();
			String rating = ratingInput.getText();
			String priority = priorityInput.getText();
			VideoGameStatus vidEnum = null;
			WatchableMediaStatus watchEnum = null;
			try {
				if (mediaTypeDisplayed.equals("VideoGames")) {

					switch (status) {

					case "To Play":
						vidEnum = VideoGameStatus.TO_PLAY;
						break;
					case "Playing":
						vidEnum = VideoGameStatus.PLAYING;
						break;
					case "On Hold":
						vidEnum = VideoGameStatus.ON_HOLD;
						break;
					case "Dropped":
						vidEnum = VideoGameStatus.DROPPED;
						break;
					default:
						vidEnum = VideoGameStatus.COMPLETED;
					}

				} else {

					switch (status) {

					case "To Watch":
						watchEnum = WatchableMediaStatus.TO_WATCH;
						break;
					case "Watching":
						watchEnum = WatchableMediaStatus.WATCHING;
						break;
					case "On Hold":
						watchEnum = WatchableMediaStatus.ON_HOLD;
						break;
					case "Dropped":
						watchEnum = WatchableMediaStatus.DROPPED;
						break;
					default:
						watchEnum = WatchableMediaStatus.COMPLETED;
					}
				}
				// catch errors
			} catch (Exception exception) {
				Alert alert = new Alert(AlertType.INFORMATION, "Select a Status");
				alert.showAndWait();

				return;
			}
			if (!release.matches("\\d{4}") && !(release.equals(""))) {
				Alert alert = new Alert(AlertType.INFORMATION, "Please enter a valid year for the release");
				alert.showAndWait();

				return;

			} else if (!(rating.equals("")) && !(rating.equals("10.0")) && !rating.matches("[0-9][.][0-9]{0,2}")) {

				Alert alert = new Alert(AlertType.INFORMATION, "Please enter a valid rating (0-10). 2 decimal places");
				alert.showAndWait();

				return;
			} else if (!priority.matches("-?[0-9]*")) {
				Alert alert = new Alert(AlertType.INFORMATION, "Please enter a valid integer for priority");
				alert.showAndWait();

				return;

			}

			int releaseInt;
			if (release.equals("")) {
				releaseInt = -1;
			} else {
				releaseInt = Integer.parseInt(release);
			}

			// determine which table should have media added to it based on currently
			// selected media type
			switch (mediaTypeDisplayed) {
			case "TVShows":
				TVShowDatabase tvdb = new TVShowDatabase();
				TVShowBacklog tvbdb = new TVShowBacklog();
				int tvid = tvdb.getMaxID() + 1;
				TVShow TVtoAdd = new TVShow(title, genre, rating, releaseInt, tvid, " ");
				TVShowBacklogItem TVBackToAdd = new TVShowBacklogItem(tvid, title, genre, "", rating,
						Integer.parseInt(priority));
				tvdb.Insert(TVtoAdd);
				tvbdb.Insert(TVBackToAdd, watchEnum, rating, Integer.parseInt(priority));
				vbox.getChildren().set(1, loadTVTable());

				break;
			case "Movies":
				MovieDatabase mvdb = new MovieDatabase();
				MovieBacklog mvbdb = new MovieBacklog();
				int mid = mvdb.getMaxID() + 1;
				Movie MovietoAdd = new Movie(title, genre, rating, releaseInt, mid, " ");
				MovieBacklogItem MovieBackToAdd = new MovieBacklogItem(mid, title, genre, "", rating,
						Integer.parseInt(priority));
				mvdb.Insert(MovietoAdd);
				mvbdb.Insert(MovieBackToAdd, watchEnum, rating, Integer.parseInt(priority));
				vbox.getChildren().set(1, loadMovieTable());
				break;
			case "Anime":
				AnimeDatabase adb = new AnimeDatabase();
				AnimeBacklog abdb = new AnimeBacklog();
				int aid = adb.getMaxID() + 1;
				Anime AnimetoAdd = new Anime(title, genre, rating, aid);
				AnimeBacklogItem AnimeBackToAdd = new AnimeBacklogItem(aid, title, genre, "", rating,
						Integer.parseInt(priority));
				adb.Insert(AnimetoAdd);
				abdb.Insert(AnimeBackToAdd, watchEnum, rating, Integer.parseInt(priority));
				vbox.getChildren().set(1, loadAnimeTable());
				break;
			case "VideoGames":
				VideoGameDatabase vgdb = new VideoGameDatabase();
				VideoGameBacklog vgbdb = new VideoGameBacklog();
				int vgid = vgdb.getMaxID() + 1;
				VideoGame VGtoAdd = new VideoGame(title, genre, rating, releaseInt, vgid, " ");
				VideoGameBacklogItem VGBackToAdd = new VideoGameBacklogItem(vgid, title, genre, "", rating,
						Integer.parseInt(priority));
				vgdb.Insert(VGtoAdd);
				vgbdb.Insert(VGBackToAdd, vidEnum, rating, Integer.parseInt(priority));
				vbox.getChildren().set(1, loadTVTable());
				break;
			default:
				System.out.println("Danger there be dragons in the Gap");

			}

			addStage.close();
		});

		addButtons.setAlignment(Pos.CENTER);
		addButtons.setSpacing(20);

		addButtons.getChildren().addAll(addtoBacklogButton, cancelButton);
		addBox.setBottom(addButtons);

		addStage.setScene(addScene);
		addStage.show();

	}

	private void createEditForm(Stage editStage, Object media) {
		TVShowBacklogItem selectedShow = null;
		MovieBacklogItem selectedMovie = null;
		AnimeBacklogItem selectedAnime = null;
		VideoGameBacklogItem selectedGame = null;
		// determine which media type is selected, create media for
		switch (mediaTypeDisplayed) {
		case "TVShows":
			selectedShow = (TVShowBacklogItem) media;
			break;
		case "Movies":
			selectedMovie = (MovieBacklogItem) media;
			break;
		case "Anime":
			selectedAnime = (AnimeBacklogItem) media;
			break;
		case "VideoGames":
			selectedGame = (VideoGameBacklogItem) media;
			break;
		default:
			System.out.println("Danger there be dragons in the Gap");

		}

		// initialize edit media box
		editStage.setTitle("Edit Media");
		HBox addButtons = new HBox();
		addButtons.setPadding(new Insets(20, 20, 20, 20));
		GridPane addPane = new GridPane();
		addPane.setAlignment(Pos.CENTER);
		addPane.setHgap(15);
		addPane.setVgap(15);
		addPane.setPadding(new Insets(0, 40, 40, 40));

		ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		columnOneConstraints.setHalignment(HPos.RIGHT);
		ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
		columnTwoConstrains.setHgrow(Priority.ALWAYS);
		addPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

		BorderPane addBox = new BorderPane();

		Scene addScene = new Scene(addBox, 700, 500);

		HBox labelBox = new HBox();
		Label pageTitle = new Label("Edit Media Backlog");
		pageTitle.setFont(new Font("Arial", 30));
		addBox.setTop(labelBox);
		labelBox.getChildren().add(pageTitle);
		labelBox.setPadding(new Insets(20, 20, 20, 0));
		labelBox.setAlignment(Pos.CENTER);

		Label titleLabel = new Label("Title: ");
		TextField titleInput = new TextField();
		titleInput.setDisable(true);

		Label statusLabel = new Label("Status: ");
		ObservableList<String> statuses;

		if (mediaTypeDisplayed.contentEquals("VideoGames")) {
			statuses = FXCollections.observableArrayList("To Play", "Playing", "On Hold", "Dropped", "Completed"

			);

		} else {
			statuses = FXCollections.observableArrayList("To Watch", "Watching", "On Hold", "Dropped", "Completed");
		}
		ComboBox<String> statusBox = new ComboBox<String>(statuses);

		Label ratingLabel = new Label("Rating(1-10): ");
		TextField ratingInput = new TextField();

		Label priorityLabel = new Label("Priority(Numeric): ");
		TextField priorityInput = new TextField();

		// determine what the Title, Status, Rating, and Priority should say based on
		// currently selected media
		switch (this.mediaTypeDisplayed) {
		case "TVShows":
			titleInput.setText(selectedShow.getTitle());
			statusBox.setValue(selectedShow.getStatus());
			ratingInput.setText(selectedShow.getRating());
			priorityInput.setText(Integer.toString(selectedShow.getPriority()));
			break;

		case "Movies":
			titleInput.setText(selectedMovie.getTitle());
			statusBox.setValue(selectedMovie.getStatus());
			ratingInput.setText(selectedMovie.getRating());
			priorityInput.setText(Integer.toString(selectedMovie.getPriority()));
			break;
		case "Anime":
			titleInput.setText(selectedAnime.getTitle());
			statusBox.setValue(selectedAnime.getStatus());
			ratingInput.setText(selectedAnime.getRating());
			priorityInput.setText(Integer.toString(selectedAnime.getPriority()));
			break;
		case "VieoGames":
			titleInput.setText(selectedGame.getTitle());
			statusBox.setValue(selectedGame.getStatus());
			ratingInput.setText(selectedGame.getRating());
			priorityInput.setText(Integer.toString(selectedGame.getPriority()));
			break;
		default:

		}

		addPane.add(titleLabel, 0, 0);
		addPane.add(titleInput, 1, 0);
		addPane.add(statusLabel, 0, 2);
		addPane.add(statusBox, 1, 2);
		addPane.add(ratingLabel, 0, 3);
		addPane.add(ratingInput, 1, 3);
		addPane.add(priorityLabel, 0, 4);
		addPane.add(priorityInput, 1, 4);

		addBox.setCenter(addPane);

		// initialize cancel button
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				editStage.close();
			}
		});
		cancelButton.setPrefWidth(100);
		cancelButton.setPrefHeight(50);

		// initialize edit button
		Button editBacklogButton = new Button("Edit");
		editBacklogButton.setPrefWidth(100);
		editBacklogButton.setPrefHeight(50);
		final TVShowBacklogItem selectedShowFinal = selectedShow;
		final MovieBacklogItem selectedMovieFinal = selectedMovie;
		final AnimeBacklogItem selectedAnimeFinal = selectedAnime;
		final VideoGameBacklogItem selectedGameFinal = selectedGame;
		editBacklogButton.setOnAction(e -> {
			String status = statusBox.getValue();
			String rating = ratingInput.getText();
			String priority = priorityInput.getText();

			if (!(rating.equals("")) && !(rating.equals("10.0")) && !rating.matches("[0-9][.][0-9]{0,2}")) {

				Alert alert = new Alert(AlertType.INFORMATION, "Please enter a valid rating (0-10). 2 decimal places");
				alert.showAndWait();

				return;
			} else if (!priority.matches("-?[0-9]*")) {
				Alert alert = new Alert(AlertType.INFORMATION, "Please enter a valid integer for priority");
				alert.showAndWait();

				return;

			}

			// update the currently selected media in the database based on the currently
			// selected media type
			int priorityInt = Integer.parseInt(priority);
			switch (mediaTypeDisplayed) {
			case "TVShows":
				TVShowBacklog tvbdb = new TVShowBacklog();
				TVShowBacklogItem updateShow = new TVShowBacklogItem(selectedShowFinal.getID(),
						selectedShowFinal.getTitle(), selectedShowFinal.getGenre(), status, rating, priorityInt);
				tvbdb.Update(updateShow);
				vbox.getChildren().set(1, loadTVTable());

				break;
			case "Movies":

				MovieBacklog mvbdb = new MovieBacklog();
				MovieBacklogItem updateMovie = new MovieBacklogItem(selectedMovieFinal.getID(),
						selectedMovieFinal.getTitle(), selectedMovieFinal.getGenre(), status, rating, priorityInt);
				mvbdb.Update(updateMovie);

				vbox.getChildren().set(1, loadMovieTable());
				break;
			case "Anime":
				AnimeBacklog abdb = new AnimeBacklog();
				AnimeBacklogItem updateAnime = new AnimeBacklogItem(selectedAnimeFinal.getID(),
						selectedAnimeFinal.getTitle(), selectedAnimeFinal.getGenre(), status, rating, priorityInt);

				abdb.Update(updateAnime);
				vbox.getChildren().set(1, loadAnimeTable());
				break;
			case "VideoGames":
				VideoGameBacklog vgbdb = new VideoGameBacklog();
				VideoGameBacklogItem updateGame = new VideoGameBacklogItem(selectedGameFinal.getID(),
						selectedGameFinal.getTitle(), selectedGameFinal.getGenre(), status, rating, priorityInt);

				vgbdb.Update(updateGame);
				vbox.getChildren().set(1, loadVideoGameTable());
				break;
			default:
				System.out.println("Danger there be dragons in the Gap");

			}

			editStage.close();
		});

		addButtons.setAlignment(Pos.CENTER);
		addButtons.setSpacing(20);

		addButtons.getChildren().addAll(editBacklogButton, cancelButton);
		addBox.setBottom(addButtons);

		editStage.setScene(addScene);
		editStage.show();

	}

	public void createAddRecommendedStage(Stage recStage, Object media) {
		TVShow selectedShow = null;
		Movie selectedMovie = null;
		Anime selectedAnime = null;
		VideoGame selectedGame = null;
		switch (mediaTypeDisplayed) {
		case "TVShows":
			selectedShow = (TVShow) media;
			break;
		case "Movies":
			selectedMovie = (Movie) media;
			break;
		case "Anime":
			selectedAnime = (Anime) media;
			break;
		case "VideoGames":
			selectedGame = (VideoGame) media;
			break;
		default:
			System.out.println("Danger there be dragons in the Gap");

		}

		// initialize add recommendation box
		recStage.setTitle("Add Recommendation");
		HBox addButtons = new HBox();
		addButtons.setPadding(new Insets(20, 20, 20, 20));
		GridPane addPane = new GridPane();
		addPane.setAlignment(Pos.CENTER);
		addPane.setHgap(15);
		addPane.setVgap(15);
		addPane.setPadding(new Insets(0, 40, 40, 40));

		ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		columnOneConstraints.setHalignment(HPos.RIGHT);
		ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
		columnTwoConstrains.setHgrow(Priority.ALWAYS);
		addPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

		BorderPane addBox = new BorderPane();

		Scene addScene = new Scene(addBox, 700, 500);

		HBox labelBox = new HBox();
		Label pageTitle = new Label("Add Recommended Media");
		pageTitle.setFont(new Font("Arial", 30));
		addBox.setTop(labelBox);
		labelBox.getChildren().add(pageTitle);
		labelBox.setPadding(new Insets(20, 20, 20, 0));
		labelBox.setAlignment(Pos.CENTER);

		Label titleLabel = new Label("Title: ");
		TextField titleInput = new TextField();
		titleInput.setDisable(true);

		switch (this.mediaTypeDisplayed) {
		case "TVShows":
			titleInput.setText(selectedShow.getTitle());
			break;
		case "Movies":
			titleInput.setText(selectedMovie.getTitle());
			break;
		case "Anime":
			titleInput.setText(selectedAnime.getTitle());
			break;
		case "VideoGames":
			titleInput.setText(selectedGame.getTitle());
			break;
		default:

		}

		Label statusLabel = new Label("Status: ");
		ObservableList<String> statuses;

		if (mediaTypeDisplayed.contentEquals("VideoGames")) {
			statuses = FXCollections.observableArrayList("To Play", "Playing", "On Hold", "Dropped", "Completed"

			);

		} else {
			statuses = FXCollections.observableArrayList("To Watch", "Watching", "On Hold", "Dropped", "Completed");
		}
		ComboBox<String> statusBox = new ComboBox<String>(statuses);

		Label ratingLabel = new Label("Rating(1-10): ");
		TextField ratingInput = new TextField();

		Label priorityLabel = new Label("Priority(Numeric): ");
		TextField priorityInput = new TextField();

		addPane.add(titleLabel, 0, 0);
		addPane.add(titleInput, 1, 0);
		addPane.add(statusLabel, 0, 2);
		addPane.add(statusBox, 1, 2);
		addPane.add(ratingLabel, 0, 3);
		addPane.add(ratingInput, 1, 3);
		addPane.add(priorityLabel, 0, 4);
		addPane.add(priorityInput, 1, 4);

		addBox.setCenter(addPane);

		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				recStage.close();
			}
		});
		cancelButton.setPrefWidth(100);
		cancelButton.setPrefHeight(50);

		// initialize add button
		Button addRecommended = new Button("Add");
		addRecommended.setPrefWidth(100);
		addRecommended.setPrefHeight(50);
		final TVShow selectedShowFinal = selectedShow;
		final Movie selectedMovieFinal = selectedMovie;
		final Anime selectedAnimeFinal = selectedAnime;
		final VideoGame selectedGameFinal = selectedGame;
		addRecommended.setOnAction(e -> {
			String status = statusBox.getValue();
			String rating = ratingInput.getText();
			String priority = priorityInput.getText();
			VideoGameStatus vidEnum = null;
			WatchableMediaStatus watchEnum = null;
			try {
				if (mediaTypeDisplayed.equals("VideoGames")) {

					switch (status) {

					case "To Play":
						vidEnum = VideoGameStatus.TO_PLAY;
						break;
					case "Playing":
						vidEnum = VideoGameStatus.PLAYING;
						break;
					case "On Hold":
						vidEnum = VideoGameStatus.ON_HOLD;
						break;
					case "Dropped":
						vidEnum = VideoGameStatus.DROPPED;
						break;
					default:
						vidEnum = VideoGameStatus.COMPLETED;
					}

				} else {

					switch (status) {

					case "To Watch":
						watchEnum = WatchableMediaStatus.TO_WATCH;
						break;
					case "Watching":
						watchEnum = WatchableMediaStatus.WATCHING;
						break;
					case "On Hold":
						watchEnum = WatchableMediaStatus.ON_HOLD;
						break;
					case "Dropped":
						watchEnum = WatchableMediaStatus.DROPPED;
						break;
					default:
						watchEnum = WatchableMediaStatus.COMPLETED;
					}
				}
			} catch (Exception exception) {
				Alert alert = new Alert(AlertType.INFORMATION, "Select a Status");
				alert.showAndWait();

				return;
			}

			if (!(rating.equals("")) && !(rating.equals("10.0")) && !rating.matches("[0-9][.][0-9]{0,2}")) {

				Alert alert = new Alert(AlertType.INFORMATION, "Please enter a valid rating (0-10). 2 decimal places");
				alert.showAndWait();

				return;
			} else if (!priority.matches("-?[0-9]*")) {
				Alert alert = new Alert(AlertType.INFORMATION, "Please enter a valid integer for priority");
				alert.showAndWait();

				return;

			}

			int priorityInt = Integer.parseInt(priority);
			switch (mediaTypeDisplayed) {
			case "TVShows":
				TVShowBacklog tvbdb = new TVShowBacklog();
				TVShowBacklogItem updateShow = new TVShowBacklogItem(selectedShowFinal.getID(),
						selectedShowFinal.getTitle(), selectedShowFinal.getGenre(), status, rating, priorityInt);
				tvbdb.Insert(updateShow, watchEnum, rating, priorityInt);

				break;
			case "Movies":

				MovieBacklog mvbdb = new MovieBacklog();
				MovieBacklogItem updateMovie = new MovieBacklogItem(selectedMovieFinal.getID(),
						selectedMovieFinal.getTitle(), selectedMovieFinal.getGenre(), status, rating, priorityInt);
				mvbdb.Insert(updateMovie, watchEnum, rating, priorityInt);

				break;
			case "Anime":
				AnimeBacklog abdb = new AnimeBacklog();
				AnimeBacklogItem updateAnime = new AnimeBacklogItem(selectedAnimeFinal.getID(),
						selectedAnimeFinal.getTitle(), selectedAnimeFinal.getGenre(), status, rating, priorityInt);

				abdb.Insert(updateAnime, watchEnum, rating, priorityInt);
				break;
			case "VideoGames":
				VideoGameBacklog vgbdb = new VideoGameBacklog();
				VideoGameBacklogItem updateGame = new VideoGameBacklogItem(selectedGameFinal.getID(),
						selectedGameFinal.getTitle(), selectedGameFinal.getGenre(), status, rating, priorityInt);

				vgbdb.Insert(updateGame, vidEnum, rating, priorityInt);
				break;
			default:
				System.out.println("Danger there be dragons in the Gap");

			}

			recStage.close();
		});

		addButtons.setAlignment(Pos.CENTER);
		addButtons.setSpacing(20);

		addButtons.getChildren().addAll(addRecommended, cancelButton);
		addBox.setBottom(addButtons);

		recStage.setScene(addScene);
		recStage.show();

	}

	public void createAddSearchedStage(Stage recStage, Object media) {
		TVShow selectedShow = null;
		Movie selectedMovie = null;
		Anime selectedAnime = null;
		VideoGame selectedGame = null;
		switch (mediaTypeDisplayed) {
		case "TVShows":
			selectedShow = (TVShow) media;
			break;
		case "Movies":
			selectedMovie = (Movie) media;
			break;
		case "Anime":
			selectedAnime = (Anime) media;
			break;
		case "VideoGames":
			selectedGame = (VideoGame) media;
			break;
		default:
			System.out.println("Danger there be dragons in the Gap");

		}

		// initialize add searched media
		recStage.setTitle("Add Searched Media");
		HBox addButtons = new HBox();
		addButtons.setPadding(new Insets(20, 20, 20, 20));
		GridPane addPane = new GridPane();
		addPane.setAlignment(Pos.CENTER);
		addPane.setHgap(15);
		addPane.setVgap(15);
		addPane.setPadding(new Insets(0, 40, 40, 40));

		ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		columnOneConstraints.setHalignment(HPos.RIGHT);
		ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
		columnTwoConstrains.setHgrow(Priority.ALWAYS);
		addPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

		BorderPane addBox = new BorderPane();

		Scene addScene = new Scene(addBox, 700, 500);

		HBox labelBox = new HBox();
		Label pageTitle = new Label("Add Searched Media");
		pageTitle.setFont(new Font("Arial", 30));
		addBox.setTop(labelBox);
		labelBox.getChildren().add(pageTitle);
		labelBox.setPadding(new Insets(20, 20, 20, 0));
		labelBox.setAlignment(Pos.CENTER);

		Label titleLabel = new Label("Title: ");
		TextField titleInput = new TextField();
		titleInput.setDisable(true);

		switch (this.mediaTypeDisplayed) {
		case "TVShows":
			titleInput.setText(selectedShow.getTitle());
			break;
		case "Movies":
			titleInput.setText(selectedMovie.getTitle());
			break;
		case "Anime":
			titleInput.setText(selectedAnime.getTitle());
			break;
		case "VideoGames":
			titleInput.setText(selectedGame.getTitle());
			break;
		default:

		}

		Label statusLabel = new Label("Status: ");
		ObservableList<String> statuses;

		if (mediaTypeDisplayed.contentEquals("VideoGames")) {
			statuses = FXCollections.observableArrayList("To Play", "Playing", "On Hold", "Dropped", "Completed"

			);

		} else {
			statuses = FXCollections.observableArrayList("To Watch", "Watching", "On Hold", "Dropped", "Completed");
		}
		ComboBox<String> statusBox = new ComboBox<String>(statuses);

		Label ratingLabel = new Label("Rating(1-10): ");
		TextField ratingInput = new TextField();

		Label priorityLabel = new Label("Priority(Numeric): ");
		TextField priorityInput = new TextField();

		addPane.add(titleLabel, 0, 0);
		addPane.add(titleInput, 1, 0);
		addPane.add(statusLabel, 0, 2);
		addPane.add(statusBox, 1, 2);
		addPane.add(ratingLabel, 0, 3);
		addPane.add(ratingInput, 1, 3);
		addPane.add(priorityLabel, 0, 4);
		addPane.add(priorityInput, 1, 4);

		addBox.setCenter(addPane);

		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				recStage.close();
			}
		});
		cancelButton.setPrefWidth(100);
		cancelButton.setPrefHeight(50);

		Button addRecommended = new Button("Add");
		addRecommended.setPrefWidth(100);
		addRecommended.setPrefHeight(50);
		final TVShow selectedShowFinal = selectedShow;
		final Movie selectedMovieFinal = selectedMovie;
		final Anime selectedAnimeFinal = selectedAnime;
		final VideoGame selectedGameFinal = selectedGame;
		addRecommended.setOnAction(e -> {
			String status = statusBox.getValue();
			String rating = ratingInput.getText();
			String priority = priorityInput.getText();
			VideoGameStatus vidEnum = null;
			WatchableMediaStatus watchEnum = null;
			try {
				if (mediaTypeDisplayed.equals("VideoGames")) {

					switch (status) {

					case "To Play":
						vidEnum = VideoGameStatus.TO_PLAY;
						break;
					case "Playing":
						vidEnum = VideoGameStatus.PLAYING;
						break;
					case "On Hold":
						vidEnum = VideoGameStatus.ON_HOLD;
						break;
					case "Dropped":
						vidEnum = VideoGameStatus.DROPPED;
						break;
					default:
						vidEnum = VideoGameStatus.COMPLETED;
					}

				} else {

					switch (status) {

					case "To Watch":
						watchEnum = WatchableMediaStatus.TO_WATCH;
						break;
					case "Watching":
						watchEnum = WatchableMediaStatus.WATCHING;
						break;
					case "On Hold":
						watchEnum = WatchableMediaStatus.ON_HOLD;
						break;
					case "Dropped":
						watchEnum = WatchableMediaStatus.DROPPED;
						break;
					default:
						watchEnum = WatchableMediaStatus.COMPLETED;
					}
				}
			} catch (Exception exception) {
				Alert alert = new Alert(AlertType.INFORMATION, "Select a Status");
				alert.showAndWait();

				return;
			}

			if (!(rating.equals("")) && !(rating.equals("10.0")) && !rating.matches("[0-9][.][0-9]{0,2}")) {

				Alert alert = new Alert(AlertType.INFORMATION, "Please enter a valid rating (0-10). 2 decimal places");
				alert.showAndWait();

				return;
			} else if (!priority.matches("-?[0-9]*")) {
				Alert alert = new Alert(AlertType.INFORMATION, "Please enter a valid integer for priority");
				alert.showAndWait();

				return;

			}

			int priorityInt = Integer.parseInt(priority);
			switch (mediaTypeDisplayed) {
			case "TVShows":
				TVShowBacklog tvbdb = new TVShowBacklog();
				TVShowBacklogItem updateShow = new TVShowBacklogItem(selectedShowFinal.getID(),
						selectedShowFinal.getTitle(), selectedShowFinal.getGenre(), status, rating, priorityInt);
				tvbdb.Insert(updateShow, watchEnum, rating, priorityInt);

				break;
			case "Movies":

				MovieBacklog mvbdb = new MovieBacklog();
				MovieBacklogItem updateMovie = new MovieBacklogItem(selectedMovieFinal.getID(),
						selectedMovieFinal.getTitle(), selectedMovieFinal.getGenre(), status, rating, priorityInt);
				mvbdb.Insert(updateMovie, watchEnum, rating, priorityInt);

				break;
			case "Anime":
				AnimeBacklog abdb = new AnimeBacklog();
				AnimeBacklogItem updateAnime = new AnimeBacklogItem(selectedAnimeFinal.getID(),
						selectedAnimeFinal.getTitle(), selectedAnimeFinal.getGenre(), status, rating, priorityInt);

				abdb.Insert(updateAnime, watchEnum, rating, priorityInt);
				break;
			case "VideoGames":
				VideoGameBacklog vgbdb = new VideoGameBacklog();
				VideoGameBacklogItem updateGame = new VideoGameBacklogItem(selectedGameFinal.getID(),
						selectedGameFinal.getTitle(), selectedGameFinal.getGenre(), status, rating, priorityInt);

				vgbdb.Insert(updateGame, vidEnum, rating, priorityInt);
				break;
			default:
				System.out.println("Danger there be dragons in the Gap");

			}

			recStage.close();
		});

		addButtons.setAlignment(Pos.CENTER);
		addButtons.setSpacing(20);

		addButtons.getChildren().addAll(addRecommended, cancelButton);
		addBox.setBottom(addButtons);

		recStage.setScene(addScene);
		recStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
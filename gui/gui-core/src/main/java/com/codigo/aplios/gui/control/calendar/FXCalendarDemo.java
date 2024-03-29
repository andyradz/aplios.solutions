package com.codigo.aplios.gui.control.calendar;

import java.util.Locale;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXCalendarDemo extends Application {

	private Stage		stage;
	private Scene		scene;
	private BorderPane	root;
	private VBox		center;

	public static void main(final String[] args) {

		Application.launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {

		this.stage = stage;
		configureScene();
		configureStage();
		configureHeader();
		configureFooter();
		configureCenter();

		int i = 1;
		configureSimpleDate(i++);
		configureDefaultDate(i++);
		configureWeekNumber(i++);
		configureLocaleCalendar(i++);
		configureCalendarTheme(i);

	}

	private void configureStage() {

		this.stage.setTitle("FX Calendar Demo");
		this.stage.setX(0);
		this.stage.setY(0);
		this.stage.setWidth(Screen.getPrimary()
				.getVisualBounds()
				.getWidth());
		this.stage.setHeight(Screen.getPrimary()
				.getVisualBounds()
				.getHeight());
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	private void configureScene() {

		this.root = new BorderPane();
		this.root.autosize();
		this.scene = new Scene(
			this.root, Color.LINEN);
		loadStyleSheet(this.scene);
	}

	private final void loadStyleSheet(final Scene scene) {

		scene.getStylesheets()
				.add(FXCalendarDemo.class
						.getResource("/com/codigo/aplios/gui/control/calendar/styles/calendar_styles.css")
						.toExternalForm());
	}

	private void configureHeader() {

		final StackPane sp = new StackPane();
		sp.setPrefHeight(100);
		sp.setAlignment(Pos.TOP_LEFT);
		sp.setStyle(
				"-fx-background-color: linear-gradient(to bottom, #7A7A7A 0%, #333333 100%);-fx-opacity:.8;-fx-border-width: 0 0 2px 0;-fx-border-color: #868686;");

		final Label header = new Label(
			"JavaFX 2 .0 Calendar");
		header.setTextFill(Color.BEIGE);
		header.setTranslateX(10);
		header.setStyle("-fx-font-size:40;");
		header.setTranslateY(25);

		sp.getChildren()
				.addAll(header);
		this.root.setTop(sp);
	}

	private void configureFooter() {

		final StackPane sp = new StackPane();
		sp.setPrefHeight(20);
		sp.setAlignment(Pos.CENTER);
		sp.setStyle(
				"-fx-background-color: linear-gradient(to bottom, #7A7A7A 0%, #333333 100%);-fx-opacity:.8;-fx-border-width: 2px 0 0 0;-fx-border-color: #6D6B69;");

		this.root.setBottom(sp);
	}

	private void configureCenter() {

		final ScrollPane sp = new ScrollPane();
		sp.getStyleClass()
				.add("centerBG");
		this.center = new VBox();
		this.center.setPadding(new Insets(
			10));
		this.center.setSpacing(25);

		sp.setContent(this.center);
		this.root.setCenter(sp);
	}

	private void configureSimpleDate(final int i) {

		final VBox vb = new VBox();
		vb.setSpacing(10);
		final FeatureHeader header = new FeatureHeader(
			"#" + i + " : Simple Calendar Control");
		final FeatureLabel lbl = new FeatureLabel(
			"Select the date : ");

		final HBox hb = new HBox();
		hb.setSpacing(10);
		hb.getChildren()
				.addAll(lbl, new FXCalendar());

		vb.getChildren()
				.addAll(header, hb);
		this.center.getChildren()
				.add(vb);
	}

	private void configureDefaultDate(final int i) {

		final VBox vb = new VBox();
		vb.setSpacing(10);
		final FeatureHeader header = new FeatureHeader(
			"#" + i + " : Calendar Control with Default date and Custom width");
		final FeatureLabel lbl = new FeatureLabel(
			"Select the date : ");
		final FXCalendar calendar = new FXCalendar();
		calendar.setValue(new FXCalendarUtility().convertStringtoDate("02/02/2001"));

		final HBox hb = new HBox();
		hb.setSpacing(10);
		hb.getChildren()
				.addAll(lbl, calendar);

		final FeatureLabel lbl2 = new FeatureLabel(
			"");
		final Button btn = new Button(
			"Show Selected date");
		btn.setOnAction(arg0 -> lbl2.setText(calendar.getValue()
				.toString()));

		final HBox hb1 = new HBox();
		hb1.setSpacing(10);
		hb1.getChildren()
				.addAll(btn, lbl2);

		vb.getChildren()
				.addAll(header, hb, hb1);
		this.center.getChildren()
				.add(vb);
	}

	private void configureWeekNumber(final int i) {

		final VBox vb = new VBox();
		vb.setSpacing(10);
		final FeatureHeader header = new FeatureHeader(
			"#" + i + " : Calendar Control with Week Number display");
		final FeatureLabel lbl = new FeatureLabel(
			"Select the date : ");
		final FXCalendar calendar = new FXCalendar();
		calendar.setShowWeekNumber(true);
		calendar.setValue(new FXCalendarUtility().convertStringtoDate("02/02/0001"));
		final HBox hb = new HBox();
		hb.setSpacing(10);
		hb.getChildren()
				.addAll(lbl, calendar);

		vb.getChildren()
				.addAll(header, hb);
		this.center.getChildren()
				.add(vb);
	}

	private void configureLocaleCalendar(final int i) {

		final VBox vb = new VBox();
		vb.setSpacing(10);
		final FeatureHeader header = new FeatureHeader(
			"#" + i + " : Calendar Control with Locale specific");
		final FeatureLabel lbl1 = new FeatureLabel(
			"Select the language : ");
		final FeatureLabel lbl2 = new FeatureLabel(
			"Select the date : ");
		final FXCalendar calendar = new FXCalendar();
		calendar.setLocale(Locale.ENGLISH);

		final ObservableList<String> list = FXCollections.observableArrayList("English", "French", "Dutch", "Polish");
		final ChoiceBox<String> cb = new ChoiceBox<>(
			list);
		cb.getSelectionModel()
				.select(0);
		cb.getSelectionModel()
				.selectedIndexProperty()
				.addListener((ChangeListener<Number>) (arg0, arg1, arg2) -> {

					if (arg2.intValue() == 0)
						calendar.setLocale(Locale.ENGLISH);
					else if (arg2.intValue() == 1)
						calendar.setLocale(Locale.FRENCH);
					else if (arg2.intValue() == 3)
						calendar.setLocale(new Locale(
							"pl"));
					else
						calendar.setLocale(new Locale(
							"nl", "BE"));
				});
		final HBox hb = new HBox();
		hb.setSpacing(10);
		hb.getChildren()
				.addAll(lbl1, cb);

		final HBox hb1 = new HBox();
		hb1.setSpacing(10);
		hb1.getChildren()
				.addAll(lbl2, calendar);

		vb.getChildren()
				.addAll(header, hb, hb1);
		this.center.getChildren()
				.add(vb);
	}

	private void configureCalendarTheme(final int i) {

		final VBox vb = new VBox();
		vb.setSpacing(10);
		final FeatureHeader header = new FeatureHeader(
			"#" + i + " : Calendar Control with different Themes");

		final FeatureLabel lbl1 = new FeatureLabel(
			"Select the language ( Red Theme ): ");
		final FXCalendar calendar1 = new FXCalendar();
		calendar1.setBaseColor(Color.web("#940C02"));

		final HBox hb = new HBox();
		hb.setSpacing(10);
		hb.getChildren()
				.addAll(lbl1, calendar1);

		final FeatureLabel lbl2 = new FeatureLabel(
			"Select the date ( Black Theme ) : ");
		final FXCalendar calendar2 = new FXCalendar();
		calendar2.setBaseColor(Color.BLACK);

		final HBox hb1 = new HBox();
		hb1.setSpacing(10);
		hb1.getChildren()
				.addAll(lbl2, calendar2);

		vb.getChildren()
				.addAll(header, hb, hb1);
		this.center.getChildren()
				.add(vb);
	}

}

class FeatureHeader extends Label {
	public FeatureHeader(final String str) {

		super(str);
		setStyle(
				"-fx-font-weight :bold;-fx-font-size: 16px;-fx-font-family: verdana,arial,helvetica,tahoma,sans-serif;");
	}
}

class FeatureLabel extends Label {
	public FeatureLabel(final String str) {

		super(str);
		setStyle("-fx-font-size: 12px;-fx-font-family: verdana,arial,helvetica,tahoma,sans-serif;");
	}
}

/*
 * http://www.anglozof.com/angielski/questions-pytania.htm Inwersja zadawania pytań po angielsku. to
 * to have
 *
 * W czasie Present Simple - Have you got time? - Is She at home? - Has She got a house? - Are they
 * start? - Am I tall?
 *
 * W czasie Past Simple - Was he in New York last year? -Were tkay at school yester? - Had he really
 * a job when he was 16?
 *
 * W czasie Present Continous to be jako czasownik poiłkowy Is she commingto the party?
 *
 * W czasie Present perfect -Have you completed the assignment yet?
 *
 * Czasowniki modalne will, should, would, may,
 *
 * Will you go there in the summer?
 *
 * Should I go there and talk to him?
 *
 * May I open window?
 *
 * Can you swim?
 *
 * Would you like some ice cream
 *
 * Czasownik posiłkowy DO, DOES, DID
 *
 * Present Simple Do you like me?
 *
 * Do you like us?
 *
 * Does she do her homework ever day?
 *
 * Past Simple
 *
 * Did they see you at this game?
 *
 * Did he pack his suitcase?
 *
 * Who came to the party?
 *
 * Which book is yours?
 *
 * Who won the game?
 *
 * Pytania Question tags
 *
 * You are very cleaver, arent you? This is not going to happen, is it?
 *
 * She is not coming, is she?
 */

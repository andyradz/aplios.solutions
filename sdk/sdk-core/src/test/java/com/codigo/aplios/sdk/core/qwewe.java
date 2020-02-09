package com.codigo.aplios.sdk.core;

/***
 * // Java 8 code public class JavaFXTrayIconSample extends Application {
 *
 * // one icon location is shared between the application tray icon and task bar
 * icon. // you could also use multiple icons to allow for clean display of tray
 * icons on hi-dpi devices. private static final String iconImageLoc =
 * "file:/d:/GameCenter-icon.png";
 *
 * // application stage is stored so that it can be shown and hidden based on
 * system tray icon // operations. private Stage stage;
 *
 * // a timer allowing the tray icon to provide a periodic notification event.
 * private final Timer notificationTimer = new Timer();
 *
 * // format used to display the current time in a tray icon notification.
 * private final DateFormat timeFormat = SimpleDateFormat.getTimeInstance();
 *
 * // sets up the javafx application. // a tray icon is setup for the icon, but
 * the main stage remains invisible until the user // interacts with the tray
 * icon.
 *
 * @Override public void start(final Stage stage) {
 *
 *           // stores a reference to the stage. this.stage = stage;
 *
 *           // instructs the javafx system not to exit implicitly when the last
 *           application window is shut. Platform.setImplicitExit(false);
 *
 *           // sets up the tray icon (using awt code run on the swing thread).
 *           javax.swing.SwingUtilities.invokeLater(this::addAppToTray);
 *
 *           // out stage will be translucent, so give it a transparent style.
 *           stage.initStyle(StageStyle.TRANSPARENT);
 *
 *           // create the layout for the javafx stage. final StackPane layout =
 *           new StackPane(this.createContent());
 *           layout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
 *           layout.setPrefSize(300, 200);
 *
 *           // this dummy app just hides itself when the app screen is clicked.
 *           // a real app might have some interactive UI and a separate icon
 *           which hides the app window. layout.setOnMouseClicked(event ->
 *           stage.hide());
 *
 *           // a scene with a transparent fill is necessary to implement the
 *           translucent app window. final Scene scene = new Scene(layout);
 *           scene.setFill(Color.TRANSPARENT);
 *
 *           stage.setScene(scene); }
 *
 *           private Node createContent() {
 *
 *           final Label hello = new Label("hello, world");
 *           hello.setStyle("-fx-font-size: 40px; -fx-text-fill: forestgreen;");
 *           final Label instructions = new Label("(click to hide)");
 *           instructions.setStyle("-fx-font-size: 12px; -fx-text-fill:
 *           orange;");
 *
 *           final VBox content = new VBox(10, hello, instructions);
 *           content.setAlignment(Pos.CENTER);
 *
 *           return content; }
 *
 *
 *           private void addAppToTray() {
 *
 *           try { // ensure awt toolkit is initialized.
 *           java.awt.Toolkit.getDefaultToolkit();
 *
 *           // app requires system tray support, just exit if there is no
 *           support. if (!java.awt.SystemTray.isSupported()) {
 *           System.out.println("No system tray support, application exiting.");
 *           Platform.exit(); }
 *
 *           // set up a system tray icon. final java.awt.SystemTray tray =
 *           java.awt.SystemTray.getSystemTray(); final URL imageLoc = new
 *           URL(JavaFXTrayIconSample.iconImageLoc); final java.awt.Image image
 *           = ImageIO.read(imageLoc); final java.awt.TrayIcon trayIcon = new
 *           java.awt.TrayIcon(image);
 *
 *           // if the user double-clicks on the tray icon, show the main app
 *           stage. trayIcon.addActionListener(event ->
 *           Platform.runLater(this::showStage));
 *
 *           // if the user selects the default menu item (which includes the
 *           app name), // show the main app stage. final java.awt.MenuItem
 *           openItem = new java.awt.MenuItem("hello, world");
 *           openItem.addActionListener(event ->
 *           Platform.runLater(this::showStage));
 *
 *           // the convention for tray icons seems to be to set the default
 *           icon for opening // the application stage in a bold font. final
 *           java.awt.Font defaultFont = java.awt.Font.decode(null); final
 *           java.awt.Font boldFont =
 *           defaultFont.deriveFont(java.awt.Font.BOLD);
 *           openItem.setFont(boldFont);
 *
 *           // to really exit the application, the user must go to the system
 *           tray icon // and select the exit option, this will shutdown JavaFX
 *           and remove the // tray icon (removing the tray icon will also shut
 *           down AWT). final java.awt.MenuItem exitItem = new
 *           java.awt.MenuItem("Exit"); exitItem.addActionListener(event -> {
 *           this.notificationTimer.cancel(); Platform.exit();
 *           tray.remove(trayIcon); });
 *
 *           // setup the popup menu for the application. final
 *           java.awt.PopupMenu popup = new java.awt.PopupMenu();
 *           popup.add(openItem); popup.addSeparator(); popup.add(exitItem);
 *           trayIcon.setPopupMenu(popup);
 *
 *           // create a timer which periodically displays a notification
 *           message. this.notificationTimer.schedule(new TimerTask() {
 * @Override public void run() {
 *
 *           javax.swing.SwingUtilities.invokeLater(() ->
 *           trayIcon.displayMessage("hello", "The time is now " +
 *           JavaFXTrayIconSample.this.timeFormat.format(new Date()),
 *           java.awt.TrayIcon.MessageType.INFO)); } }, 5_000, 60_000);
 *
 *           // add the application tray icon to the system tray.
 *           tray.add(trayIcon); } catch (java.awt.AWTException | IOException e)
 *           { System.out.println("Unable to init system tray");
 *           e.printStackTrace(); } }
 *
 *           /** Shows the application stage and ensures that it is brought ot
 *           the front of all stages. private void showStage(){
 * 
 *           if(this.stage!=null){this.stage.show();this.stage.toFront();}}
 * 
 *           public static void main(final String[]args)throws
 *           IOException,java.awt.AWTException{
 * 
 *           // Just launches the JavaFX application. // Due to way the
 *           application is coded, the application will remain running // until
 *           the user selects the Exit menu option from the tray icon.
 *           Application.launch(args);}
 **/
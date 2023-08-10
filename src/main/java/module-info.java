module com.github.ericnaibert.pdflibrary {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    requires pdfbox.app;

    opens com.github.ericnaibert.pdflibrary to javafx.fxml;
    exports com.github.ericnaibert.pdflibrary;
    exports com.github.ericnaibert.pdflibrary.group;
    opens com.github.ericnaibert.pdflibrary.group to javafx.fxml;
    exports com.github.ericnaibert.pdflibrary.menu;
    opens com.github.ericnaibert.pdflibrary.menu to javafx.fxml;
}
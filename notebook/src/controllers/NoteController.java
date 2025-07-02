package controllers;

// import necessary JavaFX UI classes
import models.Note;//must import class if its in a different package
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NoteController {

    //these fields are linked to components in the FXML file using fx:id
    @FXML private TableView<Note> noteTable;
    @FXML private TableColumn<Note, String> titleColumn;
    @FXML private TableColumn<Note, String> contentColumn;
    @FXML private TextField titleField;
    @FXML private TextArea contentField;

    //observable list stores the notes; JavaFX auto update UI when it changes
    private ObservableList<Note> notes = FXCollections.observableArrayList();

    //called automatically when the FXML is loaded
    @FXML
    public void initialize() {
        //set what each table column displays - bind the property from the Note object
        titleColumn.setCellValueFactory(data -> data.getValue().titleProperty());
        contentColumn.setCellValueFactory(data -> data.getValue().contentProperty());

        //Attach the list of notes to the table
        noteTable.setItems(notes);

        //Add a listener: when the user selects a note, load its values into the input fields
        noteTable.getSelectionModel().selectedItemProperty().addListener((obs, oldNote, newNote) -> {
            if (newNote != null) {
                titleField.setText(newNote.getTitle());
                contentField.setText(newNote.getContent());
            }
        });
    }

    //this methods runs when  the 'Add' button is clicked
    @FXML
    private void addNote() {
        //Create a new Note using input fields
        Note newNote = new Note(titleField.getText(), contentField.getText());

        //add it to the list
        notes.add(newNote);

        //clear input fields
        clearFields();
    }

    //this method runs 'update' when clicked
    @FXML
    private void updateNote() {
        //get selected note
        Note selected = noteTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            //update its title/content
            selected.setTitle(titleField.getText());
            selected.setContent(contentField.getText());

            //refresh the table so it shoes updated table
            noteTable.refresh();
        }
    }

    //this runs when 'delete' is clicked
    @FXML
    private void deleteNote() {
        Note selected = noteTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            notes.remove(selected); // remove from the list
            clearFields();// clear the form
        }
    }

    //helper method to clear the input fields
    private void clearFields() {
        titleField.clear();
        contentField.clear();
    }
}

package controllers;

// import necessary JavaFX UI classes
import models.Note;//must import class if its in a different package
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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
        //url to localhost for mongodb
        String URI = "mongodb://localhost:27017";

        //Create a mongo client (try with resources -> no catch block)
        try(MongoClient mongoClient= MongoClients.create(URI)) {
            //These lines get the database db_mongodb_notes
            //From the specified database we get the collection(Table) notes
            //We create a document to add to notes
            MongoDatabase mongoDatabase = mongoClient.getDatabase("db_mongodb_notes");
            MongoCollection mongoCollection = mongoDatabase.getCollection("notes");
            Document document = new Document();
            System.out.println("Connection was possible");

            //Add code for reading from the database @syd-xo

        }

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
        //url to localhost for mongodb
        String URI = "mongodb://localhost:27017";

        //Create a mongo client (try with resources -> no catch block)
        try(MongoClient mongoClient= MongoClients.create(URI)){
            //These lines get the database db_mongodb_notes
            //From the specified database we get the collection(Table) notes
            //We create a document to add to notes
            MongoDatabase mongoDatabase= mongoClient.getDatabase("db_mongodb_notes");
            MongoCollection mongoCollection = mongoDatabase.getCollection("notes");
            Document document= new Document();
            System.out.println("Connection was possible");

            //We will be adding details to the document and sending that to the collection
            document.append("Title",titleField.getText()).append("Content",contentField.getText());
            mongoCollection.insertOne(document);
            System.out.println("Send was possible");
        }

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
        //url to localhost for mongodb
        String URI = "mongodb://localhost:27017";

        //Create a mongo client (try with resources -> no catch block)
        try(MongoClient mongoClient= MongoClients.create(URI)) {
            //These lines get the database db_mongodb_notes
            //From the specified database we get the collection(Table) notes
            //We create a document to add to notes
            MongoDatabase mongoDatabase = mongoClient.getDatabase("db_mongodb_notes");
            MongoCollection mongoCollection = mongoDatabase.getCollection("notes");
            Document document = new Document();
            System.out.println("Connection was possible");

            Note selectedNote = noteTable.getSelectionModel().getSelectedItem();
            if (selectedNote != null) {
                String originalTitle = selectedNote.getTitle();
                String newTitle = titleField.getText();
                String newContent = contentField.getText();

                Document filter = new Document("Title", originalTitle);
                Document update = new Document("$set", new Document("Title", newTitle).append("Content", newContent));

                mongoCollection.updateOne(filter, update);
                System.out.println("Note updated successfully in MongoDb");

                selectedNote.setTitle(newTitle);
                selectedNote.setContent(newContent);
                noteTable.refresh();
            }
        } catch (Exception e) {
            System.out.println("Error updating note: " + e.getMessage());

        }
            //Add code for updating notes @Kristiebytes

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
        //url to localhost for mongodb
        String URI = "mongodb://localhost:27017";

        //Create a mongo client (try with resources -> no catch block)
        try(MongoClient mongoClient= MongoClients.create(URI)) {
            //These lines get the database db_mongodb_notes
            //From the specified database we get the collection(Table) notes
            //We create a document to add to notes
            MongoDatabase mongoDatabase = mongoClient.getDatabase("db_mongodb_notes");
            MongoCollection mongoCollection = mongoDatabase.getCollection("notes");
            Document document = new Document();
            System.out.println("Connection was possible");

            //Add code for deleting notes @ALBERTM06
        }

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

package models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class NoteModel {

    private static MongoCollection<Document> getNotesCollection() {
        MongoDatabase db = DatabaseConnection.getDatabase();
        return db.getCollection("notes");
    }

    public static List<Document> getAllNotes() {
        List<Document> notesList = new ArrayList<>();
        for (Document note : getNotesCollection().find()) {
            notesList.add(note);
        }
        return notesList;
    }
}

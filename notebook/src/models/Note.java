package models;

//this imports JavaFX classes to support data binding(observable fields)
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//class representing a Note (with title and content)
public class Note {
    //Declare title as JavaFX StringProperty so it can auto-update UI components
    private final StringProperty title = new SimpleStringProperty();

    //the same is done for content
    private final StringProperty content = new SimpleStringProperty();

    //Constructor that sets both fields when a new Note is created
    public Note(String title, String content) {
        this.title.set(title);
        this.content.set(content);
    }

    //Getter : retrieves the title as a plain string
    public String getTitle() { return title.get(); }

    //setter: sets the title value
    public void setTitle(String title) { this.title.set(title); }

    //property getter: used for binding to UI elements
    public StringProperty titleProperty() { return title; }

    //same three methods for content
    public String getContent() { return content.get(); }
    public void setContent(String content) { this.content.set(content); }
    public StringProperty contentProperty() { return content; }
}

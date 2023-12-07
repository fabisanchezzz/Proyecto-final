package com.project.project;

import java.util.Objects;

public class Nota {
    private int NoteID;
    private int UserID;
    private String Title;
    private String Content;

    // Constructor

    public Nota(int NoteID, int UserID, String Title, String Content) {
        this.NoteID = NoteID;
        this.UserID = UserID;
        this.Title = Title;
        this.Content = Content;
    }
       public Nota( int UserID, String Title, String Content) {
        this.UserID = UserID;
        this.Title = Title;
        this.Content = Content;
    }

    // Getters y Setters

    public int getNoteID() {
        return this.NoteID;
    }

    public void setNoteID(int NoteID) {
        this.NoteID = NoteID;
    }
    public int getUserID() {
        return this.UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public String getContent() {
        return this.Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

 @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Nota nota = (Nota) obj;
        return Objects.equals(UserID, nota.UserID) &&
               Objects.equals(Title, nota.Title) &&
               Objects.equals(Content, nota.Content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UserID, Title, Content);
    }

    @Override
    public String toString() {
        return "Nota{" +
               "UserID='" + UserID + '\'' +
               ", Title='" + Title + '\'' +
               ", Content='" + Content + '\'' +
               '}';
    }

    
}
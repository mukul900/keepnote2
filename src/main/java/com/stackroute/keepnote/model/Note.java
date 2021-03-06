package com.stackroute.keepnote.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 */

@Entity
public class Note {
	
	@Id
	@Column(length=20)
	private int noteID;
	@Column(length=20)
	private String noteTitle;
	@Column(length=20)
	private String noteContent;
	@Column(length=20)
	private String noteStatus;
	@Column(length=30)
	private LocalDateTime createdAt;
	

	public Note() {

	}

	public Note(int noteId, String noteTitle, String noteContent, String noteStatus, LocalDateTime createdAt) {
	 this.noteID = noteId;
	 this.noteTitle = noteTitle;
	 this.noteContent = noteContent;
	 this.noteStatus = noteStatus;
	 this.createdAt = createdAt;
	 
	}

	public int getNoteId() {

		return this.noteID;
	}

	public String getNoteTitle() {

		return this.noteTitle;
	}

	public String getNoteContent() {

		return this.noteContent;
	}

	public String getNoteStatus() {

		return this.noteStatus;
	}

	public void setNoteId(int noteId) {
		this.noteID = noteId;

	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;

	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;

	}

	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;

	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
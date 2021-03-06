package com.stackroute.keepnote.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.model.Note;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */
@Repository
public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 * 
	 */
	@Autowired
	SessionFactory sessionFactory;
	
	public NoteDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}


	/*
	 * Save the note in the database(note) table.
	 */

	public boolean saveNote(Note note) {
		getSession().save(note);
		return true;

	}

	/*
	 * Remove the note from the database(note) table.
	 */

	public boolean deleteNote(int noteId) {
		getSession().delete(getNoteById(noteId));
		return true;

	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	public List<Note> getAllNotes() {
		return getSession().createQuery("from Note order by createdAt desc").list();

	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	public Note getNoteById(int noteId) {
		return getSession().find(Note.class,noteId);

	}
	/* Update existing note */

	public boolean UpdateNote(Note note) {
		getSession().update(note);
		return true;
	}

}

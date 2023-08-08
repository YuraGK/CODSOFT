package ADDRESS_BOOK_SYSTEM;

import java.util.LinkedList;
import java.util.List;


public class AddressBook {

	List<Contact> contacts;
	
	public AddressBook() {
		contacts = new LinkedList<Contact>();
	}
	
	public void addContact(Contact contact) {
		if(findContact(contact.getName()) != null) {
			contacts.remove(findContact(contact.getName()));
		}
		contacts.add(contact);
	}
	
	public void removeContact(Contact contact) {
		contacts.remove(contact);
	}
	
	public Contact findContact(String name) {
		for(Contact contact : contacts) {
			if(contact.getName().equals(name)) {
				return contact;
			}
		}
		return null;
	}
	
	public List<Contact> getContacts() {
		return contacts;
	}
}

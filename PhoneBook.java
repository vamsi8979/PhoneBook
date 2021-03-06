import java.util.ArrayList;
import java.util.Collections;
import simpleIO.*;
import java.lang.StringBuilder;
import java.util.regex.*;
/**
 * The PhoneBook class uses an ArrayList to collect together
 * instances of the Entry class. The phonebook class is able
 * to manage and process the Entry objects.  For example, by adding
 * new entries to the phonebook, finding specific entires, listing
 * all the entries, etc.
 * 
 * @author Philip Hale
 * @version p2
 */
public class PhoneBook  {
    private ArrayList<Entry> entries;
    private UserDialog uD;
    
    public PhoneBook()  {
        entries = new ArrayList();
        uD = new UserDialog();
        phoneInterface();
    }
    
    
    public void phoneInterface()  {
        String[] userOptions = {"Browse your contacts", "Add a new contact", "Modify an existing contact", "Delete a contact", "Search for a contact", "Quit"};
        int which = uD.selectIndex("Welcome to your phonebook!  What would you like to do next?", userOptions);
        switch (which)  {
            case 0: listAll(); break;
            case 1: addEntry(uD.getString("Please type the name of the new contact"), uD.getString("Please type the number of the new contact")); break;
            case 2: changeEntry(findEntry(uD.getString("Please type the first few characters of the contact you wish to modify: "))); break;
            case 3: removeEntry(findEntry(uD.getString("Please type the first few characters of the contact you wish to remove"))); break;
            case 4: uD.showMessage(findEntry(uD.getString("Please type the first few characters of the contact you wish to find")).toString()); break;
            case 5: return;
            default: return;
        }
        phoneInterface();
    }
    
    public void addSamples(int quant)  {
        TestClass testClass = new TestClass();
        for (int i = 0; i < quant; i++)  {
            addEntry(testClass.sampleName(), testClass.sampleNumber());
        }
    }
    
    public boolean addEntry(String name, String number)  {
        if (!entries.add(new Entry(name, number)))  {
            return false;
        }
        return true;
    }
    
    /**
     * The findEntry() method uses regular expressions to find any contact that contains
     * the search string.  The first ocurrence of the 
     */
    public Entry findEntry(String n)  {
        Pattern p = Pattern.compile(n);
        Collections.sort(entries);
        for (Entry entry : entries)  {
            Matcher m = p.matcher(entry.getName().toLowerCase());
            if (m.find())  {
                return entry;
            }
        }
        return null;
    }
         
    public boolean removeEntry(Entry entry)  {
        return entries.remove(entry);
    }
    
    public void changeEntry(Entry entry)  {
        entry.changeName(uD.getString("Please enter a new name"));
        entry.changeNumber(uD.getString("Please enter a new number"));
    }
    
    private void changeName(Entry entry, String name)  {
        entry.changeName(name);
    }
    
    private void changeNumber(Entry entry, String number)  {
        entry.changeNumber(number);
    }
    
    public void listAll()  {
        Collections.sort(entries);
        for (Entry entry : entries)  {
            System.out.println(entry);
        }
    }
    

}
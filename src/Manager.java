import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Manager {
	List<Person> _ListOfPersons;
	
	public Manager()
	{
		_ListOfPersons = new ArrayList<>();
	}
	public void Add(String input)
	{
		Person person = new Person(input);
		_ListOfPersons.add(person);
	}
	public void DeletePerson(String input)
	{
		List<Person> toRemove = new ArrayList<Person>();
		for(Person a: _ListOfPersons){
		    if(a.ToString().contains(input)){
		        toRemove.add(a);
		    }
		}
		_ListOfPersons.removeAll(toRemove);
	}
	public String FindFirst(String input)
	{
		for(Person item : _ListOfPersons)
		{
			if(item.ToString().contains(input))
			{
				return item.toString();
			}
		}
		return "Person Not Found";
	}
	public List<Person> FindAll(String input)
	{
		List<Person> result = new ArrayList<Person>();
		for(Person item: _ListOfPersons)
		{
			if(item.ToString().contains(input))
			{
				result.add(item);
			}
		}
		return result;
	}
	public void Load()
	{
		String csvFile ="Persons.csv";
		BufferedReader br = null;
		String line ="";
		try {

            br = new BufferedReader(new FileReader(csvFile));
            _ListOfPersons = new ArrayList<>();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                Add(line);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	public void Save()
	{
		PrintWriter pw;
		StringBuilder sb;
		try {
		pw = new PrintWriter(new File("Persons.csv"));
		sb = new StringBuilder();
		for(Person item:_ListOfPersons)
		{
			sb.append(item.ToString());
			sb.append('\n');
		}
		pw.write(sb.toString());

		pw.close();
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("FileNotFound! Solution: Save to create File...");
		}
	}
}



public class Person {
	private String _FirstName;
	private String _LastName;
	private String _Gender;
	private String _DateOfBirth;
	
	public Person(String input)
	{
		String[] arrayInput = input.split(";");
		set_FirstName(arrayInput[0]);
		set_LastName(arrayInput[1]);
		set_Gender(arrayInput[2]);
		set_DateOfBirth(arrayInput[3]);
		
	}
	
	public String ToString()
	{
		return get_FirstName()+";"+get_LastName()+";"+get_Gender()+";"+get_DateOfBirth();
	}

	String get_FirstName() {
		return _FirstName;
	}

	void set_FirstName(String _FirstName) {
		this._FirstName = _FirstName;
	}

	String get_LastName() {
		return _LastName;
	}

	void set_LastName(String _LastName) {
		this._LastName = _LastName;
	}

	String get_Gender() {
		return _Gender;
	}

	void set_Gender(String _Gender) {
		this._Gender = _Gender;
	}

	String get_DateOfBirth() {
		return _DateOfBirth;
	}

	void set_DateOfBirth(String _DateOfBirth) {
		this._DateOfBirth = _DateOfBirth;
	}

}

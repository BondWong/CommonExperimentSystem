package model;

public enum UserType {
	STUDENT, PROFESSOR, ADMIN;
	
	public String toString(){
		return this.name();
	}
}

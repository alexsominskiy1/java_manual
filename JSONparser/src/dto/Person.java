package dto;

import java.time.LocalDate;

public class Person {
	
	private long id;
	private String name;
	private LocalDate birthday;
	private byte accessCode;
	private short privateCode;
	private char group;
	private int age;
	private float weight;
	private double height;
	private boolean married;
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birthday=" + birthday + ", accessCode=" + accessCode
				+ ", privateCode=" + privateCode + ", group=" + group + ", age=" + age + ", weight=" + weight
				+ ", height=" + height + ", married=" + married + "]";
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public byte getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(byte accessCode) {
		this.accessCode = accessCode;
	}
	public short getPrivateCode() {
		return privateCode;
	}
	public void setPrivateCode(short privateCode) {
		this.privateCode = privateCode;
	}
	public char getGroup() {
		return group;
	}
	public void setGroup(char group) {
		this.group = group;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public boolean isMarried() {
		return married;
	}
	public void setMarried(boolean marriage) {
		this.married = marriage;
	}
	
}

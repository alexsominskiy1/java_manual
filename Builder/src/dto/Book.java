package dto;

public class Book {
	
	private String author;
	private String title;
	private int publishingYear;
	private double price;
	private boolean pictures;
	
	@Override
	public String toString() {
		return "Book [" + 
	               "author=" + author + 
				   ", title=" + title + 
				   ", publishingYear=" + publishingYear + 
				   ", price=" + price + 
				   ", pictures=" + pictures + 
			   "]";
	}	
	

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		private Book instance;		
		public Builder() {
			this.instance = new Book();
		}		
		public Builder withAuthor(String author) {
			instance.author = author;
			return this;
		}		
		public Builder withTitle(String title) {
			instance.title = title;
			return this;
		}		
		public Builder withYear(int year) {
			instance.publishingYear = year;
			return this;
		}
		public Builder withPrice(double price) {
			instance.price = price;
			return this;
		}
		public Builder withPictures(boolean pictures) {
			instance.pictures = pictures;
			return this;
		}
		public Book build() {
			return instance;
		}
	}
}

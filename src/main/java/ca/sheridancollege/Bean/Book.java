package ca.sheridancollege.Bean;

import lombok.*;
import java.io.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book implements Serializable{

	
	private int id;
	private String title;
	private String author;
	private int price;
	private int quantity;
	private String course;
	private String campus;
	
	public Book(String title,String author,int price,
			String course,int quantity, String campus)
	{
		this.title=title;
		this.author=author;
		this.price = price;
		this.quantity = quantity;
		this.course = course;
		this.campus = campus;
	}
	
}

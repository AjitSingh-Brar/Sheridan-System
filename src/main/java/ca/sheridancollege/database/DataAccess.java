package ca.sheridancollege.database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.Bean.Book;

@Repository
public class DataAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	

	String[] title =  {"Object Oriented Prog1","Learn PHP","HTML & CSS Learning","Advanced Machine Learning","User Design",
        			"Enterprise JAVA","Assembly Language","Software Analysis","Architecture Learning","Programmming Educative"
        			,"Canadian Culture","Biological Learning"," Advance IOS Learning","Advance C++","Sharping .Net"," Advanced C#",
        			"Learning C++"," Database Implement","JavaScript Prog","Python Learner"," Visual Basic","Impact Of Internet",
        			" Data Analyst","Chemical Engineering","3D Gamming","Android Learning","JDBC Advance"," Learn Bootstrap",
        					"Networking-Switches","Basics of MySQL"}; 
	

	String [] courses= {"Java1","Web Programming","Machine Learning","Computer Architecture","Interactive User Design"};
	
	String []author = {"J.Marvis","A.Singh","H.Kaur","S.Bond","K.Talker"};
	
	
	public void generateRecord(String campus)
	{
		String courseList="";
		int num;
		
		for(int i = 0;i < 30; i++)
		{
			num = (int)(Math.random()*5+1);
			for(int j=1;j<=num;j++)
			{
				courseList = courseList.concat(courses[(int)(Math.random()*3)]);
				if(j<num)
				{
					courseList = courseList.concat(",");
				}
				if(j==1) 
				{
					courseList = courseList.concat(" ");
				}
			}
			getAdd(new Book(title[i],author[(int)(Math.random()*4)],(int)(Math.random()*200)+1,
					courseList,(int)(Math.random()*200)+1,campus));
			courseList="";
		}
	}
	
	
	
	public void getAdd(Book b)
	{
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String q = "insert into library(title,author,price,quantity,course,campus) values(:title,:author,:price,:quantity,:course,:campus)";
		
		parameter.addValue("title", b.getTitle());
		parameter.addValue("author", b.getAuthor());
		parameter.addValue("price", b.getPrice());
		parameter.addValue("quantity", b.getQuantity());
		parameter.addValue("course", b.getCourse());
		parameter.addValue("campus", b.getCampus());
		
		jdbc.update(q,parameter);
		
	}
	
	public ArrayList<Book> getView()
	{
		ArrayList<Book> bk = new ArrayList<Book>();
		String q = "Select * from library";
		
		List<Map<String,Object>> rows = jdbc.queryForList(q, new HashMap<String,Object>());
		
		for(Map<String,Object> row:rows)
		{
			Book c = new Book();
			c.setId((Integer)(row.get("id")));
			c.setTitle((String)(row.get("title")));
			c.setAuthor((String)(row.get("author")));
			c.setPrice((Integer) row.get("price"));
			c.setQuantity((Integer)(row.get("quantity")));
			c.setCourse((String)(row.get("course")));
			c.setCampus((String)(row.get("campus")));
			
			bk.add(c);
		}
		return bk;
		
		
		
	}
	public Book getEditLink(int id)
	{
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String q = "Select * from library where id = :id";
		
		parameter.addValue("id", id);
		
		ArrayList<Book> bk = (ArrayList<Book>)jdbc.query(q,parameter, new BeanPropertyRowMapper<Book>(Book.class));
		
		if(bk.size() > 0)
		{
			return bk.get(0);
		}
		return null;
	}
	public void getUpdate(Book b)
	{
	
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String q = "update library set title=:title,author=:author,price=:price,quantity=:quantity,campus=:campus where id=:id";
		
		parameter.addValue("id", b.getId());
		parameter.addValue("title", b.getTitle());
		parameter.addValue("author", b.getAuthor());
		parameter.addValue("price", b.getPrice());
		parameter.addValue("quantity", b.getQuantity());
		parameter.addValue("course", b.getCourse());
		parameter.addValue("campus", b.getCampus());
		
		jdbc.update(q,parameter);
		
	}
	public void getDelete(int id)
	{
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String q = "delete from library where id=:id";
		
		parameter.addValue("id",id);
		
		jdbc.update(q,parameter);
		
		
	}
	public Book getSearching(int id)
	{
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String q = "Select * from library where id = :id";
		
		parameter.addValue("id", id);
		
		ArrayList<Book> bk = (ArrayList<Book>)jdbc.query(q,parameter, new BeanPropertyRowMapper<Book>(Book.class));
		
		if(bk.size() > 0)
		{
			return bk.get(0);
		}
		return null;
		
		
		
		
	}
	
	public ArrayList<Book> getSearchtitle(String title)
	{
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String q = "Select * from library where title = :title";
		
		parameter.addValue("title", title);
		
		ArrayList<Book> bk = (ArrayList<Book>)jdbc.query(q,parameter, new BeanPropertyRowMapper<Book>(Book.class));
		
		
		if(bk.size() > 0)
		{
			return bk;
		}
		return null;
		
		
		
	}
	public ArrayList<Book> getSearhAuthor(String author)
	{
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String q = "Select * from library where author=:author";
		
		parameter.addValue("author", author);
		
		ArrayList<Book> bk = (ArrayList<Book>)jdbc.query(q,parameter, new BeanPropertyRowMapper<Book>(Book.class));
		
		
		if(bk.size() > 0)
		{
			return bk;
		}
		return null;
//		
		
		
	}
	public ArrayList<Book> getSearchCourse(String course)
	{
		ArrayList<Book> bk = new ArrayList<Book>();
		String q = "Select * from library";
		
		List<Map<String,Object>> rows = jdbc.queryForList(q, new HashMap<String,Object>());
		
		for(Map<String,Object> row:rows)
		{
			Book c = new Book();
			c.setId((Integer)(row.get("id")));
			c.setTitle((String)(row.get("title")));
			c.setAuthor((String)(row.get("author")));
			c.setPrice((Integer) row.get("price"));
			c.setQuantity((Integer)(row.get("quantity")));
			c.setCourse((String)(row.get("course")));
			c.setCampus((String)(row.get("campus")));
			
			String couse =(String)(row.get("course"));
			
			ArrayList<String> course1 = new ArrayList<String>();
			int j=0;
			for(int i=0;i< couse.length();i++)
			{
				if(couse.charAt(i)==',')
				{
					course1.add(couse.substring(j,i));
				    j= i + 1;
				}
				
			}
			for(int k = 0; k< course1.size();k++)
			{
				if(course.contentEquals(course1.get(k)))
				{
					bk.add(c);
					
				}
			}
			course1 = null;
			
			
			
		}
		return bk;
		
		
		
	}
	public ArrayList<Book> getSearchQuantityc(int quantity1,int quantity2)
	{
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String q = "Select * from library  where quantity between :quantity1 and :quantity2";
		
		parameter.addValue("quantity1",quantity1);
		parameter.addValue("quantity2", quantity2);
		
		ArrayList<Book> bk = (ArrayList<Book>)jdbc.query(q,parameter, new BeanPropertyRowMapper<Book>(Book.class));
		
		
		if(bk.size() > 0)
		{
			return bk;
		}
		return null;	
//		
//		
//	}
	
	
	
	
	
	
	
	
	}
	
	public void getPurchase(int id)
	{
		MapSqlParameterSource  parameter = new MapSqlParameterSource();
		String q = "update library set quantity = quantity - 1 where id=:id";
       
		parameter.addValue("id", id);
		
		
		jdbc.update(q, parameter);
	}
	public void getPurchases(int id)
	{
		MapSqlParameterSource  parameter = new MapSqlParameterSource();
		String q = "update library set quantity = quantity - 1 where id=:id";
       
		parameter.addValue("id", id);
		
		
		jdbc.update(q, parameter);
	}
	
	
	
}

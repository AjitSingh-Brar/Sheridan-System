package ca.sheridancollege.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.Bean.Book;
import ca.sheridancollege.database.DataAccess;

@Controller
public class HomeController {

	@Autowired
	private DataAccess da;
	
	
	
	private String campus;
	
	@GetMapping("/")
	public String doHome()
	{
		return "home.html";
	}
	@GetMapping("/adds")
	private String doAdd(@RequestParam String type, HttpSession session)
	{
		campus=type;
	    session.setAttribute("campuses", type);
		
		return "add.html";
	}
	
	
	@GetMapping("/addss")
	private String doAdding(@RequestParam String title,@RequestParam String author,@RequestParam int price,@RequestParam int quantity,@RequestParam String course,@RequestParam String campus)
	{
	 
		Book b = new Book();
		b.setTitle(title);
		b.setAuthor(author);
		b.setPrice(price);
		b.setQuantity(quantity);
		b.setCourse(course);
		b.setCampus(campus);
		
		da.getAdd(b);
		
		return "add.html";
		
	}
	
	@GetMapping("/view")
	public String doView(Model model)
	{
		model.addAttribute("hi",da.getView());
		return "View.html";
	}
	@GetMapping("/editlink/{id}")
	public String getEdit(Model model, @PathVariable int id)
	{
		Book b = da.getEditLink(id);
		model.addAttribute("contact", b);
		
		return "editContact.html";
		 
	}
	@GetMapping("/modify")
	public String getModify(Model model,@RequestParam int id,@RequestParam String title,@RequestParam String author,@RequestParam int  price,@RequestParam int quantity,@RequestParam String course,
			@RequestParam String campus)
	{
		Book b = new Book(id,title,author,price,quantity,course,campus);
		
		da.getUpdate(b);
	   model.addAttribute("hi",da.getView());
	   
	   return "View.html";
	 
		
	}
	@GetMapping("/deletelink/{id}")
	public String getDelete(@PathVariable int id,Model model)
	{
		da.getDelete(id);
		model.addAttribute("hi",da.getView());
		
		return "View.html";
	}
	
	
	@GetMapping("/search")
	public String getSearch()
	{
		return "search.html";
	}
	
	
	@GetMapping("/searchc")
	public String getSearch(Model model,@RequestParam int id)
	{
		
		Book b = da.getSearching(id);
		model.addAttribute("hi",b);
		return "searchview.html";	
	}
	@GetMapping("/searcht")
	public String getSearchtitle(Model model, @RequestParam String title)
	{
	   model.addAttribute("hit",da.getSearchtitle(title));
	   return "searchview.html";	
     } 
	@GetMapping("/searcha")
	public String getSearchAuthor(Model model, @RequestParam String author)
	{
		
		
		model.addAttribute("hits",da.getSearhAuthor(author));
		return "searchview.html";	
	}
	@GetMapping("/searchce")
	public String getSearchCourse(Model model, @RequestParam String course)
	{
		
		
		model.addAttribute("hitss",da.getSearchCourse(course));
		return "searchview.html";	
	}
	@GetMapping("/searchq")
	public String getSearchQuantity(Model model, @RequestParam int quantity1,@RequestParam int quantity2)
	{
		
		model.addAttribute("hitse",
				da.getSearchQuantityc(quantity1,quantity2));
		return "searchview.html";	
	
	}
	
	@GetMapping("/sear")
	public String que()
	{
		return "home.html";
	}
	@GetMapping("/searh")
	public String getSearh()
	{
		return "search.html";
	}
	
	@GetMapping("/purchaselinks/{id}")
	public String dopurchase(@PathVariable int id,Model model)
			{
		        da.getPurchase(id);
		        model.addAttribute("hi", da.getView());
		        
		        return "View.html";
			}
	
	
	@GetMapping("/purchaselink/{id}")
	public String dopurchases(@PathVariable int id,Model model)
			{
		        da.getPurchases(id);
		        model.addAttribute("hi", da.getView());
		        
		        return "View.html";
			}
	
	
	@GetMapping("/dum")
	public String getDummy(Model model)
	{
		da.generateRecord(campus);
		return "add.html";
	}
	
	
	
	
	
	
}

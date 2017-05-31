/**
 * 
 */
package com.booksKart.comn;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * @author narasareddy.k
 *
 */
public class XMLFileReading {
	
	private static List<Book> getListofBooks(String fileName){
		
		try {
			List<Book> booksList = new ArrayList<Book>();
			Book book = null;
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("book");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					book = new Book();
					Element eElement = (Element) nNode;
					book.setBookId(Long.parseLong(eElement.getAttribute("id")));
					book.setBookName(eElement.getElementsByTagName("bookName").item(0).getTextContent());
					book.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());
					book.setPublishDate(eElement.getElementsByTagName("publishDate").item(0).getTextContent());
					book.setPrice(eElement.getElementsByTagName("price").item(0).getTextContent());
					book.setImageLocation(eElement.getElementsByTagName("imageLocation").item(0).getTextContent());
				}
				booksList.add(book);
			}
			return booksList;
		    } catch (Exception e) {
			e.printStackTrace();
			return null;
		    }
	   }

	public static void main(String argv[]) {
	  String fileName ="/Users/narasareddy.k/Desktop/untitled folder/jboss-as-7.1.1.Final/standalone/deployments/BooksKart/src/books.xml";
	  List<Book> booksList = getListofBooks(fileName);
	  System.out.println(booksList.size());
		
	  }

}

• API Testing Project written using REST Assured and POM Design Pattern

• Follow these Steps to Run Test on your Machine:
1) Clone Repository
2) Open Project using IDE
3) Update Maven Project
4) Install TestNg for your IDE if you don't have it
5) Run Test

•Library API Documentation:
```
BaseURI : http://216.10.245.166
 
1.	Resource : Library/Addbook.php       Method : POST
 
Input Payload : Json:
{

"name":"Learn Appium Automation with Java",
"isbn":"bcd",
"aisle":"227",
"author":"John foe"
}
 
Output Json 
{
   "Msg": "successfully added",
   "ID": "bcd227"
} 
 

2.	Resource : /Library/GetBook.php?AuthorName=somename         Method : GET 
 
Output Json :
Output the array of Json object books with all below  details 
 
{
	Name : “bookname”   ( String)
	Isbn :  “A2fdsf”   (String)
	Aisle : 32 (Integer)
}
 
 
3.	Resource : Library/GetBook.php?ID=3389      - Method : GET 
 
Output Json :
{
      "book_name": "Selenium automation using Java",
      "isbn": "a23hd738",
      "aisle": "1223"
} 
 

4.	Resource :/Library/DeleteBook.php      Method : POST
 
Input Payload : Json:
{
	"ID" : "a23h345122332"
} 
Output Response :
{
	msg : book is successfully deleted”
}
```

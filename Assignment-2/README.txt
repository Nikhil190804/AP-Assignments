A bullet list of any assumptions that were made.

1. By Default Minor has discount 10% and Senior has 20% discount.
2. I have made Student discount available for visitor from age 6 to less than 18, by Default students discount is 0.
3. By Default the two deals are not active , admin must make them active.
4. To add discount to a custom Attraction , use set discount option .
5. Exploring the Zoo option is free for everyone, and Visitor dont need any Membership for this.
6. While Buying tickets , if user buys only 1 ticket , then only discounts are offered on the total amount. And if he buys 2 tickets then discount + 2 ticket deal is offered , and for tickets count greater than 2 discount + 3 ticket deal is offered . First discount is applied on the amount , then after that deal is applied on that discounted amount.
7. For visit Animals option , visitor need to compulsory have any Membership .
8. Input should be given in correct form i.e - when asking for a price or any numerical value only numbers should be given as a input. Although i have handled some of these , but some are still left , as handling these were not a priority for this assignment.


HOME_FOLDER = Assignment-2

All the commands should be run on the terminal in the HOME_FOLDER unless otherwise specified.

0) Download the folder from Classroom and unzip.
1) mvn clean 
2) mvn compile
3) mvn package
4) java -jar .\target\Assignment-2-1.0-SNAPSHOT.jar

Running the 4) command will run the whole jar file and will start executing the Main method . After that choose the options for functionality as you want.
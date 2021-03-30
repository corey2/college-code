Corey Shapiro
3-13-12
HW#4

	This folder contains the java files Item, Catalog, ItemOrder, 
ShoppingCart, ShoppingFrame, and ShoppingMain. These files work together to
represent the activity of shopping. ShoppingFrame represents the interface
in which people can plan to purchase items and ShoppingMain uses information 
from all the other classes to bring the scenario to
life. The other classes, which I created, deal with the more data oriented 
aspects of the scenario. The general concept that Item represents is 
self-explanatory. More specifically, it holds the name and price of the given
item. There is also a construction option which allows an Item to hold a 
bulk quantity and bulk price. These amounts represent discounts that one can
get from buying a lot of one product at one time. An item will not store any
negative values. Catalog represents the items that a retailer has available
for sale. Catalog is effectively an ArrayList that stores a name and has 
limited access to the repretoire of usual ArrayList methods. ItemOrder 
represents an order of a given quantity of a given item. It can return 
certain parts of its state, find its total priice, print itself, and compare
itself to other ItemOrder objects in terms of quantity. The final class is 
ShoppingCart, which is essentially an ArrayList of ItemOrders. It has the
special abilities of sorting itself by quantity, finding the total price
of all of its componants, and printing all the componants in one large chunk, 
each with only the call of a single method. Its hardest to program and 
therefore most impressive ability is an interesting expansion of the more 
basic ArrayList add method. The ShoppingCart will add a new potential
ItemOrder to the ArrayList normally if the type of order has not been made 
before. If this type of order HAS been added to the ShoppingList before,
the old order will be replaced with the new one.
    
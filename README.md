Simshop – Project Description

Simshop is an e-commerce system dedicated to managing a shop with sim racing accessories. The system supports essential business operations such as product management, order processing, returns, and comprehensive administration for users, admins, and superadmins.
Key Features

    Object Inheritance:
    Products are modeled using inheritance with a general Product class and specialized subclasses: Base, Wheel, Pedals.

    Product Information:
    Each product has a unique serial number, name, price, photo, and warranty. Products feature type-specific attributes (e.g., torque for bases, material, clutch presence for pedals or wheels).

    Relations:
    Products are linked to Brand and can participate in Discounts.
    Orders can contain multiple product line items and maintain a status (returns enabled).

    User Management:
    The system distinguishes between regular Users, Admins (who manage discounts), and SuperAdmins (who can delete accounts and modify emails).

    Data Validation & Uniqueness:
    Key attribute validations are in place (age, status, enum restrictions). Serial numbers are enforced as unique. Automatic calculations (like user age) are implemented.

    Persistence (Serialization):
    Data is serialized to a file (extent.ser) for persistence using an ObjectPlus approach, supporting future expansion.

Class Model

   ![Entity diagram](https://github.com/Juks0/Simracing-shop/blob/master/Entity%20diagram.pdf)
   ![Implementation diagram](https://github.com/Juks0/Simracing-shop/blob/master/Implementation%20diagram.pdf)


Orders exist only in the context of an Admin (composition). Collection relationships are ordered; there are subsets (such as user-owned orders) and various association relations.
Example Use Cases

    User registration and login

    Browsing product categories (Base, Wheel, Pedals)

    Placing new orders (selecting products and quantities)

    Order review and returns

    Management of discounts (Admin/SuperAdmin)

    Account deletion (SuperAdmin), email changes

Implementation Highlights

    Serialization enables data persistence ("file-based extent").

    Complex, optional, repeatable, and derived attributes; class-level attributes present.

    Use of polymorphism, inheritance, and interfaces.

    Collection handling (ordered, subsets).

    Dynamic transformation (e.g., Order to ReturnedOrder).

    Strict attribute validation and constraint enforcement.



Product List:
Name	Price	In Stock	Quantity
Moza r9	$15.00	    20	
Moza r16 $1,400.00	3	
Moza r21 $367.00	24	

    Order Summary: Total value and product count.

Use case model
   ![Use case model](https://github.com/Juks0/Simracing-shop/blob/master/Use%20case%20model.pdf)
Activity diagram
   ![Activity diagram](https://github.com/Juks0/Simracing-shop/blob/master/Use%20case%20model.pdf)
State diagram
   ![State diagram](https://github.com/Juks0/Simracing-shop/blob/master/State%20diagram.pdf)

   

Technical Information

The project is written in Java, designed for easy future expansion and integration. Data is serialized to a file (extent.ser). Class diagrams and use case diagrams are supplied in the internal documentation.

@OMT-7932 @regression

Feature: Validating pizza application functionalities

  Scenario Outline: Validating place order functionality
    Given user navigates to pizza application
    When user creates pizza order with data
      | Pizza        | <Pizza>        |
      | Topping 1    | <Topping 1>    |
      | Topping 2    | <Topping 2>    |
      | Quantity     | <Quantity>     |
      | Name         | <Name>         |
      | Email        | <Email>        |
      | Phone        | <Phone>        |
      | Payment Type | <Payment Type> |
    Then user validates that order is created with success message "Thank you for your order! TOTAL: " " Small 6 Slices - no toppings"
    Examples:
      | Pizza                        | Topping 1 | Topping 2    | Quantity | Name        | Email           | Phone     | Payment Type   |
      | Small 6 Slices - no toppings | Mushrooms | Extra cheese | 1        | Patel Harsh | patel@gmail.com | 123456789 | Credit Card    |
      | Large 10 Slices - 2 toppings | Mushrooms | Extra cheese | 2        | John Doe    | john@gmail.com  | 323451789 | Cash on Pickup |
      | Medium 8 Slices - 2 toppings | Olives    | Salami       | 3        | Kim Moore   | kim@gmail.com   | 123456759 | Credit Card    |

    #Map<String,Object> map =new HashMap:
  #map.put("Pizza","Small 6 Slices - no toppings,Topping")
  #map.put(Topping 1=Mushrooms)

    # [Pizza=" Small 6 Slices - no toppings,Topping 1=Mushrooms,Toppings2=Extra cheese,Quantity=1,Name=Patel Harsh,Email=patel@gmail.com,Phone123456789,Payment Type=Credit card];
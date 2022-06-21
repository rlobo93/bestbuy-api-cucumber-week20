Feature: as a user i should be able create, read, update and delete store, products, services and categories details CRUD operation

  Scenario Outline: User can run successful CRUD operation on category records
    When            I creates new category record using "<name>","<id>"
    And             Verify if the Category was added to the application
    And             Update the Category information
    And             I Delete the product by ID
    Examples:
      | name  | id     |
      | Ron12 | Ron_12 |

  Scenario: User can run successful CRUD operation on product details
    When            This will create a New product
    And             I Verify if the Product was added to the application
    And             I Update the product information
    Then            I Delete the product by using Product ID


    Scenario: User can run successful CRUD operation on service details
      When This will create a New service
      And I Verify if the service was added to the application
      And I Update the service information
      Then I service the product by ID

  Scenario: User can run successful CRUD operation on stores details
    When This will create a new Store
    And I Verify if the store was added to the application
    And I Update the store information
    Then I service the store by ID



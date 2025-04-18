Feature: example

  Scenario Outline: Test with multiple users
    Given url 'https://reqres.in/api/users/<userId>'
    When method GET
    Then status 200

    Examples:
      | userId |
      | 1      |
      | 2      |
      | 3      |
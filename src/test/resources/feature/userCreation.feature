Feature: User creation API
  Background:
    #Loading the properties
    * def PropertiesLoader = Java.type('config.PropertiesLoader')
    * def baseUrl = PropertiesLoader.getProperties('reqresIn-base-url')
    * def clientId = PropertiesLoader.getProperties('client_id')
    * def clientSecret = PropertiesLoader.getProperties('client_secret')

    #Using auth token
    * def BaseRestAuth = Java.type('api.auth.BaseRestAuth')
    * def authToken = BaseRestAuth.getAuthToken()


  @Smoke
  Scenario: Create new user
    * def requestBody =
    """
    {
      "name": "Gendalf",
      "job": "Mitrandir"
    }
    """

    Given url baseUrl + '/users'
    And header Authorization = 'Bearer ' + authToken
    And header Content-Type = 'application/json'
    And request requestBody
    When method post

    Then status 201
    Then match response ==
    """
    {
    "name": "Gendalf",
    "job": "Mitrandir",
    "id": "#string",
    "createdAt": "#string"
    }
    """
Feature: Update user

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
  Scenario: Update user data name and job
    * def requestBody =
      """
      {
        "name": "Gendalf",
        "job": "Mitrandir"
      }
      """
    Given url baseUrl + '/user/2'
    And header Authorization = 'Bearer ' + authToken
    And header Content-Type = 'application/json'
    And request requestBody
    When method put
    Then status 200
    Then match response ==
      """
      {
        "name": "Gendalf",
        "job": "Mitrandir",
        "updatedAt": "#string"
      }
      """


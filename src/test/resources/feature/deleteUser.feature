Feature: Delete user
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
  Scenario: Delete user.
    Given url baseUrl + '/users/2'
    When header Authorization = 'Bearer ' + authToken
    And method delete
    Then status 204


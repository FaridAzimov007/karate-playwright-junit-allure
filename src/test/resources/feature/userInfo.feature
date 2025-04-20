Feature: User info tests
  Background:
    #Loading the properties
    * def PropertiesLoader = Java.type('config.PropertiesLoader')
    * def baseUrl = PropertiesLoader.getProperties('reqresIn-base-url')
    * def clientId = PropertiesLoader.getProperties('client_id')
    * def clientSecret = PropertiesLoader.getProperties('client_secret')

    #Using auth token
    * def BaseRestAuth = Java.type('api.auth.BaseRestAuth')
    * def authToken = BaseRestAuth.getAuthToken()



  @allure.label.owner:Farid
  @allure.label.epic:ApicTitle
  @allure.label.story:https://jira.ce.abc.io/browse/rt-5736
  @allure.severity:hight
  @allure.label.tag:some_tag
  @Regression
  Scenario: Fetch list of users (Smoke)
    #Sending get request to get List of users
    Given url baseUrl + '/users?page=2'
    When header Authorization = 'Bearer ' + authToken
    And method get

    #Verify response of list of users
    Then status 200
    And assert response.data.length > 1
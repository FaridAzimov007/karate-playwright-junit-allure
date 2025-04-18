Feature: Authentication Feature
#
#  Scenario: Validate Auth Token
#
#    #Given I initialize the Java class
#    * def BaseRestAuth = Java.type('api.auth.BaseRestAuth')
#
#    #When I retrieve the auth token
#    * def authToken = BaseRestAuth.getAuthToken()
#
#    #Then the auth token should pass validation
#    * match authToken == '#string'
#    * assert authToken != null
#    * assert authToken.trim().length > 0
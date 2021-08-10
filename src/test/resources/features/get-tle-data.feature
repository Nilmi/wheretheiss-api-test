Feature: Return the TLE data for a given satellite in either json or text format
  API: https://api.wheretheiss.at/v1/satellites/[id]/tles

  Scenario: Return the TLE data in JSON format as the default
    When a get request is sent to the API with 'id' 25544
    Then API returns a response with response code 200
    And response content-type is 'application/json'
    And response schema matches with 'tle-schema'
    And response contains field 'id' and value 25544
    And response contains field 'name' and value 'iss'
    And response contains field 'header' and value 'ISS (ZARYA)'

  Scenario: Return the TLE data in JSON format when JSON format is requested
    When a get request is sent to the API with 'id' 25544 and 'format' 'json'
    Then API returns a response with response code 200
    And response content-type is 'application/json'
    And response schema matches with 'tle-schema'
    And response contains field 'id' and value 25544
    And response contains field 'name' and value 'iss'
    And response contains field 'header' and value 'ISS (ZARYA)'

  Scenario: Return the TLE data in text format when text format is requested
    When a get request is sent to the API with 'id' 25544 and 'format' 'text'
    Then API returns a response with response code 200
    And response content-type is 'text/plain'
    And response contains 'ISS (ZARYA)'

  Scenario: Return 404 not found if satellite id is not found
    When a get request is sent to the API with 'id' 99999
    Then API returns a response with response code 404
    And response contains field 'error' and value 'satellite not found'
    And response contains field 'status' and value 404

Feature: Return position, velocity, and other related information about the satellite
  API: https://api.wheretheiss.at/v1/satellites/[id]/positions

  Scenario: Return the satellite data in kilometers as default unit
    When a get request is sent to the API with following parameters
      | id    | timestamps |
      | 25544 | 1436029892 |
    Then API returns a response with response code 200
    And response content-type is 'application/json'
    And response schema matches with 'position-schema'
    And response array contains 1 objects
    And response array contains object with following values
      | name | id    | latitude         | visibility | timestamp  | daynum          | solar_lat      | solar_lon       | longitude      | altitude       | velocity        | units      |
      | iss  | 25544 | -24.870147579366 | daylight   | 1436029892 | 2457208.2163426 | 22.85465108118 | 283.22043315343 | 17.59950771856 | 407.8263965908 | 27597.931157627 | kilometers |

  Scenario: Return the satellite data in miles when the requested unit is miles
    When a get request is sent to the API with following parameters
      | id    | timestamps | units |
      | 25544 | 1436029892 | miles |
    Then API returns a response with response code 200
    And response content-type is 'application/json'
    And response schema matches with 'position-schema'
    And response array contains 1 objects
    And response array contains object with following values
      | name | id    | latitude         | visibility | timestamp  | daynum          | solar_lat      | solar_lon       | longitude      | altitude        | velocity        | units |
      | iss  | 25544 | -24.870147579366 | daylight   | 1436029892 | 2457208.2163426 | 22.85465108118 | 283.22043315343 | 17.59950771856 | 253.41149587602 | 17148.554081346 | miles |

  Scenario: Return the satellite data in kilometers when the requested unit is kilometers
    When a get request is sent to the API with following parameters
      | id    | timestamps | units      |
      | 25544 | 1436029892 | kilometers |
    Then API returns a response with response code 200
    And response content-type is 'application/json'
    And response schema matches with 'position-schema'
    And response array contains 1 objects
    And response array contains object with following values
      | name | id    | latitude         | visibility | timestamp  | daynum          | solar_lat      | solar_lon       | longitude      | altitude       | velocity        | units      |
      | iss  | 25544 | -24.870147579366 | daylight   | 1436029892 | 2457208.2163426 | 22.85465108118 | 283.22043315343 | 17.59950771856 | 407.8263965908 | 27597.931157627 | kilometers |

  Scenario: Return the satellite data for multiple locations
    When a get request is sent to the API with following parameters
      | id    | timestamps            | units |
      | 25544 | 1436029892,1436029902 | miles |
    Then API returns a response with response code 200
    And response content-type is 'application/json'
    And response schema matches with 'position-schema'
    And response array contains 2 objects
    And response array contains object with following values
      | name | id    | latitude         | visibility | timestamp  | daynum          | solar_lat       | solar_lon       | longitude      | altitude        | velocity        | units |
      | iss  | 25544 | -24.870147579366 | daylight   | 1436029892 | 2457208.2163426 | 22.85465108118  | 283.22043315343 | 17.59950771856 | 253.41149587602 | 17148.554081346 | miles |
      | iss  | 25544 | -25.344256596171 | daylight   | 1436029902 | 2457208.2164583 | 22.854640837166 | 283.17877169756 | 18.04663730318 | 253.53270332355 | 17148.247302231 | miles |

  Scenario: Return 404 not found if NORAD catalog id is not found
    When a get request is sent to the API with following parameters
      | id    | timestamps |
      | 99999 | 1436029892 |
    Then API returns a response with response code 404
    And response content-type is 'application/json'
    And response contains field 'error' and value 'satellite not found'
    And response contains field 'status' and value 404

@Smoke
Feature: Booking Travel

  @TC03
  Scenario Outline: Verify booking travel success
    Given the browser is opened
    When select a "<destination>" in the search bar
    And choose checkin-date as "<checkin-date>"
    And choose checkout-date as "<checkout-date>"
    And click the 'Tìm' button
    Then a list of available hotels is displayed
    When choose a hotel option
    Then the hotel details page is opened
    When click the 'Yêu cầu đặt' button
    Then the Travel Combo Request Form is displayed

    Examples:
      | destination | checkin-date | checkout-date     |
      | Phú Quốc    | today + 7    | checkin-date + 3  |
      | Nha Trang   | today + 60   | checkin-date + 7  |
      | Hội An      | today + 90   | checkin-date + 10 |

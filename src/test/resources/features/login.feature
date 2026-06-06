Feature: Login

  @TC01
  Scenario Outline: Verify login success
    Given the browser is opened

    Examples:
      | destination |
      | Phú Quốc    |

  @TC02
  Scenario Outline: Verify login failed
    Given the browser is opened

    Examples:
      | destination |
      | Nha Trang   |

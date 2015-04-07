# ThoseBabyBasicsTest

A colleague approached me for help with a Selenium test case and then I took on this as a hobby project.

Website on test: https://www.thosebabybasics.com/en/

Test Cases:
* login
* add items to cart
* remove an item from the cart
* logout

This project uses:
* Selenium (Java) to automate web browser
* log4j as a logging utility
* Maven as build tool
* TestNG as the test framework

To execute tests: Run MultiBrowserTestSuite.xml as a TestNG Suite.

Key Features:
* The code locates elements on the page using parameterized XPath wherever possible
* Custom exception is added when trying to delete an element not present in the shopping cart
* Selenium code is separated from functional code, therefore it is easier to read tests
* Each web page has its own class under the Structure folder

## Welcome Message
  As a customer,
  I would like to see a welcome message when I start the application,
  so that I feel welcome and know that Biblioteca is available.

  ### Scenario 1:
  Given I start the app
  When it is running
  Then I want to be greeted.

  _Completed_


## List Books
  As a customer,
  after the welcome message appears I would like to see a list of all library books,
  so that I can browse for books I might want to check-out.

  ### Scenario 1:
  Given I have started the application
  When there are five books available
  Then I want to see a list of all five titles.

  ### Scenario 2:
  Given I have started the application
  When there are no books available
  Then I want to be noticed that there are no books for checkout.

  _Completed_

## Book Details
  As a customer,
  I'd like the list of all books to include each books Author and Year Published,
  so that I can be confident that I have found the book I am looking for.

  ### Scenario 1:
  Given I chose to see the list of books
  When there are five books available
  Then I also want to see information on the author and year published of all five books.

  ### Scenario 2:
  Given I chose to see the list of books
  When there are two books with the same title
  Then I want information about author and year to see whether they are identical.

  _Completed_

## Main Menu
  As a customer,
  instead of automatically seeing the book list, I would like to see a list of options
  and be able to choose one.

  ### Scenario 1:
  Given the program is running
  When there exist options to choose from
  Then I want to pick one by typing in a letter.

  _Completed_

## Invalid Menu Option
  As a customer,
  I would like to be notified when I choose an invalid option with the message “Select a valid option!”,
  so that I can know when I need to re-enter my choice.

  ### Scenario 1:
  Given that I choose an option
  When it is not valid
  Then I want to be informed
  And want to be able to choose another option.

  _Completed_

## Quit
  As a customer,
  I would like to continue choosing options until I choose to 'Quit',
  so that I can do something else.

  ### Scenario 1:
  Given the program is running
  When I choose the 'Quit'-option
  Then the program terminates.

  _Completed_

## Checkout Book
  As a librarian,
  I would like customers to be able to check-out a book. Checked out books should not appear in the list of all library books,
  so that I know when a book is not available anymore.

  ### Scenario 1:
  Given that a book is available
  When a customer tries to check it out
  Then it shouldn't be available anymore.

  ### Scenario 2:
  Given a customer checked out a book
  When I look at the list of available books
  Then the checked out book should not be listed.

  _Completed_

## Successful Checkout
  As a customer,
  I would like to know that a book has been checked out by seeing the message “Thank you! Enjoy the book”,
  so that I know, the book has been successfully checked out.

  ### Scenario 1:
  Given that a book is available
  When I try to check it out
  Then the checkout should be successful
  And I want to be informed about the successful checkout.

  _Completed_

## Unsuccessful Checkout
  As a customer,
  I would like to be notified if the book I tried to check-out is not available by seeing the message, “That book is not available.”,
  so that I know that I need to select a different book or fix my spelling error.

  ### Scenario 1:
  Given that a book is not available
  When I try to check it out
  Then the checkout should be unsuccessful
  And I want to be informed about the problem.

  ### Scenario 2:
  Given that a book doesn't exist in the library
  When I try to check it out
  Then the checkout should be unsuccessful
  And I want to be informed about the problem.

  _Completed_

## Return Book
  As a librarian,
  I would like customers to be able to return a book,
  so that other customers can check that book out. Returned books should appear in the list of library books.

  ### Scenario 1:
  Given that a book is checked out
  When a customer tries to return it
  Then it should be available again.

  ### Scenario 2:
  Given that a book is returned
  When I look at the list of available books
  Then the returned book should be listed.

  _Completed_

## Successful Return
  As a customer,
  I would like to be notified if the book I am returning belongs to this library by seeing the message, “Thank you for returning the book.”,
  so that I know I returned the book to the right library.

  ### Scenario 1:
  Given that I had checked out a book
  When I try to return it
  And the book belongs to this library
  Then the book should be returned
  And I want to be informed about the successful return.

  _Completed_

## Unsuccessful Return
  As a customer,
  I would like to be notified if the book I am returning has not been added to this library by seeing the message, “That is not a valid book to return.”,
  so that I can return it to the correct library or fix my spelling error.

  ### Scenario 1:
  Given that I had checked out a book
  When I try to return it
  And the book does not belong to this library
  Then the return should fail
  And I want to be informed about the problem.

  _Completed_

## List Movies
  As a customer,
  I would like to see a list of available movies,
  so that I can browse for a movie that I might check-out.

  ### Scenario 1:
  Given I chose to see the movie list
  When there is an available movie
  Then I want to see it listed with all available details.

  ### Scenario 2:
  Given I chose to see the movie list
  When there is no available movie
  Then I want to be informed about that.

  _Completed_

## Check-out Movie
  As a customer,
  I would like to check out a movie from the library,
  so I can enjoy it at home.

  ### Scenario 1:
  Given I found a movie I want is available
  When I checkout the movie
  Then the checkout should be successful.

  _Completed_

## User Accounts - Login
  As a librarian,
  I want to know who has checked out a book,
  so that I can hold them accountable for returning it.

  ### Scenario 1:
  Given a book is checked out
  When I look at the list of borrowed books
  Then I want to see the library number of the current book holder.
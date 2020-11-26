Sorting

Task 1:

    1 - Advantages
        The program is a Sorting Array runs on abstract class, Node.Java, and it extended by two other classes, Branch.Java and Sorter.Java.

      - Disadvantages
        The program is static which it means that it is incapable of action or change, and it is interactive.
        The program 

    2 - Created the following two arrays to test the sorting algorithm.
        b - {'q', 'w', 'r', 't', 'a', 's', 'd','f','z','x','c', 'v','b' ,'g','y','u','i'};
        c - Random function to build an array of numbers between 1 and 500.

        One Sorter                   Arraylist [a]  68 ms   Arraysize 14 
        One branch / Two Sorters     Arraylist [a]  54 ms   Arraysize 14
        Three Branch / Four Sorters  Arraylist [a]  137 ms  Arraysize 14

        One Sorter                   Arraylist [b]  25 ms   Arraysize 17
        One branch / Two Sorters     Arraylist [b]  62 ms   Arraysize 17
        Three Branch / Four Sorters  Arraylist [b]  154 ms  Arraysize 17

        One Sorter                   Arraylist [b]  468 ms  Arraysize 500
        One branch / Two Sorters     Arraylist [b]  1249 ms Arraysize 500
        Three Branch / Four Sorters  Arraylist [b]  3126 ms Arraysize 500

    - After adding to more arrays, it is obvious that both Arraylists [b] 
    and [c] seems to stay consistant with the recorded times. One Sorter
    recorded as the lowest for both [b] and [c] while Three Branch / Four
    Sorters, recorded the highest times for both [b] and [c]. Arraylist [a]
    showed inconsistancy in compersion to Arraylist [b] and [c], where One 
    branch / Two Sorters recorded the lowest times and Three Branch / Four 
    Sorters continued to record the highest time for Arraylist [a].

    3 - Adding four more branches, that will result in appending the
        current branches and creating a total of seven branches and 8 sorters.

        Two Branch / Three Sorters   Arraylist [a]  103 ms   Arraysize 14 
        Seven branch / Eight Sorters Arraylist [a]  169 ms   Arraysize 14

        Two Branch / Three Sorters   Arraylist [b]  28 ms    Arraysize 17 
        Seven branch / Eight Sorters Arraylist [b]  207 ms   Arraysize 17

        Two Branch / Three Sorters   Arraylist [c]  474 ms   Arraysize 500
        Seven branch / Eight Sorters Arraylist [c]  4704 ms  Arraysize 500

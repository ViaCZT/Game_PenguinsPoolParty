# COMP1110/1140/6710 Assignment 1

## Academic Honesty and Integrity

Honesty and integrity are of utmost importance. These goals are *not* at odds
with being resourceful and working collaboratively. You *should* be resourceful
and you may discuss the assignment
and other aspects of the course with others taking the class. However, the golden
rule is simple: **you must never misrepresent the work of others as your own**.

If you have taken ideas from
elsewhere or used code sourced from elsewhere, you must say so with *utmost
clarity*. At each stage of the assignment you will be asked to submit a statement
of originality, either as a group or as individuals. This statement is the place
for you to declare which ideas or code contained in your submission were sourced
from elsewhere.

Please read the ANU's [official position](http://academichonesty.anu.edu.au/) on
academic honesty. If you have any questions, please ask me.

Carefully review the [statement of originality](originality.yml) which you must
complete.  Edit that statement and update it as you complete the assignment,
ensuring that when you complete the assignment, a truthful statement is
committed and pushed to your repo.

## Purpose

This assignment is introductory, helping you gain familiarity with the basics
of Java, but doing so in the context of slightly larger piece of code.  Most of
the assignment is composed of a series of small tasks.

## Assignment Deliverable

The assignment is worth 5% of your total assessment, and it will be marked out
of 5. However, these marks are [redeemable](https://cs.anu.edu.au/courses/comp1110/assessments/redeemable/) by the exam,
so if your exam mark / 20 is higher than your assignment one mark, you will get the exam mark / 20 rather
than the assignment one mark. **The mark breakdown is described on the
[deliverables](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/#D1A) page.**

The assignment is due at **5:00 PM AEST**, **15 August**, giving you **two** weeks in which to complete it.
You can find [the deadline](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/#D1A)
on the [deliverables page](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/), where all assignment deadlines for this semester are
listed.
Your tutor will mark your assignment by accessing your GitLab repository, so it is essential that you carefully follow
instructions for setting up and maintaining your repository. You will be marked
according to **whatever is committed to your repository at the time of the deadline**.
Since the first assignment is redeemable, [**late extensions
are not offered and will not be given**](https://comp.anu.edu.au/courses/comp1110/policies/#deadlines). As always, throughout the course, if
some significant circumstance arises outside of the course that affects your capacity to complete the course, please
carefully follow the ANU's [special consideration process](http://www.anu.edu.au/students/program-administration/assessments-exams/special-assessment-consideration), and your circumstances will be accounted for in your final
assessment.

## Overview

The assignment is based on a simple children's puzzle called [Penguins Pool Party](https://www.smartgames.eu/uk/one-player-games/penguins-pool-party),
made by [SmartGames](http://www.smartgames.eu/), a producer of educational games. The design of the game and all imagery in this assignment comes from their
Penguins Pool Party game.

![](assets/overview.png)

The game comes with 60 pre-defined challenges, organised into four
difficulty levels from *starter* to *master*. Each challenge defines how
many penguins are on the board, and their positions.

The first challenge from the game booklet is shown below:

![](assets/challenge_1.png)

The objective of the game is to place all of the four ice blocks on the game
board, without covering any of the penguins or any of the other ice blocks.

We provide you with a [paper version of the game](assets/papergame.pdf),
which you can print out to help you visualise the game if you wish.

## Your Task

This repository contains a java implementation of Penguins Pool Party,
including a graphical user interface (GUI).
Unfortunately this version of the game has some parts missing.
While the graphical user interface is complete, some of the important
game logic is missing, so it won't work as described above. It is your task
to fix the problems. Each specific subtask is (a) listed as an issue in
the issue tracker in this repository and (b) identified by a `FIXME`
comment in the source code. You should not change other parts of the
code than those indicated. When all tasks are completed, the game will
function correctly. Check your changes by using the provided unit tests.

The rest of this README file describes the components of the game, and
how they are represented in the assignment, in more detail.

### Board

The board is a 5 x 4 hexagonal grid. Below is a depiction of the board, along
with each hexagon's Cartesian coordinates:

![](assets/board.png)

Notice that, because of the shape of hexagons, odd-numbered columns (that is, 1
and 3) are slightly offset from their even counterparts. Taking this offset
into account, let's treat "rows" on this board as such:

![](assets/board_annotated.png)

where the red lines separate rows from one another. You can verify that these
lines separate rows yourself by checking the second entry of each coordinate,
which denotes the y-coordinate of that hexagon. The hexagons inside the same
red boundary should all have the same y-coordinate.

If you are interested in computational representations of hexagons, you might
like to consult [this resource](https://www.redblobgames.com/grids/hexagons/).
While it complements the content in this assignment, this readme and the course
content should be sufficient for you to be able to start this assignment.

### Pieces

The game has four identical penguin pieces and four differently-shaped
ice blocks. 

Each penguin takes up one hexagon on the game board, while each 
ice block takes up four hexagons on the game board. It is not necessary
for all four penguin pieces to be on the board, but all four ice blocks must be
placed on the board to solve the game.

Each ice block has the following information:
* A one-letter ID (out of 'A', 'B', 'C' and 'D');
* A set of coordinates denoting the piece's origin; and
* The rotation of the ice block.

Below is the representation of each ice block in this game, at rotation 0:

![](assets/ice_pieces.png)

The blue hexagons make up the ice block. The white hexagons with a dotted
outline are there to make it easier to see the ice blocks in the context of
the board.

Each ice block contains a blue hexagon with a red outline. This hexagon is the
*origin* of the ice block. The coordinates of the other hexagons that make up
the ice block are relative to the coordinates of the origin.

Below is a chart with all rotations for every ice block:

![](assets/ice_rotations.png)

You may want to hold onto this chart when completing this assignment. It may be
useful for problems related to the spatial reasoning aspects of this game. If
it is easier to remember, you can think of each rotation number *n* as
corresponding to the rotation of a piece such that its origin shares exactly
one edge with another hexagon in the same piece. This is the *n*-th edge from
the origin, where *n* = 0 is the top-most edge and successive values of *n*
move around the hexagon clockwise.

Note that piece C is symmetrical. When writing solutions in string form, *the
lower rotation number* is provided. This is because any instance of piece C
with a rotation number between 3 and 5 (inclusive) has an equivalent form with
a rotation number between 0 and 2, and a different set of origin coordinates.

The only information required to define the penguin pieces is their Cartesian
coordinates on the board.

### Challenges

The objective of the game is to place the four ice blocks on the game board,
without covering any of the penguins or any of the other ice blocks in the
water.

Each challenge defines how many penguins are on the board, and their positions.
Easier challenges use more penguins than the more difficult ones.

The challenge shown above contains four penguins: their Cartesian coordinates
are (2, 0), (1, 1), (3, 1) and (2, 3).

The game is complete when all four ice blocks are placed on the board,
such that no two blocks overlap, and no block covers a penguin.

### Solutions

Each challenge has one unique solution. A solution is defined as a placement
of all four ice blocks on the board. A *placement* of an ice block is comprised
of the ice block's ID, the location of its origin on the board, and the ice
block's rotation. See the [Encoding the Game](#encoding-the-game) section for
details of the string encoding of a solution.

## Encoding the Game

This section explains how certain elements of the game are encoded into
strings. While such an encoding is not necessary for designing software using
object orientation, the encoding and decoding used in this assignment will be
very similar to the work you will be doing in Assignment 2. Although this work
is done for you in the assignment, we encourage you to look at the methods that
have been written for encoding/decoding in this assignment. In particular, have
a look at the various `toString()` methods in the code.

### Initial State

The initial state of a challenge is defined only by the placement of penguins
on the board. Therefore, the initial state is a two- to eight-character string,
loosely defined as

``{first penguin coordinates}{second penguin coordinates}{third penguin coordinates}{fourth penguin coordinates}``

Each coordinate is two characters long: the first character corresponds to the
x-coordinate, and the second character corresponds to the y-coordinate. For
example, the challenge displayed [above](#overview) would be represented
in string form as

``20113123``.

Breaking this string up into `20`, `11`, `31` and `23` gives us the coordinates
of each penguin as mentioned in the [Challenges](#challenges) section.

Throughout each challenge, the convention is to order penguins first by
y-coordinate, then by x-coordinate. The order is ascending.

The string only encodes the positions of penguins on the board. If there are
some penguins not on the board, then their corresponding coordinate string is
empty.

### Solution

A solution is encoded by the placement of all four ice blocks on the board. A
*placement* of an ice block comprises the ice block's ID, the location of its
origin on the board, and the ice block's rotation. This is represented in
string format as

``{ID}{origin location}{rotation}``.

As an example, consider the solution to the challenge provided in the
[Objective](#objective) section of this readme:

![](assets/solution_1.png)

In detail, this solution can be described as:

* A placement of ice block A at location (3, 2) and with rotation 1;
* A placement of ice block B at location (1, 0) and with rotation 4;
* A placement of ice block C at location (0, 3) and with rotation 1; and
* A placement of ice block D at location (2, 2) and with rotation 2.

Note that there is an alternative placement of piece C, in which its location
is (2, 1) and its rotation is 4. Because these two placements are equivalent,
we take the placement with the lower rotation number.

If you are struggling to understand the above dot points, or would like to read
more about the symmetry of piece C, please consult the ice
block rotation chart in the [Pieces](#pieces) section of this readme.

So, in string form, the solution to the first challenge would be denoted as

``"A321B104C031D222"``.

It is convention for solutions to be ordered alphabetically by ice block ID.

## Legal and Ethical Issues

First, as with any work you do, you must abide by the principles of
[honesty and integrity](https://comp.anu.edu.au/courses/comp1110/policies/#academic-integrity).
We expect you to demonstrate honesty and integrity in everything you do.

In addition to those ground rules, you are to follow the rules one
would normally be subject to in a commercial setting. In particular,
you may make use of the works of others under two fundamental
conditions: a) your use of their work must be clearly acknowledged,
and b) your use of their work must be legal (for example, consistent
with any copyright and licensing that applies to the given material).
**Please understand that violation of these rules is a very serious
offence**. However, as long as you abide by these rules, you are
explicitly invited to conduct research and make use of a variety of
sources. You are also given an explicit means with which to declare
your use of other sources (via originality statements you must
complete). It is important to realize that you will be assessed on the
basis of your original contributions to the project. While you won't
be penalized for correctly attributed use of others' ideas, the work
of others will not be considered as part of your
contribution. Therefore, these rules allow you to copy another
student's work entirely if: a) they gave you permission to do so, and
b) you acknowledged that you had done so. Notice, however, that if you
were to do this you would have no original contribution and so would
receive no marks for the assignment (but you would not have broken any
rules either).

## Evaluation Criteria

**The mark breakdown is described on the
[deliverables](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/#D1A) page.**

The following task numbers are associated with specific gitlab issues.

**Pass**

Tasks #1, #2, #3, #4, #5, #6.

**Credit**

Task #7 and #8 (*in addition to all tasks required for Pass*)

**Distinction**

Task #9 and #10 (*in addition to all tasks required for Credit*)

**High Distinction**

- Task #11 (*in addition to all tasks required for Distinction*)

**IMPORTANT NOTE:** *It is very important that you understand that you are*
**not** *required to complete all elements of the assignment. In fact, **you
are not encouraged to pursue the Distinction and High Distinction tasks unless
you feel motivated and able to do so**. Recall that the assignment is
redeemable against the exam. The last part of the assignment are significantly
harder than the others, but is worth only one additional mark. We don't
encourage you to spend too much time on this unless you are enjoying the
challenge of solving these harder problems. Some tasks involve
ideas that we have **not covered**, or not covered deeply in class; you may need
to go beyond the course material.*

Corey Shapiro
3-5-12
HW#3

    The contents of this folder relate to a digital representation of
a magical land called Aviary, where various different types of birds are
free to fly around to their heart's content. This land exists in the
DrawingPanel.java universe, where Aviary.java wills our specific 20 by 20 
field of view into existance. Aviary contains 4 species of birds; Bluebirds,
Cardinals, Hummingbirds, and Vultures. Each species is represented by a
different class and have their own unique color and flight pattern. However, 
they live in the same Aviary, and must interact with their world in essentially 
the same way. The basic rules defining these interactions are shown in 
Bird.java. Also, all of the birds have a common ancestor, known by today's
computer's scientists (or at least by the people grading this assignment) as 
AbstractBird.java. Every bird's color and way of flying has evolved from
AbstractBird, so it is quite conveniant that each of these characteristics 
can be referenced from a common superclass. As its name suggests, the Bluebird
is colored blue. It is born facing right and always lives out its life flying
forward in a zig-zag pattern. Luckily, it is inteligent enough to understand
the concept of an upcoming wall, and will turn around when it reaches one.
Cardinals are red and are born facing up. They cannot comprehend a horizontal
dimension, so they just fly up and down, rebounding off the top and bottom of
Aviary. Hummingbirds are magenta, and have the power to teleport to any spot
in Aviary instantaniously. The reasoning behind where they decide to go is 
psuedorandom, and represented by the Random class. Vultures are black, and 
born facing up. They fly counterclockwise and never seem to leave the comfort
zone of their four unit radius. One final note; Vulture and Bluebird parents
are careful not to lay their eggs on the edge of the world, as their babies
would accidentally crash into a wall and hurt themselves.     
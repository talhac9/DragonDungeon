# DragonDungeon
A warrior has been trapped in the dungeons of the evil Black Queen. The dungeons consist of a set of hexagonal shaped chambers. You know the initial chamber where the warrior has been initially placed by the Queen and where the exit from the dungeons is. The warrior can move between adjacent chambers and the program will find the shortest possible path from the initial chamber to the exit, if one exists. This program uses a priority queue to solve the correct shortest path of the dungeon.

There are 4 types of chambers in the dungeons:

- Empty chambers. The warrior can safely walk across one of these chambers to move to an adjacent one.
- Dragon chambers. Each one of these chambers is a dragon lair. The warrior cannot go through these chambers or through any chamber adjacent to a dragon chamber. The warrior can enter a dragon chamber or a chamber adjacent   
  to a dragon chamber, but when he realizes that he is inone of these chambers he has to leave it right away.
- Wall chambers. The warrior cannot enter them.
- Exit chamber. This allows the warrior to exit the dungeons. The exit chamber is an empty chamber.

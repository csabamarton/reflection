# Experiment with reflection

In this two module i've tried to simplify one of my real life issue:

Given a package with classes. How can i decide which one is an implementation of a specific class.

I have walked around two possible solutions:

In the impl-reflection module there was a container class: the FruitProvider.

The program gets its declared fields and check them, whether are they right implementations or not.

In the package-reflection module there is no container class. The program scans the package and does the same check after it has obtained the classes.

# Experiment with reflection

In this two module i tried to simplify one of my real life issue:

Given a package with classes. How can i decide which one is an implementation of a specific class.

I have walk around two possible solutions:

In the impl-reflection module there was a container class: the FruitProvider.

The program get its declared fields and check them, whether are they right implementations or not.

In the package-reflection module there is no container class. The program scans the package and does the same after it obtains the classes.

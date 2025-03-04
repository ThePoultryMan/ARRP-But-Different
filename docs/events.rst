.. _Events:

======
Events
======

Before Vanilla
==============

Registers the resource pack at a lower priority than both minecraft and modded resources.

Between Vanilla and Mods
========================

Registers the resource pack between minecraft resources and modded resources.

Between Mods and User
=====================

Similar to the `Before User`_ event, but it is always enabled, and is not visible in the resource
pack screen.

Before User
===========

Registers the resource pack at a higher priority than both minecraft and modded resources, but lower
priority than the user's resources.

After Vanilla
=============

Registers the resource pack at a the "highest" priority. Some resource packs may take the precedent
over the registered one.

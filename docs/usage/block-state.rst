===============
Add Blockstates
===============

To add a blockstate, you must add a ``JState`` instance to the RRP.

.. note:: The following examples assume you are working with a RRP named *pack*.

First, create a ``JState`` instance.

.. code-block:: java
    JState blockstate = new JState();

From there you then have to decide whether you're using the typical "variant" format or the "multipart"
format. See the `Minecraft Wiki`_ for more information on blockstate definitions.

.. _Minecraft Wiki: https://minecraft.wiki/w/Blockstates_definition

Regardless of whether you are using variants or multiparts, the api for adding them to the blockstate
is the same. You can use JState::add to add a single variant or multipart, or you can use JState::addAll
to add multiple variants or multiparts.

.. caution:: Using both variants and multiparts on the same JState instance will cause runtime errors

.. tip:: Like most J* classes provided by ARRP But Different, JState use the builder pattern, allowing consecutive calls.

.. note:: JBlockModel

    The two sections below refer to ``JBlockModel``. This builder takes the resource location that
    refers to a block model. It allows setting the x and y rotation, whether to use uvlock, and the
    weight of the model.

Variants
========

When using variants in your blockstate, you must create one or more ``JVariant`` s, and add then them
to your blockstate.

The ``JVariant`` API is simple. You use ``JVariant::condition`` to add a condition that determines
when the specified model shows up. For example, if you wanted to show a model while your block is
powered, you would use ``new JVariant().condition("powered", true)``. To specify the model that shows
up, you would use the ``JVariant::model`` function, which takes a ``JBlockModel`` instance.

Example
-------

.. code-block:: java

    ...
    blockstate.add(
            new JVariant()
                    .condition("powered", true)
                    .model(new JBlockModel("block/spruce_door_bottom_left").x(90))
    );

Multiparts
==========

When using multiparts in your blockstate, you must create one or more ``JMultipart`` s, and then add
them to  your blockstate.

The models that will be displayed are added to the multipart either through the constructor or through
the ``JMultipart::addModel`` or ``JMultipart::addAllModels`` functions. To determine when the model
will appear, you will set ``JMultipart::when``.

``JWhen`` is decently complicated, you should read through the Minecraft Wiki page on blockstates
(linked above).

Example
-------

You can find a simple example of using JMultipart at
https://github.com/ThePoultryMan/Lanterns-Belong-On-Walls/blob/main/src/main/java/io/github/thepoultryman/walllanterns/WallLanterns.java#L40

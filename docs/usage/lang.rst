================
Add Translations
================

To add a translation, you must add a *JLang* instance to the RRP.

.. note:: The following examples assume you are working with a RRP named *pack*.

First, create a *JLang* instance.

.. code-block:: java
    JLang lang = new JLang();

Once a *JLang* has been created, you can start adding translations to it.

.. tip:: JLangs can be constructed via a builder pattern, allowing consecutive translations to be placed
one after another.

Example:

.. code-block:: java

    ...
    lang.addBlockTranslation(ResourceLocation.withDefaultNamespace("torch"), "Torch but it's different but it's not so it's the same.)
            .addItemTranslation(Items.STICK, "It's still a stick");

As you can see above, there are multiple ways of adding translations. The easiest method to add a
translation is to refer to the object that you want to add a translation for, but in cases that this
won't work, you can always add a translation via the resource location directly.

Once you have finished adding translations to the *JLang* instance, you need to add the instance
into the pack.

.. code-block:: java

    ...
    pack.addLang(ResourceLocation.fromNamespaceAndPath(MOD_ID, "en_us", lang);

Now test that the translations appear in game, and you're done.

.. tip:: Builder Pattern

    Because *JLang* uses a builder pattern, you can create it, and use it, directly within the
    *addLang* function.

    .. code-block:: java

        pack.addLang(ResourceLocation.fromNamespaceAndPath(MOD_ID, "en_us",
            new JLang().addBlockTranslation(...)
                    .addItemTranslation(...)
        );

# Dynadapter

Dynadapter lets you develop with RecyclerView at lightning speed.

## Without Dynadapter

Without Dyadapter, getting something to show up in a RecyclerView involved these steps:

- Create a layout for your item
- Create an `Adapter` for the item
- Create an associated `Holder` for the item
- Write some code in your `Adapter`/`Holder` to bind the layout to your item

This can get very old very fast.

## With Dynadapter

With Dynadapter, the above steps turn into this:

- Create a layout for your item
- Make your existing item implement `DynItem`

That's it!

## Example (Kotlin)

In this example, we have a `User` data class we want to show up in a layout. Here's what it looks like:

```kotlin
data class User(
    var id: Int? = null,
    var name: String? = null,
    var age: Int? = null
)
```

Let's say our layout (`R.layout.item_user`) for showing user items involves 2 views:
- `user_name`: A `TextView` for displaying the user's name
- `user_age`: A `TextView` for displaying the user's age

To use our existing `User` class with Dynadapter, all we need to do is implement `DynItem` and its required methods:

```kotlin
data class User(
    var id: Int = 0,
    var name: String? = null,
    var age: Int? = null
) : DynItem() {
  // The unique ID for this item.
  override fun getId(): Long = id.toLong()

  // This is the layout that should be inflated when the DynAdapter renders this item.
  override fun getLayout(): Int = R.layout.item_user

  override fun onBindView(context: Context, position: Int, items: List<DynItem>, view: View) {
    // `view` is our root view (our layout, `R.layout.item_user`)
    val userNameView = view.findViewById<TextView>(R.id.user_name)
    userNameView.text = name

    val userAgeView = view.findViewById<TextView>(R.id.user_age)
    userAgeView.text = "Age: $age"
  }
}
```

Then we can simply use the `DynAdapter` class for our `RecyclerView`:

```kotlin
val items = listOf(
  User(
    id = 1,
    name = "TJ",
    age = 17
  )
)

recyclerView.adapter = DynAdapter(context, items)
```

## Multiple Item Types

It's easy to show multiple types of items in a RecyclerView powered by Dynadapter. Drawing from our above example, if we had another `DynItem` called a `Post`, we could simply add it to our list:

```kotlin
val items = listOf(
  User(
    id = 1,
    name = "TJ",
    age = 17
  ),
  Post(
    id = 2,
    text = "Hello!"
  )
)

recyclerView.adapter = DynAdapter(context, items)
```

Dynadapter will take care of the rest. No more fiddling with `itemType`s.

## Contributing

Contributions are very welcome, please submit a PR if you'd like to. There aren't any strict contribution guidelines.

## License

This project is licensed under the GPL-3.0:

```
Dynadapter
Copyright (C) 2018 Academus Technologies, LLC

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
```